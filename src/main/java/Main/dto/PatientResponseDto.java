package Main.dto;

import java.time.LocalDate;

import Main.entity.type.BloodGroupType;
import lombok.Data;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}