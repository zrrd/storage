package cn.learn.cloud_storage.file.qiuniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件上传实现类
 *
 * @author shaoyijiong
 * @date 2018/7/9
 */
@Component
public class QiniuFileResource implements FileResource {

  /**
   * 七牛ak
   */
  @Value("${qiniu.accesskey}")
  private String accessKey;

  /**
   * 七牛sk
   */
  @Value("${qiniu.secretkey}")
  private String secretKey;

  /**
   * 七牛存储空间名称
   */
  @Value("${qiniu.bucket}")
  private String bucket;

  @Value("${qiniu.url}")
  private String url;

  /**
   * 构造一个带指定Zone对象的配置类 ...其他参数参考类注释
   */
  private static final Configuration CFG = new Configuration(Zone.zone0());


  @Override
  public String uploadFile(byte[] fileByte, String fileName) {
    Auth auth = Auth.create(accessKey, secretKey);
    String upToken = auth.uploadToken(bucket);
    //...生成上传凭证，然后准备上传
    UploadManager uploadManager = new UploadManager(CFG);
    try {
      Response response = uploadManager.put(fileByte, fileName, upToken);
      DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
      System.out.println(putRet.key);
      System.out.println(putRet.hash);
      return putRet.hash;
    } catch (QiniuException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String uploadFileStream(InputStream inputStream, String fileName) {
    Auth auth = Auth.create(accessKey, secretKey);
    String upToken = auth.uploadToken(bucket);
    //...生成上传凭证，然后准备上传
    UploadManager uploadManager = new UploadManager(CFG);
    try {
      Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
      DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
      System.out.println(putRet.key);
      System.out.println(putRet.hash);
      return putRet.hash;
    } catch (QiniuException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<String> fileList(String prefix, Integer limit) {
    Auth auth = Auth.create(accessKey, secretKey);
    BucketManager bucketManager = new BucketManager(auth, CFG);
    //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
    String delimiter = "";
    //列举空间文件列表
    BucketManager.FileListIterator fileListIterator = bucketManager
        .createFileListIterator(bucket, prefix, limit, delimiter);
    List<String> files = new ArrayList<>();
    while (fileListIterator.hasNext()) {
      //处理获取的file list结果
      FileInfo[] items = fileListIterator.next();
      files = Arrays.stream(items).map(a -> url + "/" + a.key).collect(Collectors.toList());
    }
    return files;
  }

}
