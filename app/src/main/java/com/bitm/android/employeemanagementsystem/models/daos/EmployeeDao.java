package com.bitm.android.employeemanagementsystem.models.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bitm.android.employeemanagementsystem.models.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    long insertNewEmployee(Employee employee);

    @Update
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);

    @Query("select * from tbl_employee")
    List<Employee> getAllEmployees();

    @Query("select * from tbl_employee where id like :id")
    Employee getEmployeeById(long id);
}
