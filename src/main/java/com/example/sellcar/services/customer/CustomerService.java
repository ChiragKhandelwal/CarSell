package com.example.sellcar.services.customer;

import com.example.sellcar.dto.BidDTO;
import com.example.sellcar.dto.CarDTO;
import com.example.sellcar.dto.SearchCarDTO;

import java.util.List;

public interface CustomerService {

    boolean createCar(CarDTO dto);

    List<CarDTO> getAllCars();

    CarDTO getCarById(Long id);

    void deleteCarById(Long id);
    boolean updateCar(Long carId,CarDTO dto);

    List<CarDTO> getCars(SearchCarDTO dto);

    boolean bidCar(BidDTO dto);

List<BidDTO> getBidsForUser(Long userId);
}
