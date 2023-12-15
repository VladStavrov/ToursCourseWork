package com.example.servicemodule.s.bulder;

import com.example.datamodule.dto.tours.TourDTO;

public interface TourBuilder {
    public TourBuilder addHotel();
    public TourBuilder addCurrency();
    public TourBuilder addFood();
    public TourBuilder addInfo();
    public TourBuilder addImages();
    public TourDTO build();
}
