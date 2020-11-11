package jp.co.axa.apidemo.controllers;

import javax.validation.Valid;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.requests.EmployeeRequest;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@Valid @PathVariable(name="employeeId")Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody EmployeeRequest request){
        return employeeService.saveEmployee(request);

    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@Valid @PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@Valid @PathVariable(name="employeeId") Long employeeId,
        @Valid @RequestBody EmployeeRequest employee){
        return employeeService.updateEmployee(employeeId, employee);
    }

}
