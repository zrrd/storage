package cn.learn.cloud_storage.application.steamParty.mapper;

import cn.learn.cloud_storage.application.steamParty.domain.Game;
import cn.learn.cloud_storage.query.steamParty.dto.GameDto;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
 * 游戏查询mapper
 *
 * @author shaoyijiong
 * @since 2018/7/1
 */
public interface GameQueryMapper extends BaseMapper<Game> {

  /**
   * 根据分数得到游戏
   *
   * @param score 评分
   * @return 高分游戏列表
   */
  List<GameDto> highScoreList(@Param("score") Double score);

  /**
   * 测试下通过RowBounds分页
   *
   * @param rowBounds 分页工具
   * @return 游戏列表
   */
  List<GameDto> list(RowBounds rowBounds);
}
