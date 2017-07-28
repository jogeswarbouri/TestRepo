package com.tavant.mockdrill.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.mockdrill.model.Deal;


@Repository
public interface DealSetupRepository extends JpaRepository<Deal, Integer> {

   // Deal findByName(String name);

}
