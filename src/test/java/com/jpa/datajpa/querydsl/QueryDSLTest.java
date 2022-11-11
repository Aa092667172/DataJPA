package com.jpa.datajpa.querydsl;

import com.jpa.datajpa.specifications.Bill;
import com.jpa.datajpa.specifications.QBill;
import com.jpa.datajpa.specifications.QOrder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.criterion.Projection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@SpringBootTest
public class QueryDSLTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    @Rollback(value = false)
    @Transactional
    void update(){
        QBill qBill = QBill.bill;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBill.id.eq(1l));

        long count = jpaQueryFactory.update(qBill).set(qBill.name, "測試測試").where(builder).execute();
        System.out.println(count);
        //selectFrom等同 select *
        List<Bill> bills = jpaQueryFactory.selectFrom(qBill).where(builder).orderBy(qBill.id.desc()).fetch();
        System.out.println(bills);
    }


    @Test
    @Transactional
    void delete(){
        QOrder qOrder =  QOrder.order;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qOrder.id.eq(1l).or(qOrder.id.eq(2l)));
        long execute = jpaQueryFactory.delete(qOrder).where(builder).execute();
        System.out.println(execute);
    }

    @Test
    void query(){
        QOrder qOrder =  QOrder.order;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qOrder.address.like("%" + "新北市" + "%"));
        //條件查詢 只拿orderName
//        List<String> s = jpaQueryFactory.select(qOrder.orderName)
//                                    .from(qOrder)
//                                    .where(builder)
//                                    .orderBy(qOrder.id.desc())
//                                    .fetch();
//        System.out.println(s);
        //
//        List<OrderOnlyNameAndId> fetch = jpaQueryFactory.select(
//                //指令生成類可以不用是Entity 但屬性變數名稱要相同才可mapping
//                Projections.bean(OrderOnlyNameAndId.class, qOrder.id, qOrder.orderName))
//                .from(qOrder)
//                .where(builder)
//                .orderBy(qOrder.id.desc())
//                .fetch();
//        System.out.println(fetch);

//        long l = jpaQueryFactory.selectFrom(qOrder).where(builder).fetchCount();
//        System.out.println(l);
        //去從
        System.out.println(jpaQueryFactory.selectDistinct(qOrder.orderName.substring(0,7) ).from(qOrder).where(builder).fetch());

    }

}
