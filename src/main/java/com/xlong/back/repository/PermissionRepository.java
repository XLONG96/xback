package com.xlong.back.repository;

import com.xlong.back.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<UserPermission, Integer> {
    @Query("select u from UserPermission u where u.userId = :userId")
    List<UserPermission> findByUserId(@Param("userId") int userId);
}
