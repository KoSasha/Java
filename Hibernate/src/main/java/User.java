import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Setter@Getter
@NoArgsConstructor@AllArgsConstructor
public class User {
    @Id@GeneratedValue(generator = "USER_G", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_G", sequenceName = "USER_S", allocationSize = 1)
    private Integer id;

    private String fio;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Lang> lang;

}
