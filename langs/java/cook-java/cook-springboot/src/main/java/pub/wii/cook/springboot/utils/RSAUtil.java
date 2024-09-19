package pub.wii.cook.springboot.utils;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {

    private final static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOhQ81fJEKI/b0+iKtK0/WN7wcMJKuGL5QhzuRDyFLCADbKPkB7uGKfkQ1QDcntjZ8k4+mx2oGBKF5E1vSB+bD7SP7fkSgDmdCzGLSJX04Q9H95xuEfFsTXwW8dGh4uQDFt/dTIY2WAx8RnGhiJFKq6y9mj+tcofnzLbQNfy2PoQIDAQAB";
    private final static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI6FDzV8kQoj9vT6Iq0rT9Y3vBwwkq4YvlCHO5EPIUsIANso+QHu4Yp+RDVANye2NnyTj6bHagYEoXkTW9IH5sPtI/t+RKAOZ0LMYtIlfThD0f3nG4R8WxNfBbx0aHi5AMW391MhjZYDHxGcaGIkUqrrL2aP61yh+fMttA1/LY+hAgMBAAECgYBuMZNA17+NB6G6aGzHV+WyzAU2Bphi497ChM0Zq4kial2/Fj7xr7HTUy2Jvszmd4xJZg579VOUs5/l7YHhMxrI2sUadaenkdJ/LmdHICRT4BAFdDnM78UsY1/YgtoA06r63ZkaE6PJ2UPMCgcYMqV5OkAFwt8oTDSGlBb4EZuUAQJBAMbR+tolF96tHPb96BKbowIytRQZpC3KMutnzY74davY4BvAnvzoUTs0d89Xi+1+YxmI0UahejGdyMEWOryNJYkCQQC3gf3B8Kmaic3hz1ghslsdY5n1sE8piJH/9owvToc54MVQc9sfFC9f+e6v8yjblHFeaoYTL6NUz9MUmeHAgatZAkB+VGnaNnuGR+UBo6/UMwROnz2jue8yESptnZVlZMYQHUu5FplvBYan4dzG6E/G5em+Dcs739qusB0hYyiLKfxRAkAiGKweqenJhgtUBqOYdzxIxKXpqZ272N1P0u6PJ6cmkOX4od438xcuXREFbkfMLNO3uFE7JWHSs17D+CejDjTZAkAWlJVrwvcgdPDun/xF2FK8YoonJFPJxFD/jrVPuoZ+HxYa9sytwuaAU8jTv8WDF4er1ZO4N3TENE+SedxuH0zW";

    @SneakyThrows
    public static PublicKey getPublicKey(String base64PublicKey) {
        PublicKey publicKey;
        X509EncodedKeySpec keySpec =
                new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    @SneakyThrows
    public static PrivateKey getPrivateKey(String base64PrivateKey) {
        PKCS8EncodedKeySpec keySpec =
                new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    @SneakyThrows
    public static String encrypt(String data, String publicKey) {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    @SneakyThrows
    public static String decrypt(byte[] data, PrivateKey privateKey) {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    public static String decrypt(String data, String base64PrivateKey) {
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }

    public static void main(String[] args) {
        String encryptedString = encrypt("hello world", publicKey);
        System.out.println(encryptedString);
        String decryptedString = RSAUtil.decrypt(encryptedString, privateKey);
        System.out.println(decryptedString);


        // node data
        System.out.println(RSAUtil.decrypt("iQMK9U8wYsLdhssRVRVY0CA40SdM3PEP7KhuP74ICPA7AH0ckUw5Rq9C8RKAIBh0fO8hIYC+kPN9xUCKNUlFGy09xtBEOgRybg8JZZC8mEjeA0hHMbQePI4fxQa74IWYY646ihj7KCtaE55yE6G8yV7hRPFyjdPJieXEKhSJsmM=",
                privateKey));
        String jsData = "hOv454LZNZhPoS2nbtnH7S1phhkdVVX0NtF7xx37H4tNKelTguQ0i/obZXBGVRC7FoP0CAP7lrqo/VlT276D9kLXHUGSl/9FW1Q4fNcj4iMZedjcQJaZNOSZ4Vlti/9cXUeuUXaGG6vS3V49EfMy/FELFy+zpbijcnrIAar6uh4=";
        System.out.println(RSAUtil.decrypt(jsData, privateKey));
    }
}
