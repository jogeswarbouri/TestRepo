package com.tavant.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("select t from Task t where t.review.revId=:revId")
	public List<Task> getSelectedReviewTask(@Param("revId") Long revId);

	@Query("select t from Task t where t.review.review=:review")
	public List<Task> findByTaskNames(String review);

}
