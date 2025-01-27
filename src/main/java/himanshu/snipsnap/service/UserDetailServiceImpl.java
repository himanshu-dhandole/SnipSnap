package himanshu.snipsnap.service;

import himanshu.snipsnap.models.Users;
import himanshu.snipsnap.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsersRepo usersRepo ;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Users user = usersRepo.findByName(name)
                .orElseThrow(()->new UsernameNotFoundException("username not found" + name)) ;

        return UserDetailsImpl.build(user);
    }
}
