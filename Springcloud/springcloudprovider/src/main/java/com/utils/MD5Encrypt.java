package com.utils;

import java.security.MessageDigest;


/**
 * @author :wushuang
 * @Description: MD5加密工具类
 * @ProjectName: own_crm
 * @Package: com.flow.framework.common.utils.MD5Encrypt
 * @Version: 1.0
 */
public class MD5Encrypt {
    /**
     * 加密密匙.
     */
    public static String ENCRYPT_KEY = "md5Util";
    /**
     * @return  结果
     * @param  s 参数
     */
    public final static String md516(String s) {
        byte[] btInput = s.getBytes();
        return MD5Encrypt.md5(btInput,false);
    }
    /**
     * @return  结果
     * @param  s 参数
     */
    public final static String md532(String s) {
        byte[] btInput = s.getBytes();
        return MD5Encrypt.md5(btInput, true);
    }
    /**
     * @return  结果
     * @param  file 参数
     * @throws DataFormatException 异常
     */

 /*   public static String getMd5ByFile(File file) throws Exception {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }*/

    /**
     * @return  结果
     * @param  btInput 数组
     *  @param  is32 参数
     */
    public final static String md5(byte[] btInput, boolean is32) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return is32 ? (new String(str)) : (new String(str).substring(8, 24));

        } catch (Exception e) {
            return null;
        }
    }
    /**
     * @return  结果
     * @param  source 参数
     */
    public final static String md5(String source) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            // 使用MD5创建MessageDigest对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            byte[] strTemp = source.getBytes();
            mdInst.update(strTemp);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }




}
