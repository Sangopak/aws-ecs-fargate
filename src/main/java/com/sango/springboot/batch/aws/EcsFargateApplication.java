package com.sango.springboot.batch.aws;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.sango.springboot.batch.aws")
public class EcsFargateApplication {

    public static void main(String [] args){
        new SpringApplicationBuilder(EcsFargateApplication.class).web(false).run(args);
    }
}
