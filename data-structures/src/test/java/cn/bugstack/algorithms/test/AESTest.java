package cn.bugstack.algorithms.test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author huangnenghe
 * @date 2022-08-30 9:28
 */
public class AESTest {
    public static void main(String[] args) {
        String sig = "{\"userName\":\"secadmin\"}";
        System.out.println(AESEncrypt(sig, "ADMINLOGINSSOKEY"));
    }


    public static String AESEncrypt(String encryptStr, String sKey) {
        try {
            byte[] raw = sKey.getBytes("utf-8");
            //定义加密算法，这里用AES算法加密skey，得到加密的密钥
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            // 实例化Cipher对象，得到AES/ECB/PKCS5Padding转换的对象，"算法/模式/补码方式,模式和补码方式可以不写，因为默认就为ECB和PKCS5Padding"
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //用密钥初始化 Cipher对象为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] encrypted = cipher.doFinal(encryptStr.getBytes("utf-8"));
            // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
            String encode = new Base64().encode(encrypted);
            return encode;
        } catch (Exception e) {
            return null;
        }
    }


}
