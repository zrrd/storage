package cn.learn.fastdfs.file;

import lombok.Data;

/**
 * .
 *
 * @author 邵益炯
 * @date 2018/9/7
 */
@Data
public class FastDFSFile {

  private String name;

  private byte[] content;

  private String ext;

  private String md5;

  private String author;

  public FastDFSFile(String name, byte[] content, String ext, String height,
      String width, String author) {
    this.name = name;
    this.content = content;
    this.ext = ext;
    this.author = author;
  }

  public FastDFSFile(String name, byte[] content, String ext) {
    this.name = name;
    this.content = content;
    this.ext = ext;

  }
}
