package cn.czboy.controller;

import cn.czboy.service.AopLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class AopLogController {

    @Autowired
    private AopLogService aopLogService;

    @RequestMapping("/aopLog")
    public void test() {
        aopLogService.aopLog();
    }
}
