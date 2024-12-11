package com.ernestogonzalez.tanititourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ernestogonzalez.tanititourism.entity.Listing;
import com.ernestogonzalez.tanititourism.entity.PlaceToStay;
import com.ernestogonzalez.tanititourism.repository.PlaceToStayRepository;

@Service
public class PlaceToStayService {

    private final PlaceToStayRepository placeToStayRepository;

    @Autowired
    public PlaceToStayService(PlaceToStayRepository placeToStayRepository) {
        this.placeToStayRepository = placeToStayRepository;
    }

    public Page<PlaceToStay> getAllPlacesToStay(int page, int size) {
        return placeToStayRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Page<PlaceToStay> findByStayType(String stayTypeName, int page, int size) {
        return placeToStayRepository.findByStayTypeTypeName(stayTypeName, PageRequest.of(page - 1, size));
    }

    public Page<PlaceToStay> findByRegion(String regionName, int page, int size) {
        return placeToStayRepository.findByRegionName(regionName, PageRequest.of(page - 1, size));
    }

    public Page<PlaceToStay> findByStayTypeAndRegion(String stayTypeName, String regionName, int page, int size) {
        return placeToStayRepository.findByStayTypeTypeNameAndRegionName(stayTypeName, regionName, PageRequest.of(page - 1, size));
    }
}
