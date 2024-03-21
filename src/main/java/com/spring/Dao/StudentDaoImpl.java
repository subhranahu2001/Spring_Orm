package com.spring.Dao;

import com.spring.Tables.Courses;
import com.spring.Tables.Student;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Data
@Repository("std")
public class StudentDaoImpl implements StudentDao{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public int addStudent(Student student) {
        return (int)hibernateTemplate.save(student);
    }

    @Transactional
    public int addCourse(Courses courses) {
        return (int)hibernateTemplate.save(courses);
    }

    @Transactional
    public List<Student> allStudent() {

        return hibernateTemplate.loadAll(Student.class);
    }


}
