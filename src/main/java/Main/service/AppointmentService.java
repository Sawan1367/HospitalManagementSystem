package Main.service;

import Main.entity.Appointment;

public interface AppointmentService {

	Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId);

	Appointment reassignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId);

}
