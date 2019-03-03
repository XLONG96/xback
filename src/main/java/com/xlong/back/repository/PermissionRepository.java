package com.xlong.back.repository;

import com.xlong.back.entity.UserPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends PagingAndSortingRepository<UserPermission, Integer> {
    @Query("select u from UserPermission u where u.userId = :userId")
    List<UserPermission> findByUserId(@Param("userId") int userId);

    Page<UserPermission> findAll(Pageable pageable);
}
