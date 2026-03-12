package edu.helenacollege.hctickets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.helenacollege.hctickets.model.UserApplicationRole;

public interface UserApplicationRoleRepository extends JpaRepository<UserApplicationRole, Integer> {
	List<UserApplicationRole> findByUserId(Integer userId);
}
