package org.example.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.example"})
@EntityScan(value = {"org.example.entity"})
@EnableTransactionManagement
public class TransferServiceConfiguration {



}