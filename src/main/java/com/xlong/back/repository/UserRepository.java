package com.xlong.back.repository;
import com.xlong.back.entity.User;
import com.xlong.back.entity.UserPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    User findByUsername(String username);

    Page<User> findAll(Pageable pageable);
}
