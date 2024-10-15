package com.example.sellcar.controllers;

import com.example.sellcar.dto.CarDTO;
import com.example.sellcar.dto.SearchCarDTO;
import com.example.sellcar.services.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

@Autowired
    AdminService adminService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getAllCars()

    {
        return ResponseEntity.ok(adminService.getAllCars());

    }

    @GetMapping("car/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable("id") Long id){
        CarDTO dto=adminService.getCarById(id);
        if(dto!=null){
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("car/{id}")
    public void deleteCar(@PathVariable("id") Long id){
        adminService.deleteCarById(id);
    }

    @GetMapping("/car/search")
    public ResponseEntity<List<CarDTO>> searchCars(@ModelAttribute SearchCarDTO dto){
        return ResponseEntity.ok(adminService.getCars(dto));
    }
}
