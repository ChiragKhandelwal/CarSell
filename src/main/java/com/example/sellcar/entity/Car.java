package com.example.sellcar.entity;

import com.example.sellcar.dto.CarDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String type;
    private String transmission;
    private String color;
    private Date manufacturingYear;
    private Boolean sold;

    @Lob
    private String description;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    public CarDTO getCarDTO() {
        CarDTO carDto = new CarDTO();
        carDto.setId(id);
        carDto.setName(name);
        carDto.setBrand(brand);
        carDto.setType(type);
        carDto.setTransmission(transmission);
        carDto.setColor(color);
        carDto.setYear(manufacturingYear);
        carDto.setSold(sold);
        carDto.setDescription(description);

        carDto.setPrice(price);
        carDto.setReturnedImg(img);

        return carDto;
    }
}