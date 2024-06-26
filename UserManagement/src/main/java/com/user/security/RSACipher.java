package com.user.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSACipher {
	
	public  RSACipher() {
		super();
		initFromStrings();
		
		
	}

	private PublicKey publicKey;
	private PrivateKey privateKey;

	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCb7vuXYMLbHD1O39CFem+aQjre/0LoxkWw6UMCdWv/WSylN7FmLYrWzNFY6b4Cf9bc5xDq85e1i1Tn1QgPOb3w+dCOuqPOZUBd99feMJC6uQ3qzbTz7O52FmhTHePPgnSlb6JOFwD35g3xoUF8I0Gz/YQZXxeO1LtispSWy8xZhQIDAQAB";
	private static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJvu+5dgwtscPU7f0IV6b5pCOt7/QujGRbDpQwJ1a/9ZLKU3sWYtitbM0VjpvgJ/1tznEOrzl7WLVOfVCA85vfD50I66o85lQF33194wkLq5DerNtPPs7nYWaFMd48+CdKVvok4XAPfmDfGhQXwjQbP9hBlfF47Uu2KylJbLzFmFAgMBAAECgYASPRo1Yw53lW6AVsJrkl4SY3PCWIYJdwN4xVfepkBbDiGgM4ru4h3OsQc9iJ1+aliF3wsGCDby95iXQz4dlcnnAjbsjl+Y/XY7ZDzcvBVRdebXVHOQgTBafiUwsVHb/0XyhjKzmO6CQBVfUabEyfleHp0uM4MviIJaPlH88DMRUwJBAN52nzz/ckfQn825FDDfwJSRN1/xkSqRkcFZ/v34yxw2dusyJgnktoWByeFCBrZ71udgds5OqzBQTGQh4feYjj8CQQCzcNHXcJVSLb1hd1+3dAKzjBJ7V6aHWy7Bkkh8Vz6Jvr9z6oLNK2C7tyxOzEOW4WmaNj4z9pc1R2lOsNkw+i87AkEAwowYa/61EUrIRd/CYY972jE4rQYTvpd5UQ8EKVTHQ0/6JWtAyswiNpUvWHgmaLXaVZQNshQoL2M5uyPKen4GfwJBAIh4bm+OhVIpzVV6R4arqX5KMqgyAkoBdC7uBUjjxYFXXB5Zpb9KlVhOuFj5ixIOP5V0WZyFl3yC4jlh0N0p+Y8CQQCK4ZMt/MJ3ZbqUbuAtUjCsQR/4moTX19xQ4mkcYgxLkiZGqccGdkDHREiyHOz9u93E94IowBYolawwgDcdKgAI";


    public void init(){
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (Exception ignored) {
        }
    }

    public void initFromStrings(){
        try{
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(PUBLIC_KEY));
            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(PRIVATE_KEY));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(keySpecPublic);
            privateKey = keyFactory.generatePrivate(keySpecPrivate);
        }catch (Exception ignored){}
    }


    public void printKeys(){
        System.err.println("Public key\n"+ encode(publicKey.getEncoded()));
        System.err.println("Private key\n"+ encode(privateKey.getEncoded()));
    }

    public String encrypt(String message) throws Exception {
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return encode(encryptedBytes);
    }

    private static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
    private static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public String decrypt(String encryptedMessage) throws Exception {
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage, "UTF8");
    }
	
	
}

