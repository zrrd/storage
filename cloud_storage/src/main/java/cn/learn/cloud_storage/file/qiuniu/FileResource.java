package cn.learn.cloud_storage.file.qiuniu;


import java.io.InputStream;
import java.util.List;

/**
 * @author 邵益炯
 * @date 2018/8/8
 */
public interface FileResource {

  /**
   * 上传文件
   *
   * @param fileByte 文件byte[]对象
   * @param fileName 文件名
   * @return 文件完整路径
   */
  String uploadFile(byte[] fileByte, String fileName);


  /**
   * 上传文件
   *
   * @param inputStream 文件流
   * @param fileName 文件名
   * @return 文件路径
   */
  String uploadFileStream(InputStream inputStream, String fileName);

  /**
   * 文件路径
   *
   * @param prefix 文件前缀
   * @param limit 文件数
   * @return 文件路径列表
   */
  List<String> fileList(String prefix, Integer limit);
}
