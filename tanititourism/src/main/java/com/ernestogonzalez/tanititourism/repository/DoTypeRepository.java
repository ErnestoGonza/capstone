package com.ernestogonzalez.tanititourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ernestogonzalez.tanititourism.entity.DoType;

import java.util.List;
import java.util.Optional;

public interface DoTypeRepository extends JpaRepository<DoType, Long> {

    Optional<DoType> findByTypeName (String typeName);

}
