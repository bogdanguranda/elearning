package thecerealkillers.elearning.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public final class PasswordExpert {

	private static final int SALT_BYTE_SIZE = 32;
	private static final String HASH_FUNCTION = "SHA-512";
	
	private PasswordExpert () {}

	/**
	 * Note, this function will not check if in the database already exists a user with this username !!!
	 * @return true if userName string is safe, false otherwise
	 */
	public static Boolean validateUserName (String userName) {
		///TODO
		return true;
	}

	/**
	 * A password is considered safe if :
	 * 		-
	 *  	-
	 *  	-
	 * @return true if the password respects the required specifications, false otherwise
	 */
	public static Boolean validatePassword (String password) {
		///TODO
		return true;
	}

	/**
	 * This function hashes the salted password.
	 * @return instance of PasswordInfo that will store the password, salt and the hash of the salted password
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static PasswordInfo newPassword (String password)
			throws NoSuchAlgorithmException, NoSuchProviderException
	{
		PasswordInfo passInfo = new PasswordInfo (password);
		
		String salt = generateSalt ();
		String hash = createHash (passInfo.getPassword (), salt);
		
		passInfo.setSalt (salt);
		passInfo.setHash (hash);
		
		return passInfo;
	}

	/**
	 * Returns a new random salt
	 * @return salt
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 */
	private static String generateSalt () 
			throws NoSuchAlgorithmException, NoSuchProviderException 
	{
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] saltByte = new byte [SALT_BYTE_SIZE];
        String salt = "";
        
        sr.nextBytes(saltByte);

		for (byte b : saltByte) {
        	salt += Integer.toString((b & 0xff) + 0x100, 16).substring(1);
        }

        return salt;
    }
	
	/**
	 * Creates the hash for the password and salt
	 * @return hash of salted password
	 * @throws NoSuchAlgorithmException
	 */
	private static String createHash (String password, String salt) 
			throws NoSuchAlgorithmException	
	{
		MessageDigest md = MessageDigest.getInstance (HASH_FUNCTION);
		String saltedPasswd = password + salt;
		byte[] bytes = md.digest (saltedPasswd.getBytes());
        StringBuilder sb = new StringBuilder();
        
        for (byte b : bytes) {
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
        
		return sb.toString();
	}
	
	/**
	 * Returns true if correctHash is equal with the hash of password + salt
	 * @param password	=	password that will be checked using user's salt and hash
	 * @param salt	=	user's salt
	 * @param correctHash	=	user's correct hash
	 * @return true if the password is correct, false otherwise
	 * @throws NoSuchAlgorithmException
	 */
	public static Boolean verifyPassword (String password, String salt, String correctHash) 
			throws NoSuchAlgorithmException
	{
		String verifiedHash = createHash (password, salt);
		
		return (correctHash.compareTo(verifiedHash) == 0);
	}
	
}
