package com.es.core.order;

public interface SecretIdDao {
    Integer getSecretByReal(Long realId);
    Long getRealBySecret(Integer secretId);
}
