package com.tavant.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tavant.springboot.bo.UserDetailsBO;

@Repository("customDao")
public class HibernateCustomDao implements CustomDao {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllSkills(Long revId, Long taskId) {
		String sql = "select s.skill_name from review r, task t, task_skill ts, skills s "
				+ "where r.rev_id = t.rev_id " + "and t.task_id = ts.task_id " + "and s.skill_id= ts.skill_id "
				+ "and r.rev_id =? " + "and t.task_id=? ";
		Query query = manager.createNativeQuery(sql);
		query.setParameter(1, revId);
		query.setParameter(2, taskId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findByUsersInSkill(String skill) {
		String sql = "select u.user_name from skills s, user u, user_skill us " + "where s.skill_id = us.skill_id "
				+ "and us.user_id= u.user_id " + "and s.skill_name = ? ";
		Query query = manager.createNativeQuery(sql);
		query.setParameter(1, skill);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getUserByTaskDetails(String username) {
		String sql = "select distinct d.deal_id, r.review_type, t.task_name, t.sla, d.priority, d.status, art.ID_ "
				+ "from " + "task t, " + "review r, " + "dealsetup d, act_ru_task art, "
				+ "(select d.task_id as taskid, d.deal_id as dealid, art.ID_ as ataskid "
				+ "from act_ru_identitylink ari, " + "act_ru_task art, " + "act_ru_variable ara, " + "dealsetup d "
				+ "where ari.TASK_ID_ = art.ID_ " + "and art.EXECUTION_ID_=ara.EXECUTION_ID_ " + "and ari.USER_ID_=? "
				+ "and ara.TEXT2_= d.deal_id) deal_data " + "where t.task_id = deal_data.taskid "
				+ "and d.deal_id = deal_data.dealid "
				+ "and art.ID_ = deal_data.ataskid "
				+ "and t.rev_id = r.rev_id "
				+ "order by d.priority ASC";

		Query query = manager.createNativeQuery(sql);
		query.setParameter(1, username);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetailsBO> getAllDeal() {
		String sql = "select distinct d.deal_id, r.review_type, t.task_name, t.sla, d.priority, d.status, art.ID_, d. claim_created_time_stamp, d.claim_completed_time_stamp, d.username "
				+ "from " + "task t, " + "review r, " + "dealsetup d, act_ru_task art, "
				+ "(select d.task_id as taskid, d.deal_id as dealid, art.ID_ as ataskid "
				+ "from act_ru_identitylink ari, " + "act_ru_task art, " + "act_ru_variable ara, " + "dealsetup d "
				+ "where ari.TASK_ID_ = art.ID_ " + "and art.EXECUTION_ID_=ara.EXECUTION_ID_ "
				+ "and ara.TEXT2_= d.deal_id) deal_data " + "where t.task_id = deal_data.taskid "
				+ "and d.deal_id = deal_data.dealid " + "and art.ID_ = deal_data.ataskid " + "and t.rev_id = r.rev_id ";
		Query query = manager.createNativeQuery(sql);
		return query.getResultList();
	}

}
