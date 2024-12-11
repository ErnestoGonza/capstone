package com.ernestogonzalez.tanititourism.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ernestogonzalez.tanititourism.entity.StayType;

import java.util.Optional;

public interface StayTypeRepository extends JpaRepository<StayType, Long> {

    Optional<StayType> findByTypeName (String typeName);

}
