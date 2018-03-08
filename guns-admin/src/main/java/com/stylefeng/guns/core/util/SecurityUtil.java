package com.stylefeng.guns.core.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

/**
 * com.stylefeng.guns.rest.common.util
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018/3/8 14:35
 * <p>
 * Company: wuxc
 * <p>
 *
 * @author apple1981@126.com
 * @version 1.0.0
 */
public class SecurityUtil {

    private static String key="0CoJ5m6Qyw8W8jue";

    private static String iv = "0102030405060708";

    /**
     * 加密字符串
     * @param content 内容
     * @return
     */
    public static String encrypt(String content){
        AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        return  aes.encryptHex(content);
    }

    /**
     * 解密字符串
     * @param encrypt 加密内容
     * @return
     */
    public static String decrypt(String encrypt){
        AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        return aes.decryptStr(encrypt, CharsetUtil.CHARSET_UTF_8);
    }

    public static void main(String[] args) {

        String conent="dfsfs测试内容fff";
        String encrypt = SecurityUtil.encrypt(conent);
        System.out.println("加密数据：" + SecurityUtil.encrypt(conent));
        //解密为字符串
        System.out.println("解密数据：" +  SecurityUtil.decrypt(encrypt));
    }


}
