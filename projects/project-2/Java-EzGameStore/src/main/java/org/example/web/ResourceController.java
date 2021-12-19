package org.example.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ResourceController {
    @GetMapping(
            value="/api/public")
    public String publicResource(){

        ///insert main paige here?
        return "PUBLIC";
    }

    @GetMapping(
            value="/api/private")
    public String privateResource(){

        //insert different page here

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();

        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getAuthorities());

        return "PRIVATE";
    }
}
