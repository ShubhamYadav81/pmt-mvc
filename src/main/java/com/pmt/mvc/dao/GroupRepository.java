package com.pmt.mvc.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pmt.mvc.model.*;

public interface GroupRepository extends CrudRepository<Group, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Group g SET g.groupName = :newGroupName WHERE g.groupName = :oldGroupName")
	void updateGroupName(String oldGroupName, String newGroupName);

	boolean existsByGroupName(String grpName);

	Optional<Group> findByGroupName(String grpName);
}
