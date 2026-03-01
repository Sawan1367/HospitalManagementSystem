package Main.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppointmentResponseDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private DoctorResponseDto doctor;
//    private PatientResponseDto patient;
}