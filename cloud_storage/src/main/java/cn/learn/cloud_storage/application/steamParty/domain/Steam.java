package cn.learn.cloud_storage.application.steamParty.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * steamParty
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Getter
@Setter
@TableName("steam")
public class Steam extends Model<Steam> {

  private Integer id;

  private Integer gameId;

  private Integer userId;

  @Override
  protected Serializable pkVal() {
    return id;
  }
}
