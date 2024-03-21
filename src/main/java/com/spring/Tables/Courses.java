package com.spring.Tables;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Courses {

    @Id
    @SequenceGenerator(
            name = "course_seq",
            allocationSize = 1,
            sequenceName = "course_seq"
    )
    @GeneratedValue(
            generator = "course_seq",
            strategy = GenerationType.SEQUENCE
    )
    private int courseId;
    private String courseName;
    private String courseRate;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER

    )
    @JoinTable(
           joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName ="id")
    )
    private List<Student> students;



}
