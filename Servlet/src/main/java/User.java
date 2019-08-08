import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.File;

@Setter@Getter@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String fio;

    private String phone;

    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
