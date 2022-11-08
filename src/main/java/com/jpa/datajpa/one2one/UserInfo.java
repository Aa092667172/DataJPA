package com.jpa.datajpa.one2one;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.function.Consumer;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "userTest")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer ages;
    private String telephone;

    /**
     * cascade設置級聯關係
     *
     *     PERSIST:新建
     *     REMOVE:刪除
     *     REFRESH:刷新
     *     MERGE: 更新
     *     ALL:四項全開
     *     orphanRemoval = true時 也會執行級聯刪除
     *     當無使用 CascadeType.REMOVE但orphanRemoval = true時
     *     刪除前將會多一次update將級聯關係先去掉
     *
     */
    //當主建和外建都是同一字段時
    @MapsId
    @OneToOne(cascade ={CascadeType.PERSIST},orphanRemoval = true,fetch = FetchType.LAZY) //維護user的外建關聯關係,配置一對一
    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT,name = "my_user_test_id"))
    private UserTest userTest;
}
