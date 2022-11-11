package com.jpa.datajpa.specifications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.*;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BillRepositoryTest {
    @Autowired
    private BillRepository repository;
    @Autowired
    private BIllOrderRepository bIllOrderRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Rollback(value = false)
    void init(){

        Bill vo = new Bill();
        vo.setName("test");
        vo.setAmount(200);

        ArrayList<Order> orders = Lists.newArrayList(
                Order.builder()
                        .orderName("test的訂單1")
                        .address("新北市板橋區")
                        .bill(vo)
                        .build(),
                Order.builder()
                        .orderName("test的訂單2")
                        .address("新北市新莊區")
                        .bill(vo)
                        .build()
        );

        vo.setOrders(orders);

        Bill vo1 = new Bill();
        vo1.setName("我要逛蝦皮");
        vo1.setAmount(5000);

        ArrayList<Order> orders1 = Lists.newArrayList(
                Order.builder()
                        .orderName("我要逛蝦皮的訂單1")
                        .address("新北市泰山區")
                        .bill(vo1)
                        .build(),
                Order.builder()
                        .orderName("我要逛蝦皮的訂單2")
                        .address("新北市五股區")
                        .bill(vo1)
                        .build()
        );

        vo1.setOrders(orders1);

        bIllOrderRepository.saveAllAndFlush(orders);
        bIllOrderRepository.saveAllAndFlush(orders1);
    }
    @Test
    void findOne(){
//        Long id = 1l;
        String name = "test";
        Integer amount = 200;
        List<Long> in = Arrays.asList(1l,2l,3l);
        //多條件帶入 觀察sql
        Specification specification = (Specification)(root, cq, cb) -> {
            Predicate inP = cb.in(root.get("id")).value(in);
            Predicate like  = cb.like(root.get("name"),"%"+name+"%");
//            Predicate qe = cb.equal(root.get("name"), name);
            Predicate ge = cb.ge(root.get("amount"), amount);
            return cq.where(inP, like,ge)
                    .getRestriction();
        };
        repository.findOne(specification).ifPresent(System.out::println);

    }


    @Test
    void testAll() throws JsonProcessingException {

        Bill selectVo = Bill.builder()
                .name("test")
                .build();

        Specification<Bill> specification = (Specification<Bill>)(root, query, criteriaBuilder) -> {
            //root :查詢的根對象 
            // query:頂層查詢對象 ,自定義查詢方式 from where
            // criteriaBuilder 查詢的構造器,封裝很多查詢條件 in like = < > ...
            List<Predicate> select = new ArrayList<>();
            //對應屬性名稱
            if(!Objects.isNull(selectVo.getName())){
                select.add(criteriaBuilder.equal(root.get("name"),selectVo.getName()));
            }
            if(!Objects.isNull(selectVo.getId())){
                select.add(criteriaBuilder.equal(root.get("id"),selectVo.getId()));
            }
            return criteriaBuilder.and(select.toArray(new Predicate[select.size()]));
        };

        Page<Bill> page = repository.findAll(specification, PageRequest.of(0, 2));
        //拿內容
        page.getContent();
        //總頁數
        page.getTotalPages();
        //總數
        page.getSize();
        //當前頁數 需+1 因為從0開始算
        page.getNumber();
        //實際取回的數量 假設22筆資料 以10個做一分頁 最後一頁的getNumberOfElements 為2
        page.getNumberOfElements();

        System.out.println(page.getNumberOfElements());
        //單純取數量
        System.out.println(repository.count(specification));

        String s = objectMapper.writeValueAsString(repository.findAll(specification,PageRequest.of(0,2)));
        System.out.println(s);
        //排序
        String s1 = objectMapper.writeValueAsString(  repository.findAll(specification, Sort.by("id").descending()));
        System.out.println(s1);
    }


    @Test
    void testJoin() throws JsonProcessingException {
        Specification<Bill> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("orders", JoinType.LEFT);
                return query.where(criteriaBuilder.equal(join.get("id"), 1)).getRestriction();
            }
        };
        String s1 = objectMapper.writeValueAsString(repository.findAll(specification));
        System.out.println(s1);
    }
    @Test
    void testJoin1() throws JsonProcessingException {

        String s1 = objectMapper.writeValueAsString(repository.selectJoinByBillId(1l).orElse(null));
        System.out.println(s1);
    }

}