/*
 * package com.wcs.user.Security;
 * 
 * import java.security.InvalidKeyException; import java.security.KeyPair;
 * import java.security.NoSuchAlgorithmException; import
 * java.security.NoSuchProviderException; import java.security.PrivateKey;
 * import java.security.PublicKey; import
 * java.security.spec.InvalidKeySpecException;
 * 
 * import javax.crypto.BadPaddingException; import
 * javax.crypto.IllegalBlockSizeException; import
 * javax.crypto.NoSuchPaddingException;
 * 
 * import org.junit.Assert; import org.junit.Test; import
 * org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 * 
 * //import com.jsgao.Application; import
 * com.wcs.user.WcsUserManagementApplication;
 * 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @SpringApplicationConfiguration(classes = WcsUserManagementApplication.class)
 * public class RSACipherUtilityTest {
 * 
 * @Autowired private RSACipherUtilityTest cipherUtility;
 * 
 * @Test public void cipherTest() {
 * 
 * String plain = "Happy day!";
 * 
 * // Initialization of key pair for encryption and decryption. KeyPair keyPair
 * = cipherUtility.getKeyPair();
 * 
 * // try {a // Get public key from the key pair. PublicKey pubKey =
 * keyPair.getPublic();
 * 
 * // Get private key from the key pair. PrivateKey privKey =
 * keyPair.getPrivate();
 * 
 * // Try to encode public key as a string. String pubKeyStr =
 * cipherUtility.encodeKey(pubKey); // Assertion of 'pubKey' and the public key
 * decoded by 'pubKeyStr'. Assert.assertEquals(pubKey,
 * cipherUtility.decodePublicKey(pubKeyStr));
 * 
 * // Try to encode private key as a string. String privKeyStr =
 * cipherUtility.encodeKey(privKey); // Assertion of 'privKey' and the private
 * key decoded by 'privKeyStr'. Assert.assertEquals(privKey,
 * cipherUtility.decodePrivateKey(privKeyStr));
 * 
 * // Encrypt plain as a cipher. String cipherContent =
 * cipherUtility.encrypt(plain, pubKey); // Decrypt cipher to original plain.
 * String decryptResult = cipherUtility.decrypt(cipherContent, privKey); //
 * Assertion of 'plain' and 'decryptResult'. Assert.assertEquals(plain,
 * decryptResult); } catch (InvalidKeyException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } catch (NoSuchAlgorithmException e) { //
 * TODO Auto-generated catch block e.printStackTrace(); } catch
 * (NoSuchProviderException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } catch (NoSuchPaddingException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } catch
 * (IllegalBlockSizeException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } catch (BadPaddingException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } catch (InvalidKeySpecException e) { //
 * TODO Auto-generated catch block e.printStackTrace(); }
 * 
 * }
 * 
 * }
 */