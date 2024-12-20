package com.ernestogonzalez.tanititourism.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ernestogonzalez.tanititourism.dto.RestaurantsAndNightlifeDto;
import com.ernestogonzalez.tanititourism.entity.DineType;
import com.ernestogonzalez.tanititourism.entity.Region;
import com.ernestogonzalez.tanititourism.entity.RestaurantsAndNightlife;
import com.ernestogonzalez.tanititourism.service.AdminRestaurantsAndNightlifeService;
import com.ernestogonzalez.tanititourism.service.DineTypeService;
import com.ernestogonzalez.tanititourism.service.RegionService;
import com.ernestogonzalez.tanititourism.service.ResponseHelperService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/restaurants-and-nightlife")
public class AdminRestaurantsAndNightlifeController {


    private final AdminRestaurantsAndNightlifeService adminRestaurantsAndNightlifeService;
    private final RegionService regionService;
    private final DineTypeService dineTypeService;
    private final ResponseHelperService responseHelperService;

    @GetMapping("/list")
    public Page<RestaurantsAndNightlife> getAllRestaurantsAndNightlife(@RequestParam(required = false, defaultValue = "1") int page,
                                                                       @RequestParam(required = false, defaultValue = "10") int size) {
        return adminRestaurantsAndNightlifeService.getAllRestaurantsAndNightlife(page, size);
    }

    @GetMapping("/listing-detail/{id}")
    public Optional<RestaurantsAndNightlife> getRestaurantAndNightlife(@PathVariable Long id) {
        return adminRestaurantsAndNightlifeService.getRestaurantAndNightlife(id);
    }

    @DeleteMapping("/delete-listing/{id}")
    public ResponseEntity<Void> deleteRestaurantsAndNightlife(@PathVariable Long id) {
        adminRestaurantsAndNightlifeService.deleteRestaurantAndNightlife(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new-listing")
    public ResponseEntity<?> addRestaurantAndNightlife(@Valid @RequestBody RestaurantsAndNightlifeDto restaurantsAndNightlifeDto,
                                                       @RequestHeader("X-Username") String username,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        RestaurantsAndNightlife restaurantsAndNightlife = convertDtoToRestaurantAndNightlife(restaurantsAndNightlifeDto);
        Region region = regionService.getRegionById(restaurantsAndNightlifeDto.getRegionId());
        DineType dineType = dineTypeService.getDineTypeById(restaurantsAndNightlifeDto.getDineTypeId());
        RestaurantsAndNightlife savedRestaurantAndNightlife = adminRestaurantsAndNightlifeService
                .addRestaurantAndNightlife(restaurantsAndNightlife, region, dineType, username);
        return ResponseEntity.ok(savedRestaurantAndNightlife);

    }

    @PutMapping("/update-listing/{id}")
    public ResponseEntity<?> updateRestaurantAndNightlife(@PathVariable Long id,
                                                          @Valid @RequestBody RestaurantsAndNightlifeDto restaurantsAndNightlifeDto,
                                                          @RequestHeader("X-Username") String username,
                                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        RestaurantsAndNightlife restaurantsAndNightlife = convertDtoToRestaurantAndNightlife(restaurantsAndNightlifeDto);
        Region region = regionService.getRegionById(restaurantsAndNightlifeDto.getRegionId());
        DineType dineType = dineTypeService.getDineTypeById(restaurantsAndNightlifeDto.getDineTypeId());
        RestaurantsAndNightlife savedRestaurantAndNightlife = adminRestaurantsAndNightlifeService
                .updateRestaurantAndNightlife(id, restaurantsAndNightlife, region, dineType, username);
        return ResponseEntity.ok(savedRestaurantAndNightlife);

    }

    private RestaurantsAndNightlife convertDtoToRestaurantAndNightlife(RestaurantsAndNightlifeDto restaurantAndNightlifeDto) {
        RestaurantsAndNightlife restaurantsAndNightlife = new RestaurantsAndNightlife();
        restaurantsAndNightlife.setName(restaurantAndNightlifeDto.getName());
        restaurantsAndNightlife.setDescription(restaurantAndNightlifeDto.getDescription());
        restaurantsAndNightlife.setCost(restaurantAndNightlifeDto.getCost());
        restaurantsAndNightlife.setImageUrl(restaurantAndNightlifeDto.getImageUrl());
        restaurantsAndNightlife.setImageAltText(restaurantAndNightlifeDto.getImageAltText());
        restaurantsAndNightlife.setPhone(restaurantAndNightlifeDto.getPhone());
        restaurantsAndNightlife.setAcceptsReservations(restaurantAndNightlifeDto.getAcceptsReservations());
        return restaurantsAndNightlife;
    }
}
