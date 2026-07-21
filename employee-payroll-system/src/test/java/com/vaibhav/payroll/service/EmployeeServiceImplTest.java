package com.vaibhav.payroll.service;

import com.vaibhav.payroll.dto.EmployeeRequest;
import com.vaibhav.payroll.entity.Employee;
import com.vaibhav.payroll.exception.EmployeeNotFoundException;
import com.vaibhav.payroll.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void shouldAddEmployee() {
        EmployeeRequest request = new EmployeeRequest(
                "John",
                "IT",
                "Software Engineer",
                80000.0
        );
        Employee employee = new Employee(
                1L,
                "John",
                "IT",
                "Software Engineer",
                80000.0
        );

        when(repository.save(any(Employee.class)))
                .thenReturn(employee);

        var response = service.addEmployee(request);

        assertNotNull(response);

        assertEquals("John", response.name());

        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    void shouldReturnEmployee() {
        Employee employee = new Employee(
                1L,
                "John",
                "IT",
                "Developer",
                90000.0
        );
        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));

        var response = service.getEmployeeById(anyLong());
        assertEquals("John", response.name());
        assertEquals("IT",response.department());

    }

    @Test
    void shouldThrowException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> service.getEmployeeById(anyLong()));

    }

    @Test
    void shouldGeneratePayroll() {
        Employee employee = new Employee(
                1L,
                "John",
                "IT",
                "Software Engineer",
                80000.0
        );
        when(repository.findById(anyLong())).thenReturn(Optional.of(employee));

        var payroll = service.generatePayroll(anyLong());

        assertEquals(16000.0,payroll.bonus());

        assertEquals(8000.0,payroll.tax());

        assertEquals(88000.0, payroll.netSalary());
    }
}
