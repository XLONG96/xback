package com.xlong.back.repository;

import com.xlong.back.entity.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResponseRepository extends PagingAndSortingRepository<Response, Integer> {
    Page<Response> findAll(Pageable pageable);
}
