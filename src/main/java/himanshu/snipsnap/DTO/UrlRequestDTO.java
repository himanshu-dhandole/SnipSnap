package himanshu.snipsnap.DTO;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UrlRequestDTO {
    @Column(nullable = false)
    private String originalURL;
}
