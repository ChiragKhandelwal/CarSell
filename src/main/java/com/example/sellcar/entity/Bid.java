package com.example.sellcar.entity;

import com.example.sellcar.dto.BidDTO;
import com.example.sellcar.enums.BidStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Builder
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "user _id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "car_id", nullable = false)
    @OnDelete (action = OnDeleteAction. CASCADE)
    @JsonIgnore
    private Car car;

    private BidStatus status;

    public BidDTO getBidDT0() {
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(id);
        bidDTO.setPrice(price);
        bidDTO.setCarId(car.getId());
        bidDTO.setCarName(car.getName());
        bidDTO.setCarBrand(car.getBrand());
        bidDTO.setBidStatus(status);
        bidDTO.setEmail(user.getEmail());

        bidDTO.setSellerName(car.getUser().getName());
        return bidDTO;

    }
}
