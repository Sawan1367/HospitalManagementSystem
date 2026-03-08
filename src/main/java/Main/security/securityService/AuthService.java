package Main.security.securityService;

import Main.dto.LoginRequestDto;
import Main.dto.LoginResponseDto;
import Main.dto.SignUpResponseDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    SignUpResponseDto signup(LoginRequestDto signUpRequestDto);

    void handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId);
}
