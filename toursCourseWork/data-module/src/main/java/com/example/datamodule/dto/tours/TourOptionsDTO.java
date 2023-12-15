package com.example.datamodule.dto.tours;


import com.example.datamodule.dto.tours.info.FoodDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourOptionsDTO {
    String dateStart;
    String dateEnd;
    int nights;
    int total;
    String typeRoom;
    FoodDTO food;
    int peoples;
}
