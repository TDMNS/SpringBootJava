package com.example.demo.repo;

import com.example.demo.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Student, Long> {

}
