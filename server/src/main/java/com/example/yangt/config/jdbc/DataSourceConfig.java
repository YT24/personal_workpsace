package com.example.yangt.config.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/**
 * @author 16602
 */
@Configuration
public class DataSourceConfig {
	
	@Value("${spring.datasource.driver-class-name}")
	public String driverClass;
	@Value("${spring.datasource.url}")
	public String url;
	@Value("${spring.datasource.username}")
	public String username;
	@Value("${spring.datasource.password}")
	public String password;

    /**
     * @return DataSource
     */
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        //用户名
        dataSource.setPassword(password);
        //密码
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }

}