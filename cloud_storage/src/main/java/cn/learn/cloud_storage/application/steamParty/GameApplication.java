package cn.learn.cloud_storage.application.steamParty;

import cn.learn.cloud_storage.application.steamParty.domain.Game;
import cn.learn.cloud_storage.application.steamParty.dto.GameDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 游戏
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Slf4j
@Component
@Transactional(rollbackFor = Throwable.class)
public class GameApplication {

  /**
   * 新增游戏
   *
   * @param gameDto 新增游戏数据对象
   */
  public boolean insertGame(GameDto gameDto) {
    Game game = new Game(gameDto);
    return game.insert();
  }

}
