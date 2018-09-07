package cn.learn.fastdfs.controller;

import cn.learn.fastdfs.file.FastDFSClient;
import cn.learn.fastdfs.file.FastDFSFile;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author 邵益炯
 * @date 2018/9/7
 */
@Controller
@Slf4j
public class UploadController {


  @GetMapping("/")
  public String index() {
    return "upload";
  }

  @PostMapping("/upload")
  public String singleFileUpload(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) {
    if (file.isEmpty()) {
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return "redirect:uploadStatus";
    }
    try {
      // Get the file and save it somewhere
      String path = saveFile(file);
      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + file.getOriginalFilename() + "'");
      redirectAttributes.addFlashAttribute("path",
          "file path url '" + path + "'");
    } catch (Exception e) {
      log.error("upload file failed", e);
    }
    return "redirect:/uploadStatus";
  }

  @GetMapping("/uploadStatus")
  public String uploadStatus() {
    return "uploadStatus";
  }

  /**
   * @param multipartFile
   * @return
   * @throws IOException
   */
  public String saveFile(MultipartFile multipartFile) throws IOException {
    String[] fileAbsolutePath = {};
    String fileName = multipartFile.getOriginalFilename();
    String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
    byte[] fileBuff = null;
    InputStream inputStream = multipartFile.getInputStream();
    if (inputStream != null) {
      int len1 = inputStream.available();
      fileBuff = new byte[len1];
      inputStream.read(fileBuff);
    }
    inputStream.close();
    FastDFSFile file = new FastDFSFile(fileName, fileBuff, ext);
    try {
      //upload to fastdfs
      fileAbsolutePath = FastDFSClient.upload(file);
    } catch (Exception e) {
      log.error("upload file Exception!", e);
    }
    if (fileAbsolutePath == null) {
      log.error("upload file failed,please upload again!");
    }
    return FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
  }
}