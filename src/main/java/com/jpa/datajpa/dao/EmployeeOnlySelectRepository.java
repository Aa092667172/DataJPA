package com.jpa.datajpa.dao;

import com.jpa.datajpa.entity.Employee;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface EmployeeOnlySelectRepository extends Repository<Employee,Long> {
    List<Employee> findAll();

    Employee findById(Long id);
}
