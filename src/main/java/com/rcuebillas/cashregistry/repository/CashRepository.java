package com.rcuebillas.cashregistry.repository;

import com.rcuebillas.cashregistry.model.Cash;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashRepository extends CrudRepository<Cash, Long> {
    @Query("SELECT c FROM Cash c WHERE c.value = :value")
    List<Cash> findByValue(@Param("value") int value);
}
