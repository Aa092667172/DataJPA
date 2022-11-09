package com.jpa.datajpa.dao;

import com.jpa.datajpa.entity.EmployeeOnlyNameSnoEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeOnlyNameRepository extends JpaRepository<EmployeeOnlyNameSnoEntity,Long> {
}
