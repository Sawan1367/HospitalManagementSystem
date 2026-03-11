package Main.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import Main.entity.type.BloodGroupType;
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

    @OneToOne
    @MapsId
    /**
     * @MapsId annotation is used when two entities share the same primary key, usually in a One-to-One relationship.
     * Use the primary key of the parent entity as the primary key of this entity as well.
     *
     * Normally in relationships you have:
        * A primary key
        * A foreign key
     * But sometimes you want:
        * The foreign key to also be the primary key
     * That’s when @MapsId is used.
     */
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
    
    /**
     * orphanRemoval removes the child entities from when they are no longer being referenced by the parent, but the parent remains in the DB
     * 
     * CascadeType.REMOVE removes the child entities from the DB, only when the parent entity is removed
     */
    @OneToOne(cascade = CascadeType.ALL /*or {CascadeType.MERGE, CascadeType.PERSIST}*/, orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id") // Join owning side (parent)
    private Insurance insurance;
    
    @OneToMany(mappedBy = "patient", orphanRemoval = true, fetch = FetchType.EAGER) // It causes N+1 problem (it is getting auto-populated) - It can be solved with the help of custom queries
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();

}