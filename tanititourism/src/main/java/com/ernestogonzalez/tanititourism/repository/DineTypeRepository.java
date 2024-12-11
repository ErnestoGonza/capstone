package com.ernestogonzalez.tanititourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ernestogonzalez.tanititourism.entity.DineType;

import java.util.Optional;

public interface DineTypeRepository extends JpaRepository<DineType, Long> {

    Optional<DineType> findByTypeName (String typeName);
}
