package Main.service.serviceImpl;

import org.springframework.stereotype.Service;

import Main.entity.Insurance;
import Main.entity.Patient;
import Main.repository.InsuranceRepository;
import Main.repository.PatientRepository;
import Main.service.InsuranceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor /*you must declare all the instance variables as final because you are using @RequiredArgsConstructor*/
public class InsuranceServiceImpl implements InsuranceService {
	
	private final InsuranceRepository insuranceRepository;
	private final PatientRepository patientRepository;
	
	@Override
	@Transactional /*Dirty Checking is allowed in @Transactional Context*/
	public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with patient id : " + patientId));
		
		patient.setInsurance(insurance);
		insurance.setPatient(patient); //  to maintain bi-directional consistency
		
		return patient;
	}
}
