package Main.service.serviceImpl;

import org.springframework.stereotype.Service;

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
	
	@Override
	@Transactional
	public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Cannot find doctor with doctor id : " + doctorId));
		Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Cannot find patient with patient id : " + patientId));
		
		if (appointment.getId() != null) throw new IllegalArgumentException("The appointment should not have been created previously");
		
		appointment.setPatient(patient);
		appointment.setDoctor(doctor);
		
		patient.getAppointments().add(appointment); // to maintain bi-directional consistency
		doctor.getAppointments().add(appointment); // to maintain bi-directional consistency
		
		return appointmentRepository.save(appointment);
	}
	
	@Override
	@Transactional
	public Appointment reassignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
		Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
		
		appointment.setDoctor(doctor);
		
		return appointment;
		
	}
	
}
