package com.wcs.user.service;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcs.user.model.Deep_user;
import com.wcs.user.security.RSACipher;
import com.wcs.user.security.RSACipherUtility;
import com.wcs.user.security.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private  UserRepository userRepository;

	private RSACipher rsaCipher = new RSACipher();
	

//	KeyPair keyPair = rsaCipherUtility.getKeyPair();
//	 Get public key from the key pair.
//	PublicKey pubKey = keyPair.getPublic();
//
//	// Get private key from the key pair.
//	PrivateKey privKey = keyPair.getPrivate();

	@Override
	public void createUser(Deep_user user) {
		
		try {
			user.setPassword(rsaCipher.encrypt(user.getPassword()));
			System.out.println(user);
			userRepository.save(user);
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Deep_user getUserByUsername(String username) {
		return userRepository.findByUserName(username);
	}

	public boolean authenticateUser(Deep_user user) {
		Deep_user user_db = userRepository.findByUserName(user.getUserName());
		try {
			if (user != null && rsaCipher.decrypt(user_db.getPassword()).equals(user.getPassword())) {
				return true;
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	

}
