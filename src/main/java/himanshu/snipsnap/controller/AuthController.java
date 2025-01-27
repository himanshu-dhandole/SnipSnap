package himanshu.snipsnap.controller;


import himanshu.snipsnap.DTO.LoginRequest;
import himanshu.snipsnap.DTO.RegisterRequest;
import himanshu.snipsnap.models.Users;
import himanshu.snipsnap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService ;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody RegisterRequest request) {
        Users user = new Users();
        user.setName(request.getName());
        user.setEmail(request.getEmail()) ;
        user.setPassword(request.getPassword()) ;
        user.setRole("ROLE_USER");

        userService.registerUser(user) ;
        return ResponseEntity.ok(user) ;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.verifyUser(request));
    }
}