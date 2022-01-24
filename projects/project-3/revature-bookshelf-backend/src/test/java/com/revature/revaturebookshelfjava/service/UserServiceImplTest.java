package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Authority;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.repository.AuthRepository;
import com.revature.revaturebookshelfjava.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UserServiceImplTest {

    private static UserServiceImpl userService;
    private static AuthRepository authRepositoryMock;
    private static UserRepository userRepositoryMock;
    private static PasswordEncoder passwordEncoderMock;

    @BeforeEach
    public void init() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        passwordEncoderMock = Mockito.mock(PasswordEncoder.class);
        authRepositoryMock = Mockito.mock(AuthRepository.class);
        userService = new UserServiceImpl(userRepositoryMock,passwordEncoderMock,authRepositoryMock);
    }

    @BeforeEach
    public void recordMock() {
        Mockito.when(passwordEncoderMock.encode("password")).thenReturn("password-encoded");
        Mockito.when(authRepositoryMock.findByAuthority("ROLE_USER")).thenReturn(Optional.of(new Authority("ROLE_USER")));
        Mockito.when(userRepositoryMock.save(new User(1,"test@mail.com","password-encoded",List.of(new Authority("ROLE_USER")),null,null,null,"test-first","test-middle","test-last")))
                .thenReturn(new User(1,"test@mail.com","password-encoded",List.of(new Authority("ROLE_USER")),null,null,null,"test-first","test-middle","test-last"));
        Mockito.when(userRepositoryMock.findByEmail("test2@mail.com"))
                .thenReturn(Optional.of(new User(0,"test2@mail.com","password-encoded",List.of(new Authority("ROLE_USER")),null,null,null,null,null,null)));
    }

    @Test
    @DisplayName("User Registration Test")
    public void registerTest() {
        User user = new User();
        user.setId(1);
        user.setEmail("test@mail.com");
        user.setFirstName("test-first");
        user.setMiddleName("test-middle");
        user.setLastName("test-last");
        user.setPassword("password");
        user.setAuthorities(List.of(new Authority("ROLE_USER")));
        User actual = userService.register(user);
        User expected = new User(1,"test@mail.com","password-encoded",List.of(new Authority("ROLE_USER")),null,null,null,"test-first","test-middle","test-last");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Load User By Username Test")
    public void loadUserByUsernameWithAuthoritiesTest() {
        User user = new User();
        user.setEmail("test2@mail.com");
        user.setPassword("password-encoded");
        user.setAuthorities(List.of(new Authority("ROLE_USER")));
        UserDetails actual = userService.loadUserByUsername(user.getEmail());
        UserDetails expected = new org.springframework.security.core.userdetails.User("test2@mail.com","password-encoded",List.of(new SimpleGrantedAuthority("ROLE_USER")));
        Assertions.assertEquals(expected,actual);
    }
}
