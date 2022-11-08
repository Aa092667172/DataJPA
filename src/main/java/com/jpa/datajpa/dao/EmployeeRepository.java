package com.jpa.datajpa.dao;

import com.jpa.datajpa.dto.EmployeeOnlySnoNameDto;
import com.jpa.datajpa.entity.Employee;
import com.jpa.datajpa.interfaces.EmployeeOnlySnoPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 不希望對外暴露增刪改僅查詢等等 可繼承 Repository 再在介面宣告方法即可
 * 不需要Page功能 可繼承CRUD Repository
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    EmployeeOnlySnoNameDto findByName(String name);

    EmployeeOnlySnoPhone findByPhone(String name);
    //JPQL語法 以物件及物件屬性為主 優點則是跨db好移植 缺點則是較不值觀
    @Query("From Employee where id=:sno")
    Employee testByQuery(@Param("sno") Long id);
    //原生sql
    @Query(value = "select sno,name,phone,department_id from employee where sno = ?1 ",nativeQuery = true)
    Employee testNativeByQuery(Long id);

    @Query("select new com.jpa.datajpa.dto.EmployeeOnlySnoNameDto(e.id,e.name) from Employee e  where e.id = ?1")
    EmployeeOnlySnoNameDto testJPQLToDtoByQuery(Long id);

    //需加入as 否則將會無資料
    @Query("select e.id as id,e.phone as phone from Employee e  where e.id =:id ")
    EmployeeOnlySnoPhone testJPQLToInterfaceByQuery(Long id);
    //native 使用 (:#{#tableName.columnName})
    @Query(" from Employee e  where " +
            "  (e.phone = :phone or :phone is null  ) " +
            " and ( e.name =:name or :name is null  ) " +
            " and (e.id =:sno or :sno is null   )  ")
    List<Employee> testJPQLByDynamicQuery(@Param("sno") Long id,String name,String phone);

    @Query(" from Employee e  where " +
            "  (e.phone = ?1 or ?1 is null  ) " +
            " and ( e.name =?2 or ?2 is null  ) " +
            " and (e.id =?3 or ?3 is null   )  ")
    List<Employee> testJPQLByDynamicQuery1(@Param("sno") Long id,String name,String phone);

}
