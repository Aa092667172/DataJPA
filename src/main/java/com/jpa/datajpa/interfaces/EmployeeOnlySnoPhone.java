package com.jpa.datajpa.interfaces;

/**
 * 使用介面做代理
 * 源代碼是使用hashMap做生成 因此介面回傳後將賦值
 * 如欄位名稱與sql不同請注意以SQL中命名為主
 */
public interface EmployeeOnlySnoPhone {
    Long getId();
    String getPhone();
}
