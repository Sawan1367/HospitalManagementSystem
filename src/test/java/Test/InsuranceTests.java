package Test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import Main.EnterSpringBootAppliction;
import Main.entity.Insurance;
import Main.entity.Patient;
import Main.service.InsuranceService;

@SpringBootTest(classes = EnterSpringBootAppliction.class)
public class InsuranceTests extends AbstractTestNGSpringContextTests {
	
	@Autowired 
	private InsuranceService insuranceService;
	
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
	
}
