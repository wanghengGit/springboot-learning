package com.kit.bootmycat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages="com.kit.bootmycat.mapper")
@SpringBootApplication
public class BootMycatApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMycatApplication.class, args);
    }

}
