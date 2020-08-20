package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.Url;

public interface UrlService extends IService<Url> {


    String getShort(String longUrl);

    void insertUser();

    String selecShort(String longUrl);
}
