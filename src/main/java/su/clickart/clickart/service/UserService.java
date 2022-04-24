package su.clickart.clickart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.clickart.clickart.entity.User;
import su.clickart.clickart.repository.UserRepository;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public Cookie createCookie() {
        int val = String.valueOf(Math.random() * 100000).hashCode();
        Cookie cookie = new Cookie("user", String.valueOf(val));
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);

        return cookie;
    }


}
