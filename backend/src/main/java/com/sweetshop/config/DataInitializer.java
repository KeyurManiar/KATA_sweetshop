package com.sweetshop.config;

import com.sweetshop.model.Sweet;
import com.sweetshop.model.User;
import com.sweetshop.repository.SweetRepository;
import com.sweetshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(SweetRepository srepo, UserRepository urepo){
        return args -> {
            if(urepo.count()==0){
                urepo.save(new User("admin","adminpass","ADMIN"));
                urepo.save(new User("user","userpass","USER"));
            }
            if(srepo.count()==0){
                srepo.save(new Sweet("Gulab Jamun","Traditional",30.0,20));
                srepo.save(new Sweet("Chocolate Truffle","Chocolate",50.0,10));
            }
        };
    }
}
