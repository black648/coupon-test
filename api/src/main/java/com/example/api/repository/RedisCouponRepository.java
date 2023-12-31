package com.example.api.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisCouponRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public RedisCouponRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // incr 사용
    public Long increment() {
        return redisTemplate
                .opsForValue()
                .increment("coupon_count");
    }

    // redis Set을 이용한 쿠폰 발급
    public Long add(Long userId) {
        return redisTemplate
                .opsForSet()
                .add("applied_user", userId.toString());
    }
}
