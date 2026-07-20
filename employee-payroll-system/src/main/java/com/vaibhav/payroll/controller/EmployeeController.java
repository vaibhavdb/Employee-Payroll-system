package com.vaibhav.payroll.controller;

import com.vaibhav.payroll.dto.EmployeeRequest;
import com.vaibhav.payroll.dto.EmployeeResponse;
import com.vaibhav.payroll.dto.PayrollResponse;
import com.vaibhav.payroll.entity.Employee;
import com.vaibhav.payroll.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@Valid @RequestBody EmployeeRequest request) {
        return employeeService.addEmployee(request);
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/payroll/{id}")
    public PayrollResponse generatePayroll(@PathVariable Long id) {
        return employeeService.generatePayroll(id);
    }


}
