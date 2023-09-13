package com.grainger.opa.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Data
public class Salary {

  private @Id @GeneratedValue Long id;
  private String employeeId;
  private int baseSalary;

  public Salary() {}
  public Salary(String employeeId, int baseSalary) {
    this.employeeId = employeeId;
    this.baseSalary = baseSalary;
  }
}
