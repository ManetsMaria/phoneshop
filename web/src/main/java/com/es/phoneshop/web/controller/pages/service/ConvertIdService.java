package com.es.phoneshop.web.controller.pages.service;

import com.es.core.order.SecretIdDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ConvertIdService {
    @Resource
    private SecretIdDao secretIdDao;

    public Long getRealBySecret(Integer secret){
        if(secret == null){
            return null;
        }
        return secretIdDao.getRealBySecret(secret);
    }

    public Integer getSecretByReal(Long real){
        if(real == null){
            return null;
        }
        return secretIdDao.getSecretByReal(real);
    }
}
