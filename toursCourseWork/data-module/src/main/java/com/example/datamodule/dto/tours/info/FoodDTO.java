package com.example.datamodule.dto.tours.info;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    Long id;
    String foodType;
    String foodDescription;

}
