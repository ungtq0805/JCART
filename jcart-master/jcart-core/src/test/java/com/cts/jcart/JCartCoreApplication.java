/**
 * 
 */
package com.cts.jcart;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author ungtq
 *
 */
@SpringBootApplication
public class JCartCoreApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(JCartCoreApplication.class, args);
	}

	@Bean  
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){  
	    return hemf.getSessionFactory();  
	}
}
