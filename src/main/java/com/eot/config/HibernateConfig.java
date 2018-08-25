package com.eot.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@EnableTransactionManagement
@ComponentScan({"com.eot.config"})
public class HibernateConfig {
	
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
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/eotassign");
		ds.setUsername("root");
		ds.setPassword("123456789");
		return ds;
		
	}
	
	private Properties hibernateProperties() {
		Properties properties  = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "none");
		
		
		
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