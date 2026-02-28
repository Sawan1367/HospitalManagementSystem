package Main.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime appointmentTime;
	
	@Column(length = 500)
	private String reason;
	
	@ManyToOne /*Many Appointments to One Patient*/
	@ToString.Exclude
	@JoinColumn(name = "appointment_patient_id", nullable = false) // patient is required and not nullable
	private Patient patient;
	
	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "appointment_doctor_id", nullable = false)
	private Doctor doctor;
}
