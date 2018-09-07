package cn.learn.cloud_storage.application.steamParty.domain;

import com.baomidou.mybatisplus.enums.IEnum;
import java.io.Serializable;
import java.util.Objects;

/**
 * 分数枚举类
 *
 * @author shaoyijiong
 * @since 2018/7/4
 */
public enum Score implements IEnum {


  /**
   * 优秀
   */
  EXCELLENT(9.0),

  /**
   * 良好
   */
  GOOD(7.5),

  /**
   * 及格
   */
  PASS(6.0),

  /**
   * 不及格
   */
  FAIL(4.5);

  private Double value;

  Score(Double value) {
    this.value = value;
  }

  public static Score valueOf(Double value) {
    for (Score score : Score.values()) {
      if (Objects.equals(score.value, value)) {
        return score;
      }
    }
    return null;
  }

  @Override
  public Serializable getValue() {
    return this.value;
  }
}
