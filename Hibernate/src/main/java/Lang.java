import lombok.*;

import javax.persistence.*;

@Entity
@Setter@Getter
public class Lang {
    @Id@GeneratedValue(generator = "LANG_G",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "LANG_G", sequenceName = "LANG_S", allocationSize = 1)
    @Column(nullable = false)
    private Integer id;

    private String language;
}
