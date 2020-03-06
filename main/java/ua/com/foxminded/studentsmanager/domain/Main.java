package ua.com.foxminded.studentsmanager.domain;

public class Main {
    public static void main(String[] args) {
        DbManager dbManager = new DbManager();
        dbManager.manageTables();
    }
}
