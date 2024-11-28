package com.FMS.fmsbackend.config.DataSource;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class MultiDataSourceConfig {

	@Bean(name = "mysqlDataSource")
	@Primary
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://10.195.32.28:3306/db_fms");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		return dataSource;
	}


	@Bean(name = "mysqlJdbcTemplate")
	public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	

	/***************** sqlServerLocalData ***************/
	@Bean(name = "sqlServerLocalData")
	public DataSource sqlServerLocalData() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(
				"jdbc:sqlserver://10.57.40.101:1433;databaseName=train_pro;encrypt=true;trustServerCertificate=true;");
		dataSource.setUsername("sa");
		dataSource.setPassword("P@55w0rd");
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return dataSource;
	}
	@Bean(name = "sqlServerLocalJdbcTemplate")
	public JdbcTemplate sqlServerLocalJdbcTemplate(@Qualifier("sqlServerLocalData") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
