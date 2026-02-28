package Main.service;

import Main.entity.Insurance;
import Main.entity.Patient;

public interface InsuranceService {
	Patient assignInsuranceToPatient(Insurance insurance, Long patientId);

	Patient disAssociateInsuranceFromParent(Long patientId);
}
