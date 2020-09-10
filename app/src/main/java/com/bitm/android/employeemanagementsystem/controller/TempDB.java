package com.bitm.android.employeemanagementsystem.controller;

import com.bitm.android.employeemanagementsystem.models.Employee;

import java.util.ArrayList;

public class TempDB {

    public static ArrayList<String> departments;
    public static ArrayList<String> designations;
    public static ArrayList<String> jobLocations;
    public static ArrayList<Employee> employeeArrayList;

    static {
        departments = new ArrayList<>();
        designations = new ArrayList<>();
        jobLocations = new ArrayList<>();
        employeeArrayList = new ArrayList<>();
        populateDepartments();
        populateDesingnations();
        populateLocations();

    }



    private static void populateDepartments() {
        departments.add("IT Support");
        departments.add("Software Development");
        departments.add("Accounts");
        departments.add("Administration");
        departments.add("Marketing & Sales");
        departments.add("Messengers");
    }

    private static void populateDesingnations() {
        designations.add("Network Engineer");
        designations.add("Jr. Software Engineer");
        designations.add("Accounts Officer");
        designations.add("Admin Officer");
        designations.add("Marketing Executive");
        designations.add("Senior Messenger");
    }

    private static void populateLocations() {
        jobLocations.add("Dhaka");
        jobLocations.add("Chittagong");
        jobLocations.add("Rajshahi");
        jobLocations.add("Khulna");
        jobLocations.add("Barishal");
        jobLocations.add("Sylhet");
        jobLocations.add("Rangpur");
    }
}
