package com.vaibhav.payroll.service;

import com.vaibhav.payroll.dto.EmployeeRequest;
import com.vaibhav.payroll.dto.EmployeeResponse;
import com.vaibhav.payroll.dto.PayrollResponse;
import com.vaibhav.payroll.repository.EmployeeRepository;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest request) {
        return null;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return List.of();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        return null;
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

    }

    @Override
    public PayrollResponse generatePayroll(Long id) {
        return null;
    }
}
