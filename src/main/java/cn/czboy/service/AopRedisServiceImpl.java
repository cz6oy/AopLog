package cn.czboy.service;

import org.springframework.stereotype.Service;

@Service
public class AopRedisServiceImpl implements  AopRedisService{

    @Override
    public void aopRedis() {
        System.out.println("我是主方法....");
    }
}
