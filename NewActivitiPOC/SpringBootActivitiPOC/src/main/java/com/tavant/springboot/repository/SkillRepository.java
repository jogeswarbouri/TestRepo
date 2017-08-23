package com.tavant.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

}
