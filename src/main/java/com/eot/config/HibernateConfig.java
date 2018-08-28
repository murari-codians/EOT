package com.eot.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
@ComponentScan({"com.eot.config"})
public class HibernateConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
	LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
	localSessionFactoryBean.setDataSource(dataSource());
	localSessionFactoryBean.setPackagesToScan(new String[] {"com.eot.domain.model"});
	localSessionFactoryBean.setHibernateProperties(hibernateProperties());
	return localSessionFactoryBean;
		
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("hibernate.connection.driver_class"));
		ds.setUrl(env.getProperty("hibernate.connection.url"));
		ds.setUsername(env.getProperty("hibernate.connection.username"));
		ds.setPassword(env.getProperty("hibernate.connection.password"));
		return ds;
		
	}
	
	private Properties hibernateProperties() {
		Properties properties  = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		
		
		return properties;
		
	}
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory session) {
		HibernateTransactionManager hibTxnmanger = new HibernateTransactionManager();
		hibTxnmanger.setSessionFactory(session);
		return hibTxnmanger;
		
	}

}
