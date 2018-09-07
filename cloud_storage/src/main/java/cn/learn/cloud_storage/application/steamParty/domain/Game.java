package cn.learn.cloud_storage.application.steamParty.domain;

import cn.learn.cloud_storage.application.steamParty.dto.GameDto;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * 游戏
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Getter
@Setter
@TableName("game")
public class Game extends Model<Game> {

  private Integer id;

  private String name;

  private Double price;

  private Date publishDate;

  private Score score;

  private String image;

  /**
   * 新增游戏构造方法
   *
   * @param gameDto 游戏传输对象
   */
  public Game(GameDto gameDto) {
    this.name = gameDto.getName();
    this.price = gameDto.getPrice();
    this.publishDate = gameDto.getPublishDate();
    this.score = Score.valueOf(gameDto.getScore());
    this.image = gameDto.getImage();
  }


  @Override
  protected Serializable pkVal() {
    return id;
  }
}
