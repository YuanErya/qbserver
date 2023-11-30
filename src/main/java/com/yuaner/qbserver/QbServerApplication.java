
package com.yuaner.qbserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yuaner.qbserver.mapper")
public class QbServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QbServerApplication.class, args);
    }
}
