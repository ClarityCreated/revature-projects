package org.example.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins={"http://localhost:4200/"})
public class TransferBalanceRequest {
    private String fromAccountNumber;

    private String toAccountNumber;

    private double amount;

}