/**
 * 
 */
package com.cts.jcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author ungtq
 *
 */
@SpringBootApplication
public class JCartAdminApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JCartAdminApplication.class);
    }

	public static void main(String[] args){
		SpringApplication.run(JCartAdminApplication.class, args);
	}
}
