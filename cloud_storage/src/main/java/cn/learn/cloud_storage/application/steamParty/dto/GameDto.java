package cn.learn.cloud_storage.application.steamParty.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * game传输类
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Getter
@Setter
public class GameDto {

  private String name;

  private Double price;

  private Date publishDate;

  private Double score;

  private String image;

  public GameDto(String name, Double price, Date publishDate, Double score, String image) {
    this.name = name;
    this.price = price;
    this.publishDate = publishDate;
    this.score = score;
    this.image = image;
  }
}
