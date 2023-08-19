package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponRepository;
import com.example.api.repository.RedisCouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    private final CouponRepository couponRepository;
    private final RedisCouponRepository redisCouponRepository;

    public ApplyService(CouponRepository couponRepository, RedisCouponRepository redisCouponRepository) {
        this.couponRepository = couponRepository;
        this.redisCouponRepository = redisCouponRepository;
    }

    public void apply(Long userId) {
        long count = redisCouponRepository.increment();

        if(count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
