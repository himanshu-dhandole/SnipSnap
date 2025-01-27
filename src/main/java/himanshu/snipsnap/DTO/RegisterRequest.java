package himanshu.snipsnap.DTO;


import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    private String name ;
    private String email ;
    private String password ;
    private Set<String> role ;
}
