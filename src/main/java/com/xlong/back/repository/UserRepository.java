package com.xlong.back.repository;
import com.xlong.back.entity.User;
import com.xlong.back.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

}
