package Main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Main.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
