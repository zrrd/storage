package cn.learn.fastdfs.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

/**
 * 文件上传相关类.
 *
 * @author 邵益炯
 * @date 2018/9/7
 */
@Data
@Slf4j
public class FastDFSClient {

  static {
    try {
      String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
      ClientGlobal.init(filePath);
    } catch (Exception e) {
      log.error("FastDFS Client Init Fail!", e);
    }
  }

  public static String[] upload(FastDFSFile file) {
    log.info("File Name: " + file.getName() + "File Length:" + file.getContent().length);

    NameValuePair[] meta_list = new NameValuePair[1];
    meta_list[0] = new NameValuePair("author", file.getAuthor());

    long startTime = System.currentTimeMillis();
    String[] uploadResults = null;
    StorageClient storageClient = null;
    try {
      storageClient = getTrackerClient();
      uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
    } catch (IOException e) {
      log.error("IO Exception when uploadind the file:" + file.getName(), e);
    } catch (Exception e) {
      log.error("Non IO Exception when uploadind the file:" + file.getName(), e);
    }
    log.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

    if (uploadResults == null && storageClient != null) {
      log.error("upload file fail, error code:" + storageClient.getErrorCode());
    }
    String groupName = uploadResults[0];
    String remoteFileName = uploadResults[1];

    log.info(
        "upload file successfully!!!" + "group_name:" + groupName + ", remoteFileName:" + " "
            + remoteFileName);
    return uploadResults;
  }

  public static FileInfo getFile(String groupName, String remoteFileName) {
    try {
      StorageClient storageClient = getTrackerClient();
      return storageClient.get_file_info(groupName, remoteFileName);
    } catch (IOException e) {
      log.error("IO Exception: Get File from Fast DFS failed", e);
    } catch (Exception e) {
      log.error("Non IO Exception: Get File from Fast DFS failed", e);
    }
    return null;
  }

  public static InputStream downFile(String groupName, String remoteFileName) {
    try {
      StorageClient storageClient = getTrackerClient();
      byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
      return new ByteArrayInputStream(fileByte);
    } catch (IOException e) {
      log.error("IO Exception: Get File from Fast DFS failed", e);
    } catch (Exception e) {
      log.error("Non IO Exception: Get File from Fast DFS failed", e);
    }
    return null;
  }

  public static void deleteFile(String groupName, String remoteFileName)
      throws Exception {
    StorageClient storageClient = getTrackerClient();
    int i = storageClient.delete_file(groupName, remoteFileName);
    log.info("delete file successfully!!!" + i);
  }

  public static StorageServer[] getStoreStorages(String groupName)
      throws IOException {
    TrackerClient trackerClient = new TrackerClient();
    TrackerServer trackerServer = trackerClient.getConnection();
    return trackerClient.getStoreStorages(trackerServer, groupName);
  }

  public static ServerInfo[] getFetchStorages(String groupName,
      String remoteFileName) throws IOException {
    TrackerClient trackerClient = new TrackerClient();
    TrackerServer trackerServer = trackerClient.getConnection();
    return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
  }

  public static String getTrackerUrl() throws IOException {
    return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":"
        + ClientGlobal.getG_tracker_http_port() + "/";
  }

  private static StorageClient getTrackerClient() throws IOException {
    TrackerServer trackerServer = getTrackerServer();
    return new StorageClient(trackerServer, null);
  }

  private static TrackerServer getTrackerServer() throws IOException {
    TrackerClient trackerClient = new TrackerClient();
    return trackerClient.getConnection();
  }

}
