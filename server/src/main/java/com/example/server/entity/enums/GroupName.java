package com.example.server.entity.enums;

public enum GroupName {
    EMPLOYEE_CRUD("Xodimlarni boshqarish"),
    CUSTOMER_CRUD("Mijozlarni boshqarish"),
    CATEGORY_CRUD("Oyliklarni boshqarish"),
    SALE_CRUD("Savdolarni boshqarish"),
    REJECT_CRUD("Qaytganlarni boshqarish"),
    DEFECT_CRUD("Yaroqsizlarni boshqarish"),
    BONUS_CRUD("Bonuslarni boshqarish"),
    GIVEN_SALARY_CRUD("Berilgan maoshlarni boshqarish"),
    KPI_CRUD("KPI larni boshqarish"),
    SALARY_CRUD("Maoshlarni boshqarish"),
    SHOP_KPI_CRUD("SHOP KPI larni boshqarish");

    private String nameUz;

    GroupName(String nameUz) {
        this.nameUz = nameUz;
    }
}
