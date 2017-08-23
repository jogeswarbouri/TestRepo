package com.tavant.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Deal;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

	public Deal findByReviewType(String reviewType);

	@Query("select c.status from Deal c where c.dealId = :dealId")
	public String findByStatus(@Param("dealId") Long dealId);

	public Deal findByDealId(long dealId);

}
