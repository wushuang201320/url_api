package com.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.Url;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;



public interface UrlMapper extends BaseMapper<Url>{


    @Insert("insert into url(shortUrl,longUrl) values(#{shortUrl},#{longUrl})")
    boolean inserUrl(Url user);

    @Select("select shortUrl from url where longUrl=#{longUrl}")
    String selecShort(String longUrl);

}
