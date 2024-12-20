package com.ernestogonzalez.tanititourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ernestogonzalez.tanititourism.entity.DineType;
import com.ernestogonzalez.tanititourism.repository.DineTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DineTypeService {

    private final DineTypeRepository dineTypeRepository;

    @Autowired
    public DineTypeService(DineTypeRepository dineTypeRepository) {
        this.dineTypeRepository = dineTypeRepository;
    }

    public void saveAll(List<DineType> dineTypes) {
        dineTypeRepository.saveAll(dineTypes);
    }

    public List<DineType> findAll() {
        return dineTypeRepository.findAll();
    }

    public DineType getDineTypeById(Long id) {
        Optional<DineType> optionalDineType = dineTypeRepository.findById(id);

        if(optionalDineType.isPresent()) {
            return optionalDineType.get();
        } else {
            throw new RuntimeException("Dine Type not found for id :: " + id);
        }
    }

    public DineType findByTypeName(String typeName) {
        Optional<DineType> optionalDineType = dineTypeRepository.findByTypeName(typeName);

        if(optionalDineType.isPresent()) {
            return optionalDineType.get();
        }else {
            throw new RuntimeException("Dine Type not found for type name :: " + typeName);
        }
    }
}
