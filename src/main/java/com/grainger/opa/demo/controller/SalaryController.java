package com.grainger.opa.demo.controller;

import com.grainger.opa.demo.model.Salary;
import com.grainger.opa.demo.service.SalaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

  @Autowired
  private SalaryService salaryService;

  @GetMapping("/getSalaries")
  List<Salary> getAllSalaries() {
    return salaryService.getAllSalaries();
  }

  @GetMapping("/getSalary/{employeeId}")
  Salary getSalaryByEmployeeId(@PathVariable String employeeId) {
    return salaryService.getSalaryByEmployeeId(employeeId);
  }

  @PutMapping("/updateSalary")
  Salary updateSalary(@RequestBody Salary newSalary) {
    return salaryService.updateSalary(newSalary);
  }


}
