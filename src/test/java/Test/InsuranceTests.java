package Test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import Main.entity.Insurance;
import Main.entity.Patient;
import Main.service.InsuranceService;

@SpringBootTest
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
		
		Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
		System.out.println(patient);
	}
	
}
