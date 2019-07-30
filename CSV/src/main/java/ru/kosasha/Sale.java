package ru.kosasha;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@JsonAutoDetect
@Getter@Setter@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    private String title;

    private String price;
}