package Main.security.securityService;

import Main.dto.LoginRequestDto;
import Main.dto.LoginResponseDto;
import Main.dto.SignUpResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    SignUpResponseDto signup(LoginRequestDto signUpRequestDto);
}
