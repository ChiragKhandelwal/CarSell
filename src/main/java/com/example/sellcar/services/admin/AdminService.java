package com.example.sellcar.services.admin;

import com.example.sellcar.dto.CarDTO;
import com.example.sellcar.dto.SearchCarDTO;

import java.util.List;

public interface AdminService {
    List<CarDTO> getAllCars();
    public CarDTO getCarById(Long id);
    void deleteCarById(Long id);

    List<CarDTO> getCars(SearchCarDTO dto);
}
