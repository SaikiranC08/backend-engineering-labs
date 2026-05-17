package com.saikiran.redis_basics.controller;


import com.saikiran.redis_basics.service.RedisService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/redis")
public class RedisController {



    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/save")
    public String saveData(@RequestParam String key,
                           @RequestParam String value) {

        redisService.saveData(key, value);

        return "Data saved successfully";
    }

    @GetMapping("/get")
    public Object getData(@RequestParam String key) {
        return redisService.getData(key);
    }
}