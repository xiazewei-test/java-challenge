package jp.co.axa.apidemo.services;

import javax.persistence.EntityNotFoundException;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.requests.EmployeeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{

    public static final String NOT_FOUND_EXCEPTION = "Employee not found";

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Get the list of all employees
     * @return
     */
    @Cacheable("employees")
    public List<Employee> retrieveEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Get the employee info by id
     * @param employeeId
     * @return
     */
    @CachePut(value="employee", key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_EXCEPTION));
    }

    /**
     * Save new employee, with generated employee id
     * @param request
     * @return created employee info
     */
    @CacheEvict(cacheNames = "employees", allEntries = true)
    public Employee saveEmployee(EmployeeRequest request){
        Employee employee = Employee.builder()
            .name(request.getName())
            .salary(request.getSalary())
            .department(request.getDepartment()).build();
        employeeRepository.save(employee);
        log.info("Employee saved successfully! " + employee.toString());
        return employee;
    }

    /**
     * Delete the employee by id
     * @param employeeId
     */
    @Caching(evict = {
        @CacheEvict(cacheNames = "employees", allEntries = true),
        @CacheEvict(cacheNames = "employee")})
    public void deleteEmployee(Long employeeId){
        employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_EXCEPTION));
        employeeRepository.deleteById(employeeId);
        log.info("Employee Deleted Successfully! Id:" + employeeId);
    }

    /**
     * Update the existing employee
     * @param employeeId
     * @param request
     * @return updated employee info
     */
    @Caching(evict = {
        @CacheEvict(cacheNames = "employees", allEntries = true),
        @CacheEvict(cacheNames = "employee")})
    public Employee updateEmployee(Long employeeId, EmployeeRequest request) {
        //check if exists
        employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_EXCEPTION));

        Employee employee = Employee.builder()
            .id(employeeId)
            .name(request.getName())
            .salary(request.getSalary())
            .department(request.getDepartment()).build();
        employeeRepository.save(employee);
        log.info("Employee updated successfully! Id:" + employeeId);
        return employee;
    }
}