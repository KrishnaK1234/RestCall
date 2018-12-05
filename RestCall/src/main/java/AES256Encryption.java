//
//import java.security.spec.KeySpec;
//import java.util.Base64;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.PBEKeySpec;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
///**
// * <h1>AES256Encryption</h1>
// * <p>
// * This class performs the AES encryption/decryption of the given strings.
// * 
// * </p>
// *
// * @version 0.1
// * @since 26 September, 2018
// */
//@Component
//@ConfigurationProperties
//public class AES256Encryption {
//
//	@Value("${encryption.secret:#{null}}")
//	private String secret;
//	@Value("${encryption.salt:#{null}}")
//	private String salt;
//
//	private static final Logger log = LoggerFactory.getLogger(AES256Encryption.class);
//
//	private static final String KEY_FACTRORY_INSTANCE = "PBKDF2WithHmacSHA256";
//	private static final String CIPHER_INSTANCE = "AES/CBC/PKCS5Padding";
//	private static final String ENCODING_TYPE = "UTF-8";
//	private static final String ENCRYPTION_ALGORITHM = "AES";
//	private static final String REQUIRED_PROPS_MISSING = "Required properties missing for encryption";
//
//	/**
//	 * Method responsible to encrypt the given string.
//	 * 
//	 * @param strToEncrypt
//	 * @return
//	 * @throws Exception
//	 */
//	// I don't know every much about AES encryption. So if you have tested this
//	// and it works, its fine with me. I will may be later look into this
//	public String encrypt(String strToEncrypt) throws Exception {
//		try {
//			if (secret == null || salt == null || strToEncrypt == null) {
//				throw new IllegalArgumentException(REQUIRED_PROPS_MISSING);
//			}
//			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//			IvParameterSpec ivspec = new IvParameterSpec(iv);
//			SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_FACTRORY_INSTANCE);
//			KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
//			SecretKey tmp = factory.generateSecret(spec);
//			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), ENCRYPTION_ALGORITHM);
//			Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
//			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
//			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(ENCODING_TYPE)));
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			throw new Exception(e);
//		}
//	}
//
//	/**
//	 * Method responsible to decrypt the given string.
//	 * 
//	 * @param strToDecrypt
//	 * @return
//	 * @throws Exception
//	 */
//	public String decrypt(String strToDecrypt) throws Exception {
//		try {
//			if (secret == null || salt == null || strToDecrypt == null) {
//				throw new IllegalArgumentException(REQUIRED_PROPS_MISSING);
//			}
//			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//			IvParameterSpec ivspec = new IvParameterSpec(iv);
//			SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_FACTRORY_INSTANCE);
//			KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
//			SecretKey tmp = factory.generateSecret(spec);
//			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), ENCRYPTION_ALGORITHM);
//			Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
//			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
//			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			throw new Exception(e);
//		}
//	}
//
//	/**
//	 * getter for secret
//	 * 
//	 * @return
//	 */
//	public String getSecret() {
//		return secret;
//	}
//
//	/**
//	 * setter for secret
//	 * 
//	 * @param secret
//	 */
//	public void setSecret(String secret) {
//		this.secret = secret;
//	}
//
//	/**
//	 * getter for salt
//	 * 
//	 * @return
//	 */
//	public String getSalt() {
//		return salt;
//	}
//
//	/**
//	 * setter for salt
//	 * 
//	 * @param salt
//	 */
//	public void setSalt(String salt) {
//		this.salt = salt;
//	}
//}
