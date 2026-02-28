package Main.service;

import java.util.List;

import Main.entity.Patient;

public interface PatientService {
	Patient getPatientById(Long patientId);
	List<Patient> getAllPatients(Integer pageNumber, Integer pageSize);
}
