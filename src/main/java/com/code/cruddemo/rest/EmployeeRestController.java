package com.code.cruddemo.rest;

import com.code.cruddemo.dao.EmployeeDAO;
import com.code.cruddemo.entity.Employee;
import com.code.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null) throw new RuntimeException("Employee id not found - " + employeeId);
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee currEmployee = employeeService.findById(theEmployee.getId());
        if(currEmployee == null) throw new RuntimeException("Employee not found: 404 ");
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee currEmployee = employeeService.findById(employeeId);
        if(currEmployee == null) throw new RuntimeException("Employee not found: 404 ");
        employeeService.deleteById(employeeId);
        return "Employee Deleted!!!!!!!!!" + employeeId;
    }
}
