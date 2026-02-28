package Main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Main.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
