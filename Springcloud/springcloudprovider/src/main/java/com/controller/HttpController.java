package com.controller;



import com.entity.Url;
import com.service.UrlService;
import com.service.UrlServiceImpl;
import com.utils.ResMsg;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user")
public class HttpController {

    @Autowired
    private UrlService userService;

    @Autowired
    private UrlServiceImpl urlServiceImpl;


    @RequestMapping(value = "/getShortUrl")
    public ResMsg getShortUrl(String longUrl){
        Url url = null;
        String beforeShortUrl = this.userService.selecShort(longUrl);
        if(StringUtils.isNotEmpty(beforeShortUrl)){
            url = urlServiceImpl.getUrl(longUrl,beforeShortUrl);
            return ResMsg.success(url);
        }else{
            String shortUrl=this.userService.getShort(longUrl);
            if(StringUtils.isEmpty(shortUrl)){
                return ResMsg.errorMsg("失败");
            }
            url = urlServiceImpl.getUrl(longUrl,shortUrl);
            return ResMsg.success(url);
        }

    }



}
