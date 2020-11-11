package jp.co.axa.apidemo.services

import jp.co.axa.apidemo.entities.Employee
import jp.co.axa.apidemo.repositories.EmployeeRepository
import jp.co.axa.apidemo.requests.EmployeeRequest
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

class EmployeeServiceImplTest extends Specification {

    def "RetrieveEmployees"() {
        setup:
            def employeeService = new EmployeeServiceImpl()
            def mockRepo = Mock(EmployeeRepository)
            mockRepo.findAll() >> mockEmployees()
            employeeService.employeeRepository = mockRepo

        when:
            def result = employeeService.retrieveEmployees()

        then:
            result.size() == 3
            result.get(0).getName() == "James Bond"
            result.get(1).getId() == 2L
    }

    def "GetEmployee"(Long id, Integer salary, String name, String department) {
        setup:
            def employeeService = new EmployeeServiceImpl()
            def mockRepo = Mock(EmployeeRepository)
            mockRepo.findById(1L) >> Optional.of(mockEmployee1())
            mockRepo.findById(2L) >> Optional.of(mockEmployee2())
            mockRepo.findById(3L) >> Optional.of(mockEmployee3())
            employeeService.employeeRepository = mockRepo

        when:
            def result = employeeService.getEmployee(id)

        then:
            result.salary == salary
            result.name == name
            result.department == department

        where:
            id  | salary    | name              | department
            1L  | 1000      | "James Bond"      | "Kitchen"
            2L  | 2000      | "Jason Bourne"    | "Cleaning"
            3L  | 3000      | "Ethan Hunt"      | "Waiter"
    }

    def "GetEmployeeNotExist" () {
        setup:
            def employeeService = new EmployeeServiceImpl()
            def mockRepo = Mock(EmployeeRepository)
            mockRepo.findById(1L) >> Optional.ofNullable(null)
            employeeService.employeeRepository = mockRepo

        when:
            employeeService.getEmployee(1L)

        then:
            EntityNotFoundException ex = thrown()
            ex.message == "Employee not found"
    }

    def "SaveEmployee"(Long id, Integer salary, String name, String department) {
        setup:
            def employeeService = new EmployeeServiceImpl()
            def mockRepo = Mock(EmployeeRepository)
            employeeService.employeeRepository = mockRepo

        when:
            EmployeeRequest request = new EmployeeRequest()
            request.setSalary(salary)
            request.setName(name)
            request.setDepartment(department)
            def result = employeeService.saveEmployee(request)

        then:
            result.salary == salary
            result.name == name
            result.department == department

        where:
            id  | salary    | name              | department
            1L  | 1000      | "James Bond"      | "Kitchen"
            2L  | 2000      | "Jason Bourne"    | "Cleaning"
            3L  | 3000      | "Ethan Hunt"      | "Waiter"
    }

    def "DeleteEmployee"() {
        setup:
            def employeeService = new EmployeeServiceImpl()
            def mockRepo = Mock(EmployeeRepository)
            mockRepo.findById(1L) >> Optional.ofNullable(mockEmployee1())
            employeeService.employeeRepository = mockRepo

        expect:
            employeeService.deleteEmployee(1L)
    }

    def "DeleteEmployeeNotExist"() {
        setup:
            def employeeService = new EmployeeServiceImpl()
            def mockRepo = Mock(EmployeeRepository)
            mockRepo.findById(1L) >> Optional.ofNullable(null)
            employeeService.employeeRepository = mockRepo

        when:
            employeeService.deleteEmployee(1L)

        then:
            EntityNotFoundException ex = thrown()
            ex.message == "Employee not found"
    }

    def "UpdateEmployee"(Long id, Integer salary, String name, String department) {
        setup:
            def employeeService = new EmployeeServiceImpl()
            def mockRepo = Mock(EmployeeRepository)
            mockRepo.findById(1L) >> Optional.of(mockEmployee1())
            mockRepo.findById(2L) >> Optional.of(mockEmployee2())
            mockRepo.findById(3L) >> Optional.of(mockEmployee3())
            employeeService.employeeRepository = mockRepo

        when:
            EmployeeRequest request = new EmployeeRequest()
            request.setSalary(salary)
            request.setName(name)
            request.setDepartment(department)
            def result = employeeService.updateEmployee(id, request)

        then:
            result.id == id
            result.salary == salary
            result.name == name
            result.department == department

        where:
            id  | salary    | name              | department
            1L  | 1000      | "James Bond"      | "Kitchen"
            2L  | 2000      | "Jason Bourne"    | "Cleaning"
            3L  | 3000      | "Ethan Hunt"      | "Waiter"
    }

    def "UpdateEmployeeNotExist"() {
        setup:
            def employeeService = new EmployeeServiceImpl()
            def mockRepo = Mock(EmployeeRepository)
            mockRepo.findById(1L) >> Optional.ofNullable(null)
            employeeService.employeeRepository = mockRepo

        when:
            EmployeeRequest request = new EmployeeRequest()
            employeeService.updateEmployee(1L, request)

        then:
            EntityNotFoundException ex = thrown()
            ex.message == "Employee not found"
    }

    def "mockEmployees"() {
        List<Employee> employees = new ArrayList<>()
        employees.add(mockEmployee1())
        employees.add(mockEmployee2())
        employees.add(mockEmployee3())
        employees
    }

   def "mockEmployee1"() {
       Employee.builder()
                .id(1L)
                .name("James Bond")
                .salary(1000)
                .department("Kitchen")
                .build()
    }

    def "mockEmployee2"() {
        Employee.builder()
                .id(2L)
                .name("Jason Bourne")
                .salary(2000)
                .department("Cleaning")
                .build()
    }

    def "mockEmployee3"() {
        Employee.builder()
                .id(3L)
                .name("Ethan Hunt")
                .salary(3000)
                .department("Waiter")
                .build()
    }
}
