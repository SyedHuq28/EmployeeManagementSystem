package com.bitm.android.employeemanagementsystem.models;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "tbl_employee")
public class Employee {
    @PrimaryKey(autoGenerate = true)

    private long id;

    private String name;
    private int age;
    private String mobile;
    private String emailAddress;
    private double salary;
    private String gender;
    private String dob;
    private String department;
    private String designation;
    private String location;
    private String allowances;
    @Ignore
    private Bitmap image;

    public Employee(String name, int age, String mobile, String emailAddress, double salary, String gender, String dob, String department, String designation, String location, String allowances) {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.emailAddress = emailAddress;
        this.salary = salary;
        this.gender = gender;
        this.dob = dob;
        this.department = department;
        this.designation = designation;
        this.location = location;
        this.allowances = allowances;
    }

    @Ignore
    public Employee(long id, String name, int age, String mobile, String emailAddress, double salary, String gender, String dob, String department, String designation, String location, String allowances) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.emailAddress = emailAddress;
        this.salary = salary;
        this.gender = gender;
        this.dob = dob;
        this.department = department;
        this.designation = designation;
        this.location = location;
        this.allowances = allowances;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAllowances() {
        return allowances;
    }

    public void setAllowances(String allowances) {
        this.allowances = allowances;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mobile='" + mobile + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", salary=" + salary +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", location='" + location + '\'' +
                ", allowances='" + allowances + '\'' +
                ", image=" + image +
                '}';
    }
}
