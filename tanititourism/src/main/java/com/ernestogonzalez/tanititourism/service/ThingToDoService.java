package com.ernestogonzalez.tanititourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ernestogonzalez.tanititourism.entity.ThingToDo;
import com.ernestogonzalez.tanititourism.repository.ThingToDoRepository;

import java.util.List;

@Service
public class ThingToDoService {
    private final ThingToDoRepository thingToDoRepository;

    @Autowired
    public ThingToDoService(ThingToDoRepository thingToDoRepository) {
        this.thingToDoRepository = thingToDoRepository;
    }

    public Page<ThingToDo> getAllThingsToDo(int page, int size) {
        return thingToDoRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Page<ThingToDo> findByDoType(String doTypeName, int page, int size) {
        return thingToDoRepository.findByDoTypesTypeName(doTypeName, PageRequest.of(page - 1, size));
    }

    public Page<ThingToDo> findByRegion(String regionName,  int page, int size) {
        return thingToDoRepository.findByRegionName(regionName, PageRequest.of(page - 1, size));
    }

    public Page<ThingToDo> findByDoTypeAndRegion(String doTypeName, String regionName, int page, int size) {
        return thingToDoRepository.findByDoTypesTypeNameAndRegionName(doTypeName, regionName, PageRequest.of(page - 1, size));
    }
}
