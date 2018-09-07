package cn.learn.cloud_storage.query.steamParty;

import cn.learn.cloud_storage.query.steamParty.dto.GameDto;
import cn.learn.cloud_storage.query.steamParty.mapper.GameQueryMapper;
import com.github.pagehelper.PageHelper;
import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

/**
 * 游戏帮助查询类
 *
 * @author shaoyijiong
 * @since 2018/7/1
 */
@Component
public class GameQuery {

  @Resource
  private GameQueryMapper gameQueryMapper;

  /**
   *   通过rowBounds分页
   */
  public List<GameDto> list(int pageNum,int pageSize){
    //参数数pageHelper不同
    RowBounds rowBounds = new RowBounds((pageNum-1)*pageSize,pageSize);
    List<GameDto> result = gameQueryMapper.list(rowBounds);
    System.out.println(result.get(0));
    System.out.println(result.size());
    return result;
  }

  /**
   * 通过pageHelper分页
   */
  public List<GameDto> quertGames(int pageNum,int pageSize,double score){
    PageHelper.startPage(pageNum,pageSize);
    List<GameDto> result = gameQueryMapper.highScoreList(score);
    System.out.println(result.get(0));
    System.out.println(result.size());
    return result;
  }


}
