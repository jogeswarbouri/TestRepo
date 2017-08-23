package com.tavant.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SKILLS")
public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_id")
	private Long skillId;

	@NotEmpty
	@Column(name = "skill_name", nullable = false)
	private String skillName;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "USER_SKILL", joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skill_id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
	private List<User> userSkills;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "TASK_SKILL", joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skill_id"), inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "task_id"))
	private List<Task> taskSkills;

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public List<User> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(List<User> userSkills) {
		this.userSkills = userSkills;
	}

	public List<Task> getTaskSkills() {
		return taskSkills;
	}

	public void setTaskSkills(List<Task> taskSkills) {
		this.taskSkills = taskSkills;
	}

	static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skillName=" + skillName + ", userSkills=" + userSkills + ", taskSkills="
				+ taskSkills + "]";
	}

}
