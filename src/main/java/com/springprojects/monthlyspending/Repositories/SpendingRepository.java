package com.springprojects.monthlyspending.Repositories;

import com.springprojects.monthlyspending.Entities.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long>, JpaSpecificationExecutor<Spending> {
    List<Spending> findAllByItemNameContainingIgnoreCase(String name);

    @Query("SELECT e FROM Spending e WHERE e.date BETWEEN :startDate AND :endDate")
    List<Spending> findAllByDateBetween(
            @Param("startDate") Date start,
            @Param("endDate") Date end
    );
}
