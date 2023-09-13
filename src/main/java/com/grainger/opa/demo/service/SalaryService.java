package com.grainger.opa.demo.service;

import com.grainger.opa.demo.model.Salary;
import com.grainger.opa.demo.repository.SalaryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

  @Autowired
  private SalaryRepository repository;

  public List<Salary> getAllSalaries() {
    return repository.findAll();
  }

  public Salary updateSalary(Salary salary) {
    Salary existingSalary = repository.findByEmployeeId(salary.getEmployeeId());
    if (existingSalary != null) {
      Salary newSalary = new Salary(salary.getEmployeeId(), salary.getBaseSalary());
      newSalary.setId(existingSalary.getId());
      return repository.saveAndFlush(newSalary);
    } else {
      return repository.saveAndFlush(salary);
    }
  }

  public Salary getSalaryByEmployeeId(String employeeId) {
    return repository.findByEmployeeId(employeeId);
  }
}
