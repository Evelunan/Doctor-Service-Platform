package com.hd.dsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class DoctorServicePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorServicePlatformApplication.class, args);
    }

}
