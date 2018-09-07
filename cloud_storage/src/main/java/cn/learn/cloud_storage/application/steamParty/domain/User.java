package cn.learn.cloud_storage.application.steamParty.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Getter
@Setter
@TableName("user")
public class User extends Model<User> {

  private Integer id;

  private String name;

  private Integer gameNum;

  private Double money;

  @Override
  protected Serializable pkVal() {
    return id;
  }
}
