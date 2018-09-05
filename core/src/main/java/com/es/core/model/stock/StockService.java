package com.es.core.model.stock;

public interface StockService {
    boolean checkAdd(Long phoneId, Long quantity);
    boolean checkUpdate(Long phoneId, Long quantity);
    void removeFromStock(Long phoneId, Long quantity);
}
