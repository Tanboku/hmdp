package com.hmdp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
@SpringBootTest
class HmDianPingApplicationTests {
    @Autowired  // 或 @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    // 在 sendCode 或 login 方法开头添加
    public void testRedisConnection() {
        try {
            stringRedisTemplate.opsForValue().set("test:conn", "ok");
            String val = stringRedisTemplate.opsForValue().get("test:conn");
            log.info("Redis 连接测试结果：{}", val);  // 应打印 "ok"
        } catch (Exception e) {
            log.error("Redis 连接失败", e);
        }
    }

}
