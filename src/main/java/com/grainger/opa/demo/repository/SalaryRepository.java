package com.grainger.opa.demo.repository;

import com.grainger.opa.demo.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

  Salary findByEmployeeId(String employeeId);

}
