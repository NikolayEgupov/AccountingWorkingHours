package ru.egupov.accountingworkinghours.model;

public enum EventType {
    START_WORK("Начало работы"),
    END_WORK("Завершение работы");

    private String name;

    EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
