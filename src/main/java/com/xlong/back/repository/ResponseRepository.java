package com.xlong.back.repository;

import com.xlong.back.entity.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResponseRepository extends PagingAndSortingRepository<Response, Integer> {
    Page<Response> findAll(Pageable pageable);

    @Query(value = "SELECT count(*) FROM responses r where year(r.responseTime)=:year " +
            "and month(r.responseTime) =:month", nativeQuery = true)
    Integer getCountByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);

    @Query(value = "SELECT * FROM responses r where year(r.responseTime)=:year " +
            "and month(r.responseTime) =:month", nativeQuery = true)
    List<Response> findAllByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);

    Response findByRequestId(String requestId);
}
