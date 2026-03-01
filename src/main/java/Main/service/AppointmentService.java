package Main.service;

import java.util.List;

import Main.dto.AppointmentResponseDto;
import Main.dto.CreateAppointmentRequestDto;
import Main.entity.Appointment;

public interface AppointmentService {

	Appointment reassignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId);

	List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId);

	AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto);

}
