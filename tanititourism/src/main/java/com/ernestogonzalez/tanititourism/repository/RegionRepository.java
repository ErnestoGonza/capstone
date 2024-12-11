package com.ernestogonzalez.tanititourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ernestogonzalez.tanititourism.entity.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findByName (String name);

}
