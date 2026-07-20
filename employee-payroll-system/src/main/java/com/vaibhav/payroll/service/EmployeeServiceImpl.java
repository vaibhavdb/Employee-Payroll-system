package com.vaibhav.payroll.service;

import com.vaibhav.payroll.dto.EmployeeRequest;
import com.vaibhav.payroll.dto.EmployeeResponse;
import com.vaibhav.payroll.dto.PayrollResponse;
import com.vaibhav.payroll.entity.Employee;
import com.vaibhav.payroll.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest request) {

        var employee = new Employee();

        employee.setName(request.name());
        employee.setDepartment(request.department());
        employee.setDesignation(request.designation());
        employee.setSalary(request.salary());

        var savedEmployee = repository.save(employee);

        return new EmployeeResponse(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getDepartment(),
                savedEmployee.getDesignation(),
                savedEmployee.getSalary()
        );
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return repository.findAll().stream().map(employee -> new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getDesignation(),
                employee.getSalary()
        )).toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        var employee = repository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException("Employee not found with id :" + id))
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
