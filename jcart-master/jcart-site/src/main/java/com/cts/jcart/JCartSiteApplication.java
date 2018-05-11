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
public class JCartSiteApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JCartSiteApplication.class);
    }
	
	public static void main(String[] args){
		SpringApplication.run(JCartSiteApplication.class, args);
	}
}
