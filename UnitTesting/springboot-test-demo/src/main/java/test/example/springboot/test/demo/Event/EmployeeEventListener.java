package test.example.springboot.test.demo.Event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import test.example.springboot.test.demo.Event.EmployeeCreatedEvent;

@Component
public class EmployeeEventListener {

	@EventListener
	public void handleEmployeeCreatedEvent(EmployeeCreatedEvent event) {
		// Callback logic here
		System.out.println("Employee created: " + event.getEmployee());
	}
}
