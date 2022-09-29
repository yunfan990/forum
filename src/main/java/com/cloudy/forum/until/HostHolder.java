package com.cloudy.forum.until;

import com.cloudy.forum.entity.User;
import org.springframework.stereotype.Component;

/**
 * @auther li bin
 * @create 2022-09-30 1:11
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
