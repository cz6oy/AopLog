package cn.czboy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="cn.czboy.dao")

public class AopRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopRedisApplication.class, args);
    }

}
