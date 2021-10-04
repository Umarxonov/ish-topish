package com.example.server.entity.enums;

public enum PermissionName {
    ADD_EMPLOYEE("Xodim qo'shish",GroupName.EMPLOYEE_CRUD),
    ADD_CUSTOMER("Mijoz qo'shish",GroupName.CUSTOMER_CRUD),
    EDIT_EMPLOYEE("Xodimni Taxrirlash",GroupName.EMPLOYEE_CRUD),
    EDIT_CUSTOMER("Mijozni Taxrirlash",GroupName.CUSTOMER_CRUD),
    DELETE_EMPLOYEE("Xodim o'chirish",GroupName.EMPLOYEE_CRUD),
    DELETE_CUSTOMER("Mijozni o'chirish",GroupName.CUSTOMER_CRUD),
    GET_EMPLOYEE("Xodimlarni olish",GroupName.EMPLOYEE_CRUD),
    GET_CUSTOMER("Mijozlarni olish",GroupName.CUSTOMER_CRUD),
    ADD_CATEGORY("Kategoriya qo'shish",GroupName.CATEGORY_CRUD),
    GET_SALE("Savdolarni olish",GroupName.SALE_CRUD),
    ADD_SALE("Savdoni qo'shish",GroupName.SALE_CRUD),
    EDIT_SALE("Savdoni taxrirlash",GroupName.SALE_CRUD),
    GET_REJECT("Qaytganlarni olish",GroupName.REJECT_CRUD),
    ADD_REJECT("Qaytganni qo'shish",GroupName.REJECT_CRUD),
    EDIT_REJECT("Qaytganni taxrirlash",GroupName.REJECT_CRUD),
    APPROVED_REJECT("Qaytganni tasdiqlash",GroupName.REJECT_CRUD),
    GET_DEFECT("Yaroqsizlarni olish",GroupName.DEFECT_CRUD),
    ADD_DEFECT("Yaroqsizni qo'shish",GroupName.DEFECT_CRUD),
    EDIT_DEFECT("Yaroqsizni taxrirlash",GroupName.DEFECT_CRUD),
    ADD_BONUS("Bonus qo'shish",GroupName.BONUS_CRUD),
    EDIT_BONUS("Bonuslarni taxrirlash",GroupName.BONUS_CRUD),
    GET_BONUS("Bonuslarni olish",GroupName.BONUS_CRUD),
    GET_ONE_BONUS("Bonusni olish",GroupName.BONUS_CRUD),
    DELETE__BONUS("Bonusni o'chirish",GroupName.BONUS_CRUD),
    ADD_GIVEN_SALARY("Berilgan oylikni qo'shish",GroupName.GIVEN_SALARY_CRUD),
    EDIT_GIVEN_SALARY("Berilgan oylikni taxrirlash",GroupName.GIVEN_SALARY_CRUD),
    GET_GIVEN_SALARY("Berilgan oyliklarni ko'rish",GroupName.GIVEN_SALARY_CRUD),
    GET_ONE_GIVEN_SALARY("BERILGAN oylikni ko'rish",GroupName.GIVEN_SALARY_CRUD),
    DELETE_GIVEN_SALARY("Berilagan oylikni o'chirish",GroupName.GIVEN_SALARY_CRUD),
    ADD_KPI("Kpi qo'shish",GroupName.KPI_CRUD),
    GET_KPI("Kpilarni olish",GroupName.KPI_CRUD),
    GET_ONE_KPI("Kpini olish",GroupName.KPI_CRUD),
    DELETE_KPI("Kpini o'chirish",GroupName.KPI_CRUD),
    ADD_SALARY("Oylik qo'shish",GroupName.SALARY_CRUD),
    EDIT_SALARY("Oylik taxrirlash",GroupName.SALARY_CRUD),
    GET_ONE_SALARY("Oylikni olish",GroupName.SALARY_CRUD),
    GET_SALARY("Oyliklarni olish",GroupName.SALARY_CRUD),
    DELETE_SALARY("Oyliklarni o'chirish",GroupName.SALARY_CRUD),
    ADD_SHOP_KPI("Shop kpi qo'shish",GroupName.SHOP_KPI_CRUD),
    EDIT_SHOP_KPI("Shop kpi taxrirlash",GroupName.SHOP_KPI_CRUD),
    GET_ONE_SHOP_KPI("Shop kpi olish",GroupName.SHOP_KPI_CRUD),
    GET_SHOP_KPI("Shop kpilarni olish",GroupName.SHOP_KPI_CRUD),
    DELETE_SHOP_KPI("Shop KPI o'chirish",GroupName.SHOP_KPI_CRUD);

    private String nameUz;

    private GroupName groupName;

    PermissionName(String uzName, GroupName groupName) {
        this.nameUz = uzName;
        this.groupName=groupName;
    }

    public String getNameUz() {
        return nameUz;
    }

    public void setNameUz(String nameUz) {
        this.nameUz = nameUz;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName groupName) {
        this.groupName = groupName;
    }
}
