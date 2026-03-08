package Main.security.securityService.securityServiceImpl;

import Main.dto.LoginRequestDto;
import Main.dto.LoginResponseDto;
import Main.dto.SignUpRequestDto;
import Main.dto.SignUpResponseDto;
import Main.entity.User;
import Main.entity.type.AuthProviderType;
import Main.repository.UserRepository;
import Main.security.AuthUtil;
import Main.security.securityService.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }

    @Override
    public SignUpResponseDto signup(LoginRequestDto signUpRequestDto) {
        User user = userRepository.findByUsername(signUpRequestDto.getUsername()).orElse(null);

        if (user != null) throw new IllegalArgumentException("User already exists");

        user = userRepository.save(
                User.builder()
                        .username(signUpRequestDto.getUsername())
                        .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                        .build()
        );

        return new SignUpResponseDto(user.getId(), user.getUsername());
    }

    @Override
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
        // fetch providerType and providerId
        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);

        String email = oAuth2User.getAttribute("email");

        User emailUser = userRepository.findByUsername(email).orElse(null);

        if (user == null && emailUser == null) {
            // signup flow
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            SignUpResponseDto signUpResponseDto = signup(new LoginRequestDto(username, null));
        }

        // save the providerType and providerId info with the user


        // if the user has an account, then directly login otherwise signup and then login


    }
}
