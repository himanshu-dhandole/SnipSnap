package himanshu.snipsnap.service;

import himanshu.snipsnap.models.Users;
import himanshu.snipsnap.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepo usersRepo ;
    private final PasswordEncoder passwordEncoder ;

    public Users registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepo.save(user) ;
    }
}
