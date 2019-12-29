package cn.czboy.service;

import cn.czboy.annotation.Log1;
import org.springframework.stereotype.Service;

@Service
public class AopLogServiceImpl implements AopLogService {

    @Override
    @Log1
    public void aopLog() {
        System.out.println("我是方法....");
    }
}
