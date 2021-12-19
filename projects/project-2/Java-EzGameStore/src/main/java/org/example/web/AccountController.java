package org.example.web;

import org.example.entity.*;
import org.example.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:4200/"})
@RequestMapping(value="/api/account")
public class AccountController {
    @Autowired
    private TransferService transferService;

    @GetMapping(value = "/create")
    public List<Account> create(@Valid @RequestBody Account account) {
        transferService.save(account);
        return transferService.findAll();
    }

    ;

    @PostMapping(value = "/transfer")
    public ResponseEntity<Transaction> transfer(
            @RequestBody TransferBalanceRequest transferBalanceRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                transferService.transfer(
                        transferBalanceRequest
                )
        );
    }
}