package Main.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    String jwt;
    Long userId;

}
