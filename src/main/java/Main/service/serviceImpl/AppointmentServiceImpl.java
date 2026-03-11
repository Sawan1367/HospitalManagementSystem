package Main.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import Main.dto.AppointmentResponseDto;
import Main.dto.CreateAppointmentRequestDto;
import Main.entity.Appointment;
import Main.entity.Doctor;
import Main.entity.Patient;
import Main.repository.AppointmentRepository;
import Main.repository.DoctorRepository;
import Main.repository.PatientRepository;
import Main.service.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
	
	private final AppointmentRepository appointmentRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final ModelMapper modelMapper;
	
	@Override
	@Transactional
	public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto) {
        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));
        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequestDto.getReason())
                .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
                .build();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        patient.getAppointments().add(appointment); // to maintain consistency

        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }
	
	@Override
	@Transactional
	@PreAuthorize("hasAuthority('appointment:write') or #doctorId == authentication.principal.id")
	public Appointment reassignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
		
		appointment.setDoctor(doctor);
		
		return appointment;
		
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN') OR (hasRole('DOCTOR') AND #doctorId == authentication.principal.id)")
	public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }
	
}
