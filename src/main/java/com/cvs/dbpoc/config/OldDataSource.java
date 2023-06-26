package com.cvs.dbpoc.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"com.cvs.dbpoc.repository.oldd"},
    entityManagerFactoryRef = "oldEntityManagerFactory",
    transactionManagerRef = "oldTransactionManager"
)
public class OldDataSource {
    
    @Primary
    @Bean
    @ConfigurationProperties("dbold.datasource")
    public DataSourceProperties getDataSourceProperty() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
	@ConfigurationProperties(prefix = "dbold.datasource")
	public DataSource getDataSource() {
		return getDataSourceProperty().initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}

    @Primary
    @Bean(name = "oldEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean oldEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getDataSource()).packages("com.cvs.dbpoc.entity.oldd").persistenceUnit("olddb").build();
	}

    @Primary
    @Bean(name = "oldTransactionManager")
    @ConfigurationProperties("spring.jpa")
	public PlatformTransactionManager oldTransactionManager(final @Qualifier("oldEntityManagerFactory") LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}
}
