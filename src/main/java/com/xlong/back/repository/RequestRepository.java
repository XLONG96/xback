package com.xlong.back.repository;

import com.xlong.back.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends PagingAndSortingRepository<Request, Integer> {
    Page<Request> findAll(Pageable pageable);
}
