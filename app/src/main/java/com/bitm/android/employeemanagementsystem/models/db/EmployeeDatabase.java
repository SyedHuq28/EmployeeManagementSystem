package com.bitm.android.employeemanagementsystem.models.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bitm.android.employeemanagementsystem.models.daos.EmployeeDao;
import com.bitm.android.employeemanagementsystem.models.Employee;

@Database(entities = {Employee.class}, version = 2)
public abstract class EmployeeDatabase extends RoomDatabase {
    public abstract EmployeeDao getEmployeeDao();
    private static EmployeeDatabase db;

    private final static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("begin transaction");

        }
    };

    public static EmployeeDatabase getDb(Context context) {
        //Singleton Pattern
        if (db != null) {
            return db;
        }

        db = Room.databaseBuilder(context, EmployeeDatabase.class, "emp_db")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();
        return db;
    }
}
