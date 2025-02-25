package com.priyavrat.springboot.thymeleafdemo.dao;

import com.priyavrat.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    //Add a method to sort by last name -- spring data jpa will read the method name and create the query accordingly
    public List<Employee> findAllByOrderByLastNameAsc();

}
