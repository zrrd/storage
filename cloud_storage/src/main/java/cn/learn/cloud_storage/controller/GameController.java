package cn.learn.cloud_storage.controller;

import cn.learn.cloud_storage.file.qiuniu.FileResource;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 游戏相关响应
 *
 * @author shaoyijiong
 * @date 2018/7/9
 */
@Controller
@RequestMapping("/game")
public class GameController {

  @Resource
  private FileResource fileResource;

  @GetMapping("/uploadImg")
  public String uploadImg(){
    return "upload";
  }

  @ResponseBody
  @PostMapping("/uploadImg")
  public String uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
    byte[] fileBytes = file.getBytes();
    String fileName = file.getOriginalFilename();
    fileResource.uploadFile(fileBytes, fileName);
    return "上传成功";
  }

  @GetMapping("/testImg")
  public ModelAndView testImg(ModelAndView modelAndView){
    //这个url是已经上传的文件
    String url = "http://pbl2silgf.bkt.clouddn.com/api_thumb_450 (1).jpg";
    modelAndView.addObject("url",url);
    modelAndView.setViewName("img");
    return modelAndView;
  }
}
