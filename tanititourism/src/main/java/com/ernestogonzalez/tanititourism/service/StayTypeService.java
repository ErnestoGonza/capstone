package com.ernestogonzalez.tanititourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ernestogonzalez.tanititourism.entity.Region;
import com.ernestogonzalez.tanititourism.entity.StayType;
import com.ernestogonzalez.tanititourism.repository.StayTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StayTypeService {

    private final StayTypeRepository stayTypeRepository;

    @Autowired
    public StayTypeService(StayTypeRepository stayTypeRepository) {
        this.stayTypeRepository = stayTypeRepository;
    }

    public void saveAll(List<StayType> stayTypes) {
         stayTypeRepository.saveAll(stayTypes);
    }
    public List<StayType> findAll() {
        return stayTypeRepository.findAll();
    }

    public StayType getStayTypeById(Long id){
        Optional<StayType> optionalStayType = stayTypeRepository.findById(id);

        if(optionalStayType.isPresent()) {
            return optionalStayType.get();
        } else {
            throw new RuntimeException("Stay Type not found for id :: " + id);
        }
    }

    public StayType findByTypeName(String typeName) {
        Optional<StayType> optionalStayType = stayTypeRepository.findByTypeName(typeName);

        if(optionalStayType.isPresent()){
            return optionalStayType.get();
        } else {
            throw new RuntimeException("Stay Type not found for type name :: " + typeName);
        }
    }
}
