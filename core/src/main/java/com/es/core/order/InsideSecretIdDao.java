package com.es.core.order;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InsideSecretIdDao implements SecretIdDao{

    Map<Long, Integer> secretIdByReal = new HashMap<>();

    @Override
    public Integer getSecretByReal(Long realId) {
        Integer secret = secretIdByReal.get(realId);
        if(secret == null){
            secret = new Long((realId*6563)).hashCode();
            secretIdByReal.put(realId, secret);
        }
        return secret;
    }

    @Override
    public Long getRealBySecret(Integer secretId) {
        for(Map.Entry<Long, Integer> e: secretIdByReal.entrySet()){
            if(e.getValue().equals(secretId)){
                return e.getKey();
            }
        }
        return null;
    }
}
