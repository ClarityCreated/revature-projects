package com.revature.revaturebookshelfjava.controller;

import com.revature.revaturebookshelfjava.authenicator.extractor.UserDetailsExtractor;
import com.revature.revaturebookshelfjava.controller.payload.HttpResponseBody;
import com.revature.revaturebookshelfjava.entity.Address;
import com.revature.revaturebookshelfjava.entity.User;
import com.revature.revaturebookshelfjava.service.AddressService;
import com.revature.revaturebookshelfjava.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@Slf4j
@RestController
public class AddressController {
    private final UserService userService;
    private final AddressService addressService;
    @Autowired
    private UserDetailsExtractor userDetailsExtractor;
    @Autowired
    public AddressController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    public AddressController(UserService userService, AddressService addressService, UserDetailsExtractor userDetailsExtractor) {
        this.userService = userService;
        this.addressService = addressService;
        this.userDetailsExtractor = userDetailsExtractor;
    }

    // TODO: Finalize URL + Add to security filter
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/test"
    )
    public ResponseEntity<?> getTEST(Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info(userDetails.getUsername());
        log.info(userDetails.getAuthorities().toString());
        log.info("------------------------");
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        log.info(authenticationToken.toString());
        return ResponseEntity.ok("JWT TEST");
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/api/address"
    )
    public List<Address> getUserAddress() {
        String username = userDetailsExtractor.extractUsername();
        User user = userService.getUser(username);
        return addressService.getAddressByUser(user);
    }
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/address"
    )
    public ResponseEntity<?> postUserAddress(@RequestBody Address address) {
        log.info(address.toString());
        String username = userDetailsExtractor.extractUsername();
        // call userService.getUser(String username);
        User user = userService.getUser(username);
        try {
            addressService.registerAddress(address, user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        HttpResponseBody httpResponseBody = new HttpResponseBody("user's address posted");
        return ResponseEntity.status(HttpStatus.CREATED).body(httpResponseBody);
    }

    public ResponseEntity<?> putUserAddress(@RequestBody Address address) {
        String username = userDetailsExtractor.extractUsername();
        User user = userService.getUser(username);
        /* Operate on User for editing addresses*/
//        try {
//
//        } catch () {
//
//        }
        return ResponseEntity.ok("Address edited");
    }
}
