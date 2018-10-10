package com.ddl.guava.entity;

import com.google.common.base.Objects;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class Student {
    private String firstName;

    private String lastName;

    private int rollNo;

    private String className;

    public Student(String firstName, String lastName, int rollNo, String className) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNo = rollNo;
        this.className = className;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Student) || object == null) {
            return false;
        }
        Student student = (Student) object;
        return Objects.equal(firstName, student.firstName)
                && Objects.equal(lastName, student.lastName)
                && Objects.equal(rollNo, student.rollNo)
                && Objects.equal(className, student.className);
    }

    @Override
    public int hashCode() {
        System.out.println(java.util.Objects.hash(className, rollNo));
        return Objects.hashCode(className, rollNo);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rollNo=" + rollNo +
                ", className='" + className + '\'' +
                '}';
    }
}
