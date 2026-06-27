package com.hmdp;

import com.hmdp.entity.Shop;
import com.hmdp.service.impl.ShopServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class HmDianPingApplicationTests {
    @Autowired  // 或 @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ShopServiceImpl shopService;

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

    @Test
    public void testRedis() {
        shopService.saveShop2Redis(1L, 10L);
    }

}
