package com.tavant.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavant.springboot.model.Skill;
import com.tavant.springboot.repository.SkillRepository;

@Service("skillService")
@Transactional
public class SkillServiceImpl implements SkillService {
	@Autowired
	private SkillRepository skillRepository;

	@Override
	public List<Skill> findAllSkills() {
		return (List<Skill>) skillRepository.findAll();
	}

}
