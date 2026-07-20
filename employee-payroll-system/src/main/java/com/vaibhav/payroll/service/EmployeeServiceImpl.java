package com.vaibhav.payroll.service;

import com.vaibhav.payroll.dto.EmployeeRequest;
import com.vaibhav.payroll.dto.EmployeeResponse;
import com.vaibhav.payroll.dto.PayrollResponse;
import com.vaibhav.payroll.entity.Employee;
import com.vaibhav.payroll.exception.EmployeeNotFoundException;
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
                new EmployeeNotFoundException("Employee not found with id :" + id));
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getDesignation(),
                employee.getSalary()
        );
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {

        var employee = repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee not found with id :" + id));


        employee.setName(request.name());
        employee.setDepartment(request.department());
        employee.setDesignation(request.designation());
        employee.setSalary(request.salary());

        var updatedEmployee = repository.save(employee);
        return new EmployeeResponse(
                updatedEmployee.getId(),
                updatedEmployee.getName(),
                updatedEmployee.getDepartment(),
                updatedEmployee.getDesignation(),
                updatedEmployee.getSalary()
        );
    }

    @Override
    public void deleteEmployee(Long id) {

        if (!repository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id :" + id);
        }

        repository.deleteById(id);
    }

    @Override
    public PayrollResponse generatePayroll(Long id) {

        var employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not Found with id :" +id));

        double bonus = switch (employee.getDepartment()) {
            case "IT" -> employee.getSalary() * 0.20;
            case "HR" -> employee.getSalary() * 0.10;
            case "finance" -> employee.getSalary() * 0.15;
            default -> employee.getSalary() * 0.05;
        };

        double tax = employee.getSalary() * 0.10;

        double netSalary = employee.getSalary() + bonus - tax;

        return new PayrollResponse(
                employee.getName(),
                employee.getSalary(),
                bonus,
                tax,
                netSalary
        );
    }
}
