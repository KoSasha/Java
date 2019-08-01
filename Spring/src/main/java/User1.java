import lombok.*;

import javax.persistence.*;


@Entity
@Setter@Getter@NoArgsConstructor
@AllArgsConstructor
public class User1 {
    @Id
    private Integer id;

    private String fio;

    private String phone;
}
