package com.spring.orm;

import com.spring.Dao.StudentDao;
import com.spring.Dao.StudentDaoImpl;
import com.spring.JavaConfig.Config;
import com.spring.Tables.Courses;
import com.spring.Tables.Student;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Test
    public void test() {

        ApplicationContext context = new ClassPathXmlApplicationContext("Config.xml");
        StudentDao studentDao = context.getBean("dao", StudentDaoImpl.class);
        Student s = Student.builder()
                .name("Thor")
                .email("thor@gmail.com")
                .age(1500)
                .city("Asgad")
                .build();


        System.out.println(studentDao.addStudent(s)+"Row inserted");
    }


    @Test
    public void addCourse () {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        StudentDaoImpl studentDao = context.getBean("std",StudentDaoImpl.class);
        System.out.println(studentDao);
        Courses courses = Courses.builder()
                .courseName("spring boot")
                .courseRate("7.5")
                .build();
        System.out.println(studentDao.addCourse(courses)+"Row inserted");
    }

    @Test
    public void allStudent() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        StudentDaoImpl studentDao = context.getBean("std",StudentDaoImpl.class);
        studentDao.allStudent().forEach(System.out::println);
    }

    @Test
    public void addCourseWithStudent () {
        Student student1 = Student.builder()
                .name("Ironman")
                .age(56)
                .email("ironman@gmail.com")
                .city("NewYork")
                .build();
        Student student = Student.builder()
                .name("Hulk")
                .age(199)
                .email("hulk@gmail.com")
                .city("usa")
                .build();
        Courses courses = Courses.builder()
                .courseName("Hibernate")
                .courseRate("8.7")
                .students(List.of(student,student1))
                .build();
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        StudentDaoImpl studentDao = context.getBean("std",StudentDaoImpl.class);
        System.out.println(studentDao.addCourse(courses));
    }
}
