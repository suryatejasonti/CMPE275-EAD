package edu.sjsu.entertainmentbox.dao.impl;

import edu.sjsu.entertainmentbox.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
