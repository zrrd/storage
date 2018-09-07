package cn.learn.cloud_storage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybaitsPlus配置
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Configuration
@MapperScan(basePackages = {"cn.learn.cloud_storage.application.steamParty.domain.mapper", "cn.learn.cloud_storage.query.steamParty.mapper"})
public class MybatisPlusConfig {

}
