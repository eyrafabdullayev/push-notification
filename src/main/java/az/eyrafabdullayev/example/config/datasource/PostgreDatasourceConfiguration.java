package az.eyrafabdullayev.example.config.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "az.eyrafabdullayev.example.repository", entityManagerFactoryRef = "postgre-em", transactionManagerRef = "postgre-tm")
public class PostgreDatasourceConfiguration {

    @Value("${postgre.datasource.username}")
    private String user;
    @Value("${postgre.datasource.password}")
    private String password;
    @Value("${postgre.datasource.url}")
    private String host;
    @Value("${postgre.datasource.driver-class-name}")
    private String driver;
    private final String dialect = "org.hibernate.dialect.PostgreSQLDialect";

    @Bean(name = "postgre-datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .username(user)
                .password(password)
                .url(host)
                .driverClassName(driver)
                .build();
    }

    @Bean(name = "postgre-jpaproperties")
    public HashMap<String, Object> jpaProperties() {
        HashMap<String, Object> jpaPropertiesMap = new HashMap<>();
        jpaPropertiesMap.put("hibernate.dialect", dialect);
        jpaPropertiesMap.put("spring.jpa.show-sql", "true");
        jpaPropertiesMap.put("hibernate.show_sql", "true");
        jpaPropertiesMap.put("hibernate.format_sql", "true");
        jpaPropertiesMap.put("hibernate.hbm2ddl.auto", "update");
        jpaPropertiesMap.put("hibernate.enable_lazy_load_no_trans", "true");
        return jpaPropertiesMap;
    }

    @Bean(name = "postgre-em")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setJpaPropertyMap(jpaProperties());
        em.setPackagesToScan("az.eyrafabdullayev.example.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean(name = "postgre-tm")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
