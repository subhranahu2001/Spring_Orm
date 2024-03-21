package com.spring.JavaConfig;

import com.spring.Dao.StudentDaoImpl;
import com.spring.Tables.Courses;
import com.spring.Tables.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = "com.spring")
public class Config {

    @Bean
    public DriverManagerDataSource getDriverManagerDataSource () {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/spring_orm");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("Silu@2001");

        return driverManagerDataSource;
    }
    @Bean
   public LocalSessionFactoryBean getLocalSessionFactoryBean () {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDriverManagerDataSource());

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.format_sql","true");

        localSessionFactoryBean.setHibernateProperties(properties);

        localSessionFactoryBean.setAnnotatedClasses(Courses.class,Student.class);

        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        HibernateTemplate template = new HibernateTemplate();
        template.setSessionFactory(getLocalSessionFactoryBean().getObject());

        return template;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager () {
        HibernateTransactionManager hibernateTransactionManager =
                new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(getLocalSessionFactoryBean().getObject());

        return hibernateTransactionManager;
    }


}
