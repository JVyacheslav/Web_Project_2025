package com.project.Web_Project.interfaces;

import com.project.Web_Project.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User save(User user);
}
