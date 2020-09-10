package com.bitm.android.employeemanagementsystem.models.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.bitm.android.employeemanagementsystem.models.db.EmployeeDatabase;
import com.bitm.android.employeemanagementsystem.models.Employee;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    private final String TAG = EmployeeViewModel.class.getSimpleName();
    private MutableLiveData<List<Employee>> employeeList = new MutableLiveData<>();
    private MutableLiveData<Employee> employeeMutableLiveData = new MutableLiveData<>();
    private Context context;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    public MutableLiveData<List<Employee>> getAllEmployees() {
        employeeList.postValue(EmployeeDatabase.getDb(context).getEmployeeDao().getAllEmployees());
        return employeeList;
    }

    public void addNewEmployee(Employee employee) {

        EmployeeDatabase.getDb(context)
                .getEmployeeDao()
                .insertNewEmployee(employee);
    }

    public MutableLiveData<Employee> getSingleEmployee(long id) {
        employeeMutableLiveData.postValue(EmployeeDatabase.getDb(context).getEmployeeDao().getEmployeeById(id));
        return employeeMutableLiveData;
    }

    public void delete(Employee employee) {
        EmployeeDatabase.getDb(context)
                .getEmployeeDao()
                .deleteEmployee(employee);
    }

    public void update(Employee employee) {
        EmployeeDatabase.getDb(context)
                .getEmployeeDao()
                .updateEmployee(employee);
    }
}
