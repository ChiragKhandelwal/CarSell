package com.example.sellcar.services.customer;

import com.example.sellcar.dto.BidDTO;
import com.example.sellcar.dto.CarDTO;
import com.example.sellcar.dto.SearchCarDTO;
import com.example.sellcar.entity.Bid;
import com.example.sellcar.entity.Car;
import com.example.sellcar.entity.User;
import com.example.sellcar.repository.BidRepository;
import com.example.sellcar.repository.CarRepository;
import com.example.sellcar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BidRepository bidRepository;

    @Override
    public boolean createCar(CarDTO carDTO) {

        Optional<User> optionalUser = userRepository.findById(carDTO.getUserId());
        if (optionalUser.isPresent()){
        Car car = new Car();
        car.setName(carDTO.getName ());
        car. setBrand (carDTO.getBrand());
        car. setPrice (carDTO.getPrice());
        car.setDescription(carDTO. getDescription());
        car. setColor (carDTO.getColor());
        car.setTransmission(car.getTransmission());
        car.setSold(false);
        car .setManufacturingYear(carDTO.getYear());
            try {
                car .setImg(carDTO.getImg().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            car .setUser (optionalUser.get());
        carRepository.save (car);
        return true;
        }


        return false;
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDTO).collect(Collectors.toList());

    }

    @Override
    public CarDTO getCarById(Long id) {
        return carRepository.findById(id).map(Car::getCarDTO).orElse(null);

    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public boolean updateCar(Long carId, CarDTO carDTO) {
        Optional<Car> byId = carRepository.findById(carId);
        Optional<User> optionalUser = userRepository.findById(carDTO.getUserId());
        if(byId.isPresent()){
            Car car = new Car();
            car.setName(carDTO.getName ());
            car. setBrand (carDTO.getBrand());
            car. setPrice (carDTO.getPrice());
            car.setDescription(carDTO. getDescription());
            car. setColor (carDTO.getColor());
            car.setTransmission(car.getTransmission());
            car.setSold(false);
            car .setManufacturingYear(carDTO.getYear());
            try {
                car .setImg(carDTO.getImg().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            car .setUser (optionalUser.get());
            carRepository.save (car);
            return true;

        }


        return false;
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

    @Override
    public boolean bidCar(BidDTO dto) {
        Optional<Car> optionalCar=carRepository.findById(dto.getCarId());
        Optional<User> optionalUser=userRepository.findById(dto.getUserId());
        if(optionalCar.isPresent()&& optionalUser.isPresent()){
            Car car=optionalCar.get();
            User user=optionalUser.get();
            Bid bid=Bid.builder()
                    .car(car)
                    .price(car.getPrice())
                    .user(user)
                    .build();

            bidRepository.save(bid);

            return true;
        }
        return false;
    }

    @Override
    public List<BidDTO> getBidsForUser(Long userId) {
        return bidRepository.findAllByUserId(userId).stream().map(Bid::getBidDT0).collect(Collectors.toList());


    }
}
