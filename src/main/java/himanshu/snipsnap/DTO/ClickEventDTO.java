package himanshu.snipsnap.DTO;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ClickEventDTO {

    private LocalDate clickDate ;
    private int clickCount ;

}
