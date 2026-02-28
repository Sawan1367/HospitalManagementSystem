package Main.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import Main.entity.type.BloodGroupType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@Table(
        name = "patient",
        uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name", "birthDate"})
        },
        indexes = {
                @Index(name = "idx_patient_birth_date", columnList = "birthDate")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * @Builder implements the Builder Pattern for object creation.
 * It allows you to create objects step-by-step in a readable way.
 * 
 * import lombok.Builder;
 * import lombok.Data;
 * 
 * @Data
 * @Builder
 * public class Student {
 *     private int id;
 *     private String name;
 *     private String course;
 * }
 */
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    //    @ToString.Exclude
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
    
    @OneToOne(cascade = CascadeType.ALL /*or {CascadeType.MERGE, CascadeType.PERSIST}*/)
    @JoinColumn(name = "patient_insurance_id") // Join owning side (parent)
    private Insurance insurance;
    
    @OneToMany(mappedBy = "patient"/*, fetch = FetchType.EAGER*/)
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();

}