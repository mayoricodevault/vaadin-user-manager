package de.merten.umgr.dbimport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

import de.merten.umgr.backend.bo.Gender;
import de.merten.umgr.backend.bo.Role;
import de.merten.umgr.backend.bo.User;

/**
 * Singelton to create User for testing.
 * 
 * @author merten
 * 
 */
public class UserFactory {

    private static UserFactory instance;

    public static UserFactory getInstance() {
        if (instance == null) instance = new UserFactory();
        return instance;
    }

    private UserFactory() {
    }

    public List<User> getUserList() {
        List<User> result = new ArrayList<User>();
        String pwd = "dev;2011";
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        pwd = encoder.encode(pwd);
        User account = new User("merten", Gender.MALE, "givenName", "sureName", "merten@berckemeyer.de",pwd);
        Set<Role> roles = new HashSet<Role>();
        roles.add(Role.ADMIN);
        roles.add(Role.ROOT);
        roles.add(Role.USER);
        account.setRoles(roles);
        result.add(account);
        return result;
    }

}
