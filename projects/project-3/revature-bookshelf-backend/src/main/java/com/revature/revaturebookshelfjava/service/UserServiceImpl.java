package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Authority;
import com.revature.revaturebookshelfjava.entity.Cart;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.repository.AuthRepository;
import com.revature.revaturebookshelfjava.repository.CartRepository;
import com.revature.revaturebookshelfjava.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    //    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private CartRepository cartRepository;
    //    @Autowired
    private final PasswordEncoder passwordEncoder;

    // MAYBE FIXME:
    // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder()

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
    }

    // Called Internally, username always exist
    @Override
    public User getUser(String username) {
        return userRepository.findByEmail(username).get();
    }

    @Override
    public User register(User user) {

        // Input User with plain-text password
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // Checking for existing authorities based
        // TODO: USE STREAM API FOR SUPPORT OF MULTIPLE input AUTHORITY
        Optional<Authority> result = authRepository.findByAuthority(user.getAuthorities().iterator().next().getAuthority());
        if (!result.isEmpty()) { // Existing Record
            user.setAuthorities(List.of(result.get()));
        }

        return userRepository.save(user);

        // Creating New Cart for a New User

    }

    // From UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) throw new UsernameNotFoundException(username);

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : user.get().getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), grantedAuthorities);
    }


}
