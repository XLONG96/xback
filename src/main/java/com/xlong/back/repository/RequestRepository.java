package com.xlong.back.repository;

import com.xlong.back.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestRepository extends PagingAndSortingRepository<Request, Integer> {
    Page<Request> findAll(Pageable pageable);

    @Query(value = "SELECT count(*) FROM requests r where year(r.requestTime)=:year " +
            "and month(r.requestTime) =:month", nativeQuery = true)
    Integer getCountByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);
}
