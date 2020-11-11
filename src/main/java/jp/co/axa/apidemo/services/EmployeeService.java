package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;
import jp.co.axa.apidemo.requests.EmployeeRequest;

public interface EmployeeService {

    public List<Employee> retrieveEmployees();

    public Employee getEmployee(Long employeeId);

    public Employee saveEmployee(EmployeeRequest request);

    public void deleteEmployee(Long employeeId);

    public Employee updateEmployee(Long employeeId, EmployeeRequest request);
}