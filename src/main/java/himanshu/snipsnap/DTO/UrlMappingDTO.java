package himanshu.snipsnap.DTO;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlMappingDTO {
    private long id ;
    private String originalURL ;
    private String shortURL ;
    private int clickCount ;
    private LocalDateTime crearedDate  ;
    private String name ;
}
