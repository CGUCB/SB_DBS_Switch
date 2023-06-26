package com.cvs.dbpoc.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"com.cvs.dbpoc.repository.neww"},
    entityManagerFactoryRef = "newEntityManagerFactory",
    transactionManagerRef = "newTransactionManager"
)
public class NewDataSource {

    @Bean
    @ConfigurationProperties("dbnew.datasource")
    public DataSourceProperties getDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
	@ConfigurationProperties(prefix = "dbnew.datasource")
	public DataSource getDatasource() {
		return getDataSourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}

    @Bean(name = "newEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean newEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getDatasource()).packages("com.cvs.dbpoc.entity.neww").persistenceUnit("newdb").build();
	}

    @Bean(name = "newTransactionManager")
    @ConfigurationProperties("spring.jpa")
	public PlatformTransactionManager newTransactionManager(final @Qualifier("newEntityManagerFactory") LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}
}
