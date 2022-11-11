package com.jpa.datajpa.specifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill,Long> , JpaSpecificationExecutor<Bill> {
    @Query(value = "select b.id, amount, name ,bo.id, address, order_name, bill_id  from bill b left join bill_order bo on b.id = bo.bill_id where b.id = ?1 ",nativeQuery = true)
    Optional<Bill> selectJoinByBillId(Long id);
}
