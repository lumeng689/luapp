package org.luapp.blog.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import com.alibaba.druid.pool.DruidDataSource;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by lumeng on 2015/4/4.
 */
@Configuration
@PropertySource(value = {"classpath:application.properties", "classpath:jdbc.properties"})
@ComponentScan(basePackages = "org.luapp.blog",excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION,value = {org.springframework.stereotype.Controller.class})})
public class AppConfig {

    @Inject
    Environment env;

    @Bean
    public DataSource dataSource() {
        final DruidDataSource ds = new DruidDataSource();

        ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.username"));
        ds.setPassword(env.getProperty("jdbc.password"));
        ds.setInitialSize(5);
        ds.setMinIdle(5);
        ds.setMaxActive(5);
        ds.setMaxWait(60000);
        ds.setTimeBetweenEvictionRunsMillis(60000);
        ds.setMinEvictableIdleTimeMillis(300000);

        return ds;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder =
                new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("org.luapp.blog.entity")
                .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }

    @Bean
    public JdbcOperations jdbcOperations() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
