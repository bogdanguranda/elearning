package thecerealkillers.elearning.utilities;

import thecerealkillers.elearning.exceptions.PasswordExpertException;
import thecerealkillers.elearning.model.PasswordInfo;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.security.SecureRandom;


/**
 * Created by Dani
 */
public final class PasswordExpert {

    private static final int SALT_BYTE_SIZE = 32;
    private static final String HASH_FUNCTION = "SHA-512";

    private PasswordExpert() {
    }


    ///=========================================Public methods======================================================

    /**
     * This function hashes the salted password.
     *
     * @return instance of PasswordInfo that will store the password, salt and the hash of the salted password
     * @throws PasswordExpertException
     */
    public static PasswordInfo newPassword(String password, String salt) throws PasswordExpertException {
        PasswordInfo passInfo = new PasswordInfo(password);

        String hash = createHash(password, salt);

        passInfo.setSalt(salt);
        passInfo.setHash(hash);

        return passInfo;
    }

    /**
     * This function hashes the salted password.
     *
     * @return instance of PasswordInfo that will store the password, salt and the hash of the salted password
     * @throws PasswordExpertException
     */
    public static PasswordInfo newPassword(String password) throws PasswordExpertException {
        PasswordInfo passInfo = new PasswordInfo(password);

        String salt = generateSalt();
        String hash = createHash(password, salt);

        passInfo.setSalt(salt);
        passInfo.setHash(hash);

        return passInfo;
    }

    /**
     * Returns a random String of 20 elements to be used as a password using salt generator method.
     *
     * @return string with length equal to 20 to be used as a password
     * @throws PasswordExpertException
     */
    public static String generatePassword() throws PasswordExpertException {
        String newPassword = generateSalt();

        return newPassword.substring(0, 20);
    }

    /**
     * Returns true if correctHash is equal with the hash of password + salt
     *
     * @param password    =	password that will be checked using user's salt and hash
     * @param salt        =	user's salt
     * @param correctHash =	user's correct hash
     * @return true if the password is correct, false otherwise
     * @throws PasswordExpertException
     */
    public static Boolean verifyPassword(String password, String salt, String correctHash) throws PasswordExpertException {
        String verifiedHash = createHash(password, salt);

        return (correctHash.compareTo(verifiedHash) == 0);
    }


    ///========================================Private methods======================================================

    /**
     * Returns a new random salt
     *
     * @return salt
     * @throws PasswordExpertException
     */
    private static String generateSalt() throws PasswordExpertException {
        try {
            SecureRandom sr;
            sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] saltByte = new byte[SALT_BYTE_SIZE];
            String salt = "";

            sr.nextBytes(saltByte);

            for (byte b : saltByte) {
                salt += Integer.toString((b & 0xff) + 0x100, 16).substring(1);
            }

            return salt;
        } catch (NoSuchAlgorithmException exception) {
            throw new PasswordExpertException(PasswordExpertException.FAILED_SALT + exception.getMessage());
        }
    }

    /**
     * Creates the hash for the password and salt
     *
     * @return hash of salted password
     * @throws PasswordExpertException
     */
    private static String createHash(String password, String salt) throws PasswordExpertException {
        try {
            MessageDigest md;

            md = MessageDigest.getInstance(HASH_FUNCTION);

            String saltedPassword = password + salt;
            byte[] bytes = md.digest(saltedPassword.getBytes());
            StringBuilder sb = new StringBuilder();

            System.out.println(saltedPassword);

            for (byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new PasswordExpertException(PasswordExpertException.FAILED_HASH + exception.getMessage());
        }
    }
}