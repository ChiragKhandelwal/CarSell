package com.example.sellcar.controllers;

import com.example.sellcar.dto.BidDTO;
import com.example.sellcar.dto.CarDTO;
import com.example.sellcar.dto.SearchCarDTO;
import com.example.sellcar.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/car/add")
    public ResponseEntity<?> addCar(@ModelAttribute CarDTO carDTO) throws IOException {
        boolean success = customerService.createCar(carDTO);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getAllCars()

    {
        return ResponseEntity.ok(customerService.getAllCars());

    }

    @GetMapping("car/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable("id") Long id){
        CarDTO dto=customerService.getCarById(id);
        if(dto!=null){
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("car/{id}")
    public void deleteCar(@PathVariable("id") Long id){
        customerService.deleteCarById(id);
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<?> updateCar(@PathVariable("id") Long id,@ModelAttribute CarDTO carDTO) throws IOException {
        boolean success = customerService.updateCar(id,carDTO);
        if (success) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    @GetMapping("/car/search")
        public ResponseEntity<List<CarDTO>> searchCars(@ModelAttribute SearchCarDTO dto){
        return ResponseEntity.ok(customerService.getCars(dto));
        }


    @PostMapping("/car/bid")
    public ResponseEntity<?> bidCar(@ModelAttribute BidDTO carDTO) throws IOException {
        boolean success = customerService.bidCar(carDTO);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("bids/{userId}")
    public ResponseEntity<List<BidDTO>> getBids(@PathVariable("id") Long id){
        return ResponseEntity.ok(customerService.getBidsForUser(id));
    }

}