package com.vaibhav.payroll.dto;


public record EmployeeResponse(

        Long id,
        String name,
        String department,
        String designation,
        Double salary
) {
}
