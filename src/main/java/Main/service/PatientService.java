package Main.service;

import java.util.List;

import Main.dto.PatientResponseDto;

public interface PatientService {
	PatientResponseDto getPatientById(Long patientId);
	List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize);
}
