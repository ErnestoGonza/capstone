package com.ernestogonzalez.tanititourism.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ernestogonzalez.tanititourism.entity.Listing;
import com.ernestogonzalez.tanititourism.entity.RestaurantsAndNightlife;

import java.util.Optional;

public interface RestaurantsAndNightlifeRepository extends JpaRepository<RestaurantsAndNightlife, Long> {

    Page<RestaurantsAndNightlife> findAll(Pageable pageable);
    Page<RestaurantsAndNightlife> findByDineTypeTypeName(String typeName, Pageable pageable);
    Page<RestaurantsAndNightlife> findByRegionName(String regionName, Pageable pageable);
    Page<RestaurantsAndNightlife> findByDineTypeTypeNameAndRegionName(String typeName, String regionName, Pageable pageable);
    Optional<RestaurantsAndNightlife> findByName(String name);

}
