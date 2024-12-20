package com.ernestogonzalez.tanititourism.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ernestogonzalez.tanititourism.entity.PlaceToStay;
import com.ernestogonzalez.tanititourism.entity.Region;
import com.ernestogonzalez.tanititourism.entity.StayType;
import com.ernestogonzalez.tanititourism.service.PlaceToStayService;
import com.ernestogonzalez.tanititourism.service.RegionService;
import com.ernestogonzalez.tanititourism.service.StayTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/places-to-stay")
public class PlaceToStayController {

    private final PlaceToStayService placeToStayService;
    private final StayTypeService stayTypeService;
    private final RegionService regionService;

    @Autowired
    public PlaceToStayController(PlaceToStayService placeToStayService, StayTypeService stayTypeService, RegionService regionService) {
        this.placeToStayService = placeToStayService;
        this.stayTypeService = stayTypeService;
        this.regionService = regionService;
    }

    @GetMapping("/type")
    public List<StayType> getStayTypes() {
        return stayTypeService.findAll();
    }

    @GetMapping("/region")
    public List<Region> getRegionTypes() {
        return regionService.findAll();
    }

    @GetMapping("/list")
    public Page<PlaceToStay> getPlacesToStay(@RequestParam(required = false, defaultValue = "all") String stayType,
                                             @RequestParam(required = false, defaultValue = "all") String region,
                                             @RequestParam(required = false, defaultValue = "1") int page,
                                             @RequestParam(required = false, defaultValue = "6") int size) {
        if(stayType.equals("all") && region.equals("all")) {
            return placeToStayService.getAllPlacesToStay(page, size);
        } else if (region.equals("all")) {
            return placeToStayService.findByStayType(stayType, page, size);
        } else if (stayType.equals("all")) {
            return placeToStayService.findByRegion(region, page, size);
        } else {
            return placeToStayService.findByStayTypeAndRegion(stayType, region, page, size);
        }
    }
}
