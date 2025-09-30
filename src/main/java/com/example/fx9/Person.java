package com.example.fx9;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private final StringProperty name = new SimpleStringProperty(this, "name", "");
    private final IntegerProperty age = new SimpleIntegerProperty(this, "age", 0);
    private final StringProperty sex = new SimpleStringProperty(this, "sex", "");
    private final StringProperty city = new SimpleStringProperty(this, "city", "");

    public Person() {}

    public Person(String name, int age, String sex) {
        setName(name);
        setAge(age);
        setSex(sex);
    }

    public Person(String name, int age, String sex, String city) {
        setName(name);
        setAge(age);
        setSex(sex);
        setCity(city);
    }

    public String getName() {
        return name.get(); }

    public void setName(String value) {
        name.set(value); }

    public StringProperty nameProperty() {
        return name; }

    public int getAge() {
        return age.get(); }

    public void setAge(int value) {
        age.set(value); }

    public IntegerProperty ageProperty() {
        return age; }

    public String getSex() {
        return sex.get(); }

    public void setSex(String value) {
        sex.set(value); }

    public StringProperty sexProperty() {
        return sex; }

    public String getCity() { return city.get(); }

    public void setCity(String value) { city.set(value); }

    public StringProperty cityProperty() { return city; }
}
