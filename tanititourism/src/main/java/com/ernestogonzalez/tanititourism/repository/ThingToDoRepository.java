package com.ernestogonzalez.tanititourism.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ernestogonzalez.tanititourism.entity.Listing;
import com.ernestogonzalez.tanititourism.entity.ThingToDo;

import java.util.Optional;


public interface ThingToDoRepository extends JpaRepository<ThingToDo, Long> {

    Page<ThingToDo> findAll(Pageable pageable);
    Page<ThingToDo> findByDoTypesTypeName(String typeName, Pageable pageable);
    Page<ThingToDo> findByRegionName(String regionName, Pageable pageable);
    Page<ThingToDo> findByDoTypesTypeNameAndRegionName(String typeName, String regionName, Pageable pageable);
    Optional<ThingToDo> findByName(String name);

}
