package com.ernestogonzalez.tanititourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ernestogonzalez.tanititourism.entity.Region;
import com.ernestogonzalez.tanititourism.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService (RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public void saveAll(List<Region> regions) {
        regionRepository.saveAll(regions);
    }
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region getRegionById(Long id){
        Optional<Region> optionalRegion = regionRepository.findById(id);

        if(optionalRegion.isPresent()) {
            return optionalRegion.get();
        } else {
            throw new RuntimeException("Region not found for id :: " + id);
        }
    }

    public Region findByName(String name) {
        Optional<Region> optionalRegion = regionRepository.findByName(name);

        if(optionalRegion.isPresent()) {
            return optionalRegion.get();
        } else {
            throw new RuntimeException("Region not found for id :: " + name);
        }
    }

}
