package Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import Main.EnterSpringBootAppliction;
import Main.entity.Appointment;
import Main.entity.Insurance;
import Main.entity.Patient;
import Main.service.AppointmentService;
import Main.service.InsuranceService;

@SpringBootTest(classes = EnterSpringBootAppliction.class)
public class InsuranceTests extends AbstractTestNGSpringContextTests {
	
	@Autowired 
	private InsuranceService insuranceService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Test
	public void testInsurance() {
		Insurance insurance = Insurance.builder()
				.policyNumber("HDFC_1234")
				.provider("HDFC")
				.validUntil(LocalDate.of(2030, 12, 12))
				.build();
		
		/**
		 * In @Transactional Context, we need to save before moving to the Persistence Context
		 * and over here that is taken care by cascade defined in Patient.java entity
		 */
		
		Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
		System.out.println(patient);
	}
	
	@Test
	public void testCreateAppointment() {
		Appointment appointment = Appointment.builder()
				.appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
				.reason("Nothing")
				.build();
		
		var newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);
		
		System.out.println(newAppointment);
		
		var updatedAppointment = appointmentService.reassignAppointmentToAnotherDoctor(appointment.getId(), 3L);
	
		System.out.println(updatedAppointment);
	}
	
}
