package test.example.springboot.test.demo.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import test.example.springboot.test.demo.Event.EmployeeCreatedEvent;
import test.example.springboot.test.demo.Model.Employee;

@Service
public class CallbackClientService {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public CallbackClientService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void notifyExternalService(Employee employee) {
        // Publish the EmployeeCreatedEvent
        EmployeeCreatedEvent event = new EmployeeCreatedEvent(this, employee);
        eventPublisher.publishEvent(event);
    }
}