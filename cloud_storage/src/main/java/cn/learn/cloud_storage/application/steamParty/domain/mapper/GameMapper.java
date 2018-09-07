package cn.learn.cloud_storage.application.steamParty.domain.mapper;

import cn.learn.cloud_storage.application.steamParty.domain.Game;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

/**
 * @author win
 */
public interface GameMapper extends BaseMapper<Game> {

  /**
   * 找到所有
   *
   * @return 游戏列表
   */
  List<Game> selectAll();

}