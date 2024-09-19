//package pub.wii.cook.springboot.utils;
//
//import com.google.gson.Gson;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.Validate;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.Cipher;
//import java.io.ByteArrayOutputStream;
//import java.nio.charset.StandardCharsets;
//import java.security.KeyFactory;
//import java.security.MessageDigest;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Slf4j
//public class RSAUtilB {
//    public static PublicKey getPublicKey(String publicKey) throws Exception {
//        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePublic(keySpec);
//    }
//
//    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
//        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePrivate(keySpec);
//    }
//
//    public static byte[] encryptNew(byte[] content, PublicKey publicKey) throws Exception {
//        int MAX_ENCRYPT_BLOCK = 245;
//        Cipher cipher = Cipher.getInstance("RSA");// java默认"RSA"="RSA/ECB/PKCS1Padding"
//
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        int inputLen = content.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
//                cache = cipher.doFinal(content, offSet, MAX_ENCRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(content, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_ENCRYPT_BLOCK;
//        }
//        byte[] encryptedData = out.toByteArray();
//        out.close();
//        return encryptedData;
//    }
//
//    public static byte[] decryptNew(byte[] content, PrivateKey privateKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        int MAX_ENCRYPT_BLOCK = 256;
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        int inputLen = content.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
//                cache = cipher.doFinal(content, offSet, MAX_ENCRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(content, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_ENCRYPT_BLOCK;
//        }
//        byte[] encryptedData = out.toByteArray();
//        out.close();
//        return encryptedData;
//    }
//
//    @SneakyThrows
//    public static String encode(String msg, String pubK) {
//        log.info("public key = {}, msg = {}", pubK, msg);
//        PublicKey publicKey = RSAUtilB.getPublicKey(pubK);
//        byte[] encodeSecretStr = RSAUtilB.encryptNew(msg.getBytes(StandardCharsets.UTF_8), publicKey);
//        // 对加密后的数据 encodeSecretStr 进行签名
//        MessageDigest msgDst = MessageDigest.getInstance("SHA-256");
//        msgDst.update(encodeSecretStr);
//        byte[] dst = msgDst.digest();
//        byte[] merge = new byte[encodeSecretStr.length + dst.length];
//        System.arraycopy(dst, 0, merge, 0, dst.length);
//        System.arraycopy(encodeSecretStr, 0, merge, dst.length, encodeSecretStr.length);
//        return Base64.getEncoder().encodeToString(merge);
//    }
//
//    @SneakyThrows
//    public static String decode(String msg, String priK) {
//        log.info("private key = {}, msg = {}", priK, msg);
//        return rSADecodeSecretNewV2(msg, priK);
//    }
//
//    @SneakyThrows
//    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("Price", "1.00");
//        map.put("No", "12010233");
//        String encodeMsg = new Gson().toJson(map);
//
//        String prk = "";
//        String pubK = "";
//        String v = encode(encodeMsg, pubK);
//        System.out.println(v);
//        System.out.println(decode(v, prk));
//
//        PublicKey publicKey = RSAUtilB.getPublicKey(pubK);
//        byte[] encodeSecretStr = RSAUtilB.encryptNew(encodeMsg.getBytes(StandardCharsets.UTF_8), publicKey);
////        // 对加密后的数据 encodeSecretStr 进行签名
//        MessageDigest msgDst = MessageDigest.getInstance("SHA-256");
//        msgDst.update(encodeSecretStr);
//        byte[] dst = msgDst.digest();
//        byte[] merge = new byte[encodeSecretStr.length + dst.length];
//        System.arraycopy(dst, 0, merge, 0, dst.length);
//        System.arraycopy(encodeSecretStr, 0, merge, dst.length, encodeSecretStr.length);
//
//        System.out.println(new String(merge));
//        String encrypt = Base64.getEncoder().encodeToString(merge);
//        System.out.println(rSADecodeSecretNew(encrypt, prk));
//    }
//
//    public static Map<String, String> rSADecodeSecretNew(String mergedBase64, String pk) throws Exception {
//        HashMap<String, String> rtnMap = new HashMap<>();
//        // Base64转码
//        byte[] merge = Base64.getDecoder().decode(mergedBase64);
//        // 声明定义密文数组
//        byte[] encodeSecretStr_base64_bytes = new byte[merge.length - 32];
//        // 声明签名数组
//        byte[] dstBase64_bytes = new byte[32];
//        // 获得签名数组
//        System.arraycopy(merge, 0, dstBase64_bytes, 0, 32);
//        // 获得密文数组
//        System.arraycopy(merge, 32, encodeSecretStr_base64_bytes, 0, merge.length - 32);
//        // 对签名进行验证
//        MessageDigest dst_key = MessageDigest.getInstance("SHA-256");
//        dst_key.update(encodeSecretStr_base64_bytes);
//        if (MessageDigest.isEqual(dstBase64_bytes, dst_key.digest())) {
//            rtnMap.put("result", "1");// 1表示验证成功 未做修改
//        } else {
//            rtnMap.put("result", "0");// 0表示验证失败
//        }
//
//        // 还原加密字符串
//        PrivateKey privateKey = RSAUtilB.getPrivateKey(pk);
//        byte[] data = RSAUtilB.decryptNew(encodeSecretStr_base64_bytes, privateKey);
//        rtnMap.put("decodeMsg", new String(data, StandardCharsets.UTF_8));
//        return rtnMap;
//    }
//
//    public static String rSADecodeSecretNewV2(String mergedBase64, String pk) throws Exception {
//        // Base64转码
//        byte[] merge = Base64.getDecoder().decode(mergedBase64);
//        // 声明定义密文数组
//        byte[] encodeSecretStrBase64Bytes = new byte[merge.length - 32];
//        // 声明签名数组
//        byte[] dstBase64_bytes = new byte[32];
//        // 获得签名数组
//        System.arraycopy(merge, 0, dstBase64_bytes, 0, 32);
//        // 获得密文数组
//        System.arraycopy(merge, 32, encodeSecretStrBase64Bytes, 0, merge.length - 32);
//        // 对签名进行验证
//        MessageDigest dstKey = MessageDigest.getInstance("SHA-256");
//        dstKey.update(encodeSecretStrBase64Bytes);
//        Validate.isTrue(MessageDigest.isEqual(dstBase64_bytes, dstKey.digest()), "decode failed");
//
//        // 还原加密字符串
//        PrivateKey privateKey = RSAUtilB.getPrivateKey(pk);
//        byte[] data = RSAUtilB.decryptNew(encodeSecretStrBase64Bytes, privateKey);
//        return new String(data, StandardCharsets.UTF_8);
//    }
//}
