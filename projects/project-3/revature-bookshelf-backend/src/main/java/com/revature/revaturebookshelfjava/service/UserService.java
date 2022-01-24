package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Cart;
import com.revature.revaturebookshelfjava.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    //UserDetails loadUserByUsername(String username);
    User register(User user);
    User getUser(String username);
}
