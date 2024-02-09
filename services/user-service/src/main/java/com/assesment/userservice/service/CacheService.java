package com.assesment.userservice.service;

public interface CacheService {
    Object getDataByKey(String key);
    Object setData(String key,Object data);
}
