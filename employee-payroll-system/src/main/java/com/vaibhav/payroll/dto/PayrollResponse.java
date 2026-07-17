package com.vaibhav.payroll.dto;

public record PayrollResponse(

        String employeeName,
        Double basicSalary,
        Double bonus,
        Double tax,
        Double netSalary
) {
}
