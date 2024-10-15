package com.example.sellcar.dto;

import com.example.sellcar.enums.BidStatus;
import lombok.Data;

@Data
public class BidDTO {

    private Long id;

    private Long price;
    private BidStatus bidStatus;
    private Long userId;
    private Long carId;

    private String sellerName;
    private String carName;
    private String carBrand;
    private String email;


}
