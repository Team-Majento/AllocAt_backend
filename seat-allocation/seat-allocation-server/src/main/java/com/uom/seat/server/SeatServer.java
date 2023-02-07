package com.uom.seat.server;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan({ "com.uom", "com.uom.seat" })
@EnableJpaRepositories({ "com.uom.seat" })
@EntityScan({ "com.uom.seat" })
public class SeatServer {

	Logger logger = Logger.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(SeatServer.class, args);
	}

}


//http://localhost:8082/swagger-ui.html#/
