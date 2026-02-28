package Main.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 100)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "department_head_doctor_id")
	private Doctor headDoctor;
	
	@ManyToMany
	@JoinTable(
			name = "department_doctor",
			joinColumns = @JoinColumn(name = "department_id"), // owning side
			inverseJoinColumns = @JoinColumn(name = "doctor_id") // inverse side 
		)
	private Set<Doctor> doctors = new HashSet<>() ;
}
