package com.ernestogonzalez.tanititourism.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ernestogonzalez.tanititourism.dto.PlaceToStayDto;
import com.ernestogonzalez.tanititourism.entity.PlaceToStay;
import com.ernestogonzalez.tanititourism.entity.Region;
import com.ernestogonzalez.tanititourism.entity.StayType;
import com.ernestogonzalez.tanititourism.service.AdminPlaceToStayService;
import com.ernestogonzalez.tanititourism.service.RegionService;
import com.ernestogonzalez.tanititourism.service.ResponseHelperService;
import com.ernestogonzalez.tanititourism.service.StayTypeService;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/places-to-stay")
public class AdminPlaceToStayController {

    private final AdminPlaceToStayService adminPlaceToStayService;
    private final RegionService regionService;
    private final StayTypeService stayTypeService;
    private final ResponseHelperService responseHelperService;


    @GetMapping("/list")
    public Page<PlaceToStay> getAllPlacesToDo(@RequestParam(required = false, defaultValue = "1") int page,
                                              @RequestParam(required = false, defaultValue = "10") int size) {
        return adminPlaceToStayService.getAllPlacesToStay(page, size);
    }

    @GetMapping("/listing-detail/{id}")
    public Optional<PlaceToStay> getPlaceToStay(@PathVariable("id") Long id) {
        return adminPlaceToStayService.getPlaceToStay(id);
    }

    @DeleteMapping("/delete-listing/{id}")
    public ResponseEntity<Void> deletePlaceToStay(@PathVariable Long id) {
        adminPlaceToStayService.deletePlaceToStay(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new-listing")
    public ResponseEntity<?> addPlaceToStay(@Valid @RequestBody PlaceToStayDto placeToStayDto,
                                            @RequestHeader("X-Username") String username,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }

        PlaceToStay placeToStay = convertDtoToPlaceToStay(placeToStayDto);
        Region region = regionService.getRegionById(placeToStayDto.getRegionId());
        StayType stayType = stayTypeService.getStayTypeById(placeToStayDto.getStayTypeId());
        PlaceToStay savedPlaceToStay = adminPlaceToStayService.addPlaceToStay(placeToStay, region, stayType, username);
        return ResponseEntity.ok(savedPlaceToStay);

    }

    @PutMapping("/update-listing/{id}")
    public ResponseEntity<?> updatePlaceToStay(@PathVariable Long id, @Valid @RequestBody PlaceToStayDto placeToStayDto,
                                               @RequestHeader("X-Username") String username,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        PlaceToStay placeToStay = convertDtoToPlaceToStay(placeToStayDto);
        Region region = regionService.getRegionById(placeToStayDto.getRegionId());
        StayType stayType = stayTypeService.getStayTypeById(placeToStayDto.getStayTypeId());
        PlaceToStay savedPlaceToStay = adminPlaceToStayService.updatePlaceToStay(id, placeToStay, region, stayType, username);
        return ResponseEntity.ok(savedPlaceToStay);
    }


    private PlaceToStay convertDtoToPlaceToStay(PlaceToStayDto placeToStayDto) {
        PlaceToStay placeToStay = new PlaceToStay();
        placeToStay.setName(placeToStayDto.getName());
        placeToStay.setDescription(placeToStayDto.getDescription());
        placeToStay.setCost(placeToStayDto.getCost());
        placeToStay.setImageUrl(placeToStayDto.getImageUrl());
        placeToStay.setImageAltText(placeToStayDto.getImageAltText());
        placeToStay.setPhone(placeToStayDto.getPhone());
        return placeToStay;
    }
}
