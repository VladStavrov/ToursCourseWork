package com.example.datamodule.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {
    private int peoples;
    private String startDate;
    private String endDate;
    private Long countryId;
    private int nights;
    private int stars;
}
