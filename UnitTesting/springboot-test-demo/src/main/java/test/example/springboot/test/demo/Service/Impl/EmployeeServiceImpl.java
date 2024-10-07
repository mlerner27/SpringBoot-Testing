package test.example.springboot.test.demo.Service.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import test.example.springboot.test.demo.Client.CallbackClientService;
import test.example.springboot.test.demo.Model.Employee;
import test.example.springboot.test.demo.Repository.EmployeeRepository;
import test.example.springboot.test.demo.Service.EmployeeService;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CallbackClientService callbackClientService;

    // dependency injection example
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CallbackClientService callbackClientService) {
        this.employeeRepository = employeeRepository;
        this.callbackClientService = callbackClientService;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()) {
            throw new RuntimeException("Employee already exists with given email: " + employee.getEmail());
        }
        Employee saved = employeeRepository.save(employee);
        System.out.println("Employee saved: " + saved);
        this.callbackClientService.notifyExternalService(saved);
        System.out.println("notifyExternalService method called");
        return saved;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee,long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException());

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}