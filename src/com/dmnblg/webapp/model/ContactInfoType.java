package com.dmnblg.webapp.model;

public enum ContactInfoType {
    TELEPHONE("Телефон"),
    SKYPE("Skype"),
    EMAIL("e-mail"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactInfoType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
