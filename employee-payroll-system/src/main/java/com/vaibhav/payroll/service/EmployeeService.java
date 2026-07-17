package com.vaibhav.payroll.service;

import com.vaibhav.payroll.dto.EmployeeRequest;
import com.vaibhav.payroll.dto.EmployeeResponse;
import com.vaibhav.payroll.dto.PayrollResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse addEmployee(EmployeeRequest request);

    List<EmployeeResponse> getAllEmployees();


}
