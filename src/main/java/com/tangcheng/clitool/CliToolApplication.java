package com.tangcheng.clitool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CliToolApplication {
    public static String[] COMMAND_ARGS;

    public static void main(String[] args) {
        COMMAND_ARGS = args;
        /**
         * Close the context so it doesn't stay awake listening for redis
         */
        SpringApplication.run(CliToolApplication.class, args).close();
    }


}
