package edu.epam.fop.web.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "edu.epam.fop.web")
public class JpaConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");

        dataSource.setUrl("jdbc:h2:file:./data/universitydb;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE");

        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("edu.epam.fop.web");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);

        // Hibernate properties
        java.util.Properties props = new java.util.Properties();
        props.put("hibernate.hbm2ddl.auto", "update");  // Schema creation
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect"); // Adjust dialect
        props.put("hibernate.show_sql", "true");        // Enable SQL logging
        props.put("hibernate.format_sql", "true");      // Pretty print SQL
        props.put("hibernate.transaction.coordinator_class", "jdbc"); // Use local (JDBC) transactions
        props.put("hibernate.connection.provider_disables_autocommit", "true"); // Ensure transactions
        props.put("hibernate.jta.platform", "org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform"); // Disable JTA
        emf.setJpaProperties(props);

        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
