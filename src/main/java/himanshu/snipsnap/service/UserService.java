package himanshu.snipsnap.service;

import himanshu.snipsnap.DTO.LoginRequest;
import himanshu.snipsnap.models.Users;
import himanshu.snipsnap.repository.UsersRepo;
import himanshu.snipsnap.security.jwtSecurity.JwtAuthenticationResponse;
import himanshu.snipsnap.security.jwtSecurity.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepo usersRepo ;
    private final PasswordEncoder passwordEncoder ;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil ;

    public Users registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepo.save(user) ;
    }

    public JwtAuthenticationResponse verifyUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails) ;
        return new JwtAuthenticationResponse(token) ;
    }
}
