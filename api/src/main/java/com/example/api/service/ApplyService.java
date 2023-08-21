package com.example.api.service;

import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.CouponRepository;
import com.example.api.repository.RedisCouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    private final CouponRepository couponRepository;
    private final RedisCouponRepository redisCouponRepository;
    private final CouponCreateProducer couponCreateProducer;

    public ApplyService(CouponRepository couponRepository, RedisCouponRepository redisCouponRepository, CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.redisCouponRepository = redisCouponRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void apply(Long userId) {
        Long apply = redisCouponRepository.add(userId);
        if(apply != 1) {
            return;
        }

        Long count = redisCouponRepository.increment();
        if(count > 100) {
            return;
        }

//        couponRepository.save(new Coupon(userId));
        couponCreateProducer.create(userId);
    }
}
