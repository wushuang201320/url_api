package com.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entity.Url;
import com.mapper.UrlMapper;
import com.utils.MD5Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class UrlServiceImpl extends ServiceImpl<UrlMapper, Url> implements UrlService {


    public static String[] chars = { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
            "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
            "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
            "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
            "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
            "U" , "V" , "W" , "X" , "Y" , "Z"
    };

    @Override
    public String getShort(String longUrl) {
        String md5 = MD5Encrypt.md5(longUrl);
        String shortUrl = build(md5);
        Url Url = new Url();
        Url.setLongUrl(longUrl);
        Url.setShortUrl(shortUrl);
        Boolean  bool= this.baseMapper.inserUrl(Url);
        if(bool){
            return shortUrl;
        }else{
           return "保存失败,生成短链接失败";
        }
    }

    @Override
    public void insertUser() {

    }


    @Override
    public String selecShort(String longUrl) {
        String selectLongUrl  = this.baseMapper.selecShort(longUrl);
        return selectLongUrl;
    }


    public static String build(String  md5){
        //将32个字符的md5码分成4段处理，每段8个字符
        for (int i = 0; i < 4 ; i++) {
            int offset = i * 8 ;
            String sub = md5.substring(offset, offset + 8) ;
            long sub16 = Long.parseLong(sub , 16) ; //将sub当作一个16进制的数，转成long
            // & 0X3FFFFFFF，去掉最前面的2位，只留下30位
            sub16 &= 0X3FFFFFFF ;
            StringBuilder sb = new StringBuilder() ;
            //将剩下的30位分6段处理，每段5位
            for (int j = 0; j < 6 ; j++) {
                //得到一个 <= 61的数字
                long t = sub16 & 0x0000003D;
                sb.append(chars[(int) t]);
                sub16 >>= 5;  //将sub16右移5位
            }
            //第三个截取前6位中的前5位
            if(i == 3 ){
                return sb.toString().substring(0,5);
            }
        }
        return null;
    }

    /**
     * 获取url
     * @param longUrl
     * @param shortUrl
     * @return
     */
    public Url getUrl(String longUrl,String shortUrl){
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);
        return url;
    }

}
