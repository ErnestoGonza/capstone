package com.ernestogonzalez.tanititourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ernestogonzalez.tanititourism.entity.Listing;
import com.ernestogonzalez.tanititourism.entity.RestaurantsAndNightlife;
import com.ernestogonzalez.tanititourism.repository.RestaurantsAndNightlifeRepository;

@Service
public class RestaurantsAndNightlifeService {

    private final RestaurantsAndNightlifeRepository restaurantsAndNightlifeRepository;

    @Autowired
    public RestaurantsAndNightlifeService(RestaurantsAndNightlifeRepository restaurantsAndNightlifeRepository) {
        this.restaurantsAndNightlifeRepository = restaurantsAndNightlifeRepository;
    }

    public Page<RestaurantsAndNightlife> getAllRestaurantsAndNightlife(int page, int size) {
        return restaurantsAndNightlifeRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Page<RestaurantsAndNightlife> findByDineType(String dineTypeName, int page, int size) {
        return restaurantsAndNightlifeRepository.findByDineTypeTypeName(dineTypeName, PageRequest.of(page - 1, size));
    }

    public Page<RestaurantsAndNightlife> findByRegion(String regionName, int page, int size) {

        return restaurantsAndNightlifeRepository.findByRegionName(regionName, PageRequest.of(page - 1, size));
    }

    public Page<RestaurantsAndNightlife> findByDineTypeAndRegion(String dineTypeName, String regionName, int page, int size) {
        return restaurantsAndNightlifeRepository.findByDineTypeTypeNameAndRegionName(dineTypeName, regionName, PageRequest.of(page - 1, size));
    }



}
