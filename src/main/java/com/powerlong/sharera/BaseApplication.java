package com.powerlong.sharera;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author fightingliu 日志接口请求，需要部署
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.powerlong.sharera")
@EnableScheduling
public class BaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
}
