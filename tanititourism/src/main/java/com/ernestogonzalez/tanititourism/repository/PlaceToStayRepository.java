package com.ernestogonzalez.tanititourism.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ernestogonzalez.tanititourism.entity.Listing;
import com.ernestogonzalez.tanititourism.entity.PlaceToStay;

import java.util.Optional;

public interface PlaceToStayRepository extends JpaRepository<PlaceToStay, Long> {
    Page<PlaceToStay> findAll(Pageable pageable);
    Page<PlaceToStay> findByStayTypeTypeName(String typeName, Pageable pageable);
    Page<PlaceToStay> findByRegionName(String regionName, Pageable pageable);
    Page<PlaceToStay> findByStayTypeTypeNameAndRegionName(String typeName, String regionName, Pageable pageable);

    Optional<PlaceToStay> findByName(String name);



}
