package Main.service;

import java.util.List;

import Main.dto.DoctorResponseDto;
import Main.dto.OnboardDoctorRequestDto;

public interface DoctorService {

	List<DoctorResponseDto> getAllDoctors();

    DoctorResponseDto onBoardNewDoctor(OnboardDoctorRequestDto onboardDoctorRequestDto);
}
