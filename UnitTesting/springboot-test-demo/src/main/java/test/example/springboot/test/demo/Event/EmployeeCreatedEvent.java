package test.example.springboot.test.demo.Event;

import org.springframework.context.ApplicationEvent;
import test.example.springboot.test.demo.Model.Employee;

public class EmployeeCreatedEvent extends ApplicationEvent {
    private Employee employee;

    public EmployeeCreatedEvent(Object source, Employee employee) {
        super(source);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }    
}
