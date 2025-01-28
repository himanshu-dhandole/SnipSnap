package himanshu.snipsnap.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String originalUrl ;
    private String shortUrl ;
    private int clickCount ;
    private LocalDateTime createdDate ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user ;

    @OneToMany(mappedBy = "urlMapping")
    private List<ClickEvents> clickEvents = new ArrayList<>();

}
