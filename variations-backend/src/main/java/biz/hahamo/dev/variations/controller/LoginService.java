package biz.hahamo.dev.variations.controller;

import java.security.SecureRandom;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.transaction.annotation.Transactional;

import biz.hahamo.dev.variations.controller.repository.UserRepository;
import biz.hahamo.dev.variations.model.User;

/**
 * Service to authenticate a user.
 * 
 * @author GHajba
 * 
 */
@Transactional(readOnly=true)
public class LoginService {

    public static final int SALT_BYTE_SIZE = 32;

    private final UserRepository userRepository;
    
    private static boolean USER_LOGGED_IN = false;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticateCredentials(String login, String password) {
        User user = findUserByLogin(login);
        if(user ==  null) {
            return false;
        }
        
        String salt = user.getSalt();
        String userPassword = user.getPassword();
        
        return (userPassword.equalsIgnoreCase(getUserPassword(password, salt)));
    }
    
    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
    
    public User findUserByEmail(String emailAddress) {
        return userRepository.findByEmail(emailAddress);
    }

    private String getUserPassword(String password, String salt) {
        return DigestUtils.sha512Hex(password+salt);
    }
    
    @Transactional
    public Long saveUser(User user) {
        return userRepository.saveUser(user);
    }
    
    @Transactional
    public Long createUser(String name, String login, String password, String emailAddress) {
        String salt = generateSalt();
        User user = new User(name, login, getUserPassword(password, salt), salt, emailAddress);
        return saveUser(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    
    public static synchronized boolean isUserLoggedIn() {
        return USER_LOGGED_IN;
    }
    
    public static synchronized void logInUser() {
        USER_LOGGED_IN = true;
    }
    
    public static synchronized void logOutUser() {
        USER_LOGGED_IN = false;
    }
    
    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return Hex.encodeHexString(salt);
    }

}
