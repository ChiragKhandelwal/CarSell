package com.example.sellcar.services.admin;

import com.example.sellcar.dto.CarDTO;
import com.example.sellcar.dto.SearchCarDTO;
import com.example.sellcar.entity.Car;
import com.example.sellcar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    CarRepository carRepository;
    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDTO).collect(Collectors.toList());

    }

    public CarDTO getCarById(Long id){
         Optional<Car> optional =carRepository.findById(id);
        return optional.map(Car::getCarDTO).orElse(null);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDTO> getCars(SearchCarDTO dto) {

        Car car = new Car();
        car. setType (dto.getType());
        car. setColor (dto.getColor());
        car.setType(dto.getType());

        ExampleMatcher matcher=ExampleMatcher.matchingAll()
                .withMatcher("brand",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("color",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Car> example=Example.of(car,matcher);

        return carRepository.findAll(example).stream().map(Car::getCarDTO).collect(Collectors.toList());
    }
}
