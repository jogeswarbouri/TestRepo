package com.tavant.mockdrill.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tavant.mockdrill.model.LoanData;


@Repository
public interface LoanRepository extends JpaRepository<LoanData, Integer> {

	public LoanData findByLoanId(String loanId);

}
