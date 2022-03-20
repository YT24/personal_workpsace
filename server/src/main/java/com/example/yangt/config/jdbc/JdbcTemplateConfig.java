package com.example.yangt.config.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 16602
 */
@Component
public class JdbcTemplateConfig {

	public final DataSource ds;
	
	
	public JdbcTemplateConfig(DataSource ds) {
		super();
		this.ds = ds;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(ds);
	}
}
