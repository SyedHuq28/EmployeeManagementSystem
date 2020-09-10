package com.bitm.android.employeemanagementsystem.controller.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bitm.android.employeemanagementsystem.R;
import com.bitm.android.employeemanagementsystem.models.Employee;

import java.util.List;

/*
* 1. Layout Inflate (row layout)
* 2. View Initialize
* 3. Data Set
* */

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeVeiwHolder> {

    private static final String TAG = "EmployeeAdapter";
    private Context context;
    private List<Employee> employeeList;
    private int count = 0;
    private OnEmployeeEditDeleteListener listener;

    public EmployeeAdapter(Context context, List<Employee> employeeList, Fragment fragment) {
        this.context = context;
        this.employeeList = employeeList;
        listener = (OnEmployeeEditDeleteListener) fragment;
    }

    @NonNull
    @Override
    public EmployeeVeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        count++;
        Log.e(TAG, "onCreateViewHolder called "+count+" times");
        View itemView = LayoutInflater.from(context).inflate(R.layout.employee_row, parent, false);
        return new EmployeeVeiwHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeVeiwHolder holder, final int position) {
        holder.nameTV.setText(employeeList.get(position).getName());
        holder.depTV.setText(employeeList.get(position).getDepartment());
        holder.phoneTV.setText(employeeList.get(position).getMobile());
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.emp_row_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_edit:
                                final Bundle bundle = new Bundle();
                                bundle.putLong("id", employeeList.get(position).getId());
                                Navigation.findNavController(v).navigate(R.id.action_employeeListFragment_to_newEmployeeFragment, bundle);
                                break;
                            case R.id.item_delete:
                                showAlertDialog(position);
                                break;
                        }
                        return false;
                    }
                });
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final long empId = employeeList.get(position).getId();
                final Bundle bundle = new Bundle();
                bundle.putLong("id", empId);
                Navigation.findNavController(v).navigate(R.id.action_employeeListFragment_to_employeeDetailsFragment, bundle);
            }
        });
    }

    private void showAlertDialog(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_baseline_delete_forever_24);
        builder.setTitle("Delete "+employeeList.get(position).getName());
        builder.setMessage("Are you sure to delete this employee?");
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDelete(employeeList.get(position));
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeeVeiwHolder extends RecyclerView.ViewHolder {
        ImageView empIV;
        TextView nameTV, depTV, phoneTV;
        Button menuBtn;
        public EmployeeVeiwHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.row_emp_name);
            depTV = itemView.findViewById(R.id.row_emp_dept);
            phoneTV = itemView.findViewById(R.id.row_emp_mobile);
            menuBtn = itemView.findViewById(R.id.row_emp_menu);
            empIV = itemView.findViewById(R.id.row_emp_image);

        }
    }

    public interface OnEmployeeEditDeleteListener {
        void onEdit(Employee employee);
        void onDelete(Employee employee);
    }

    public void refresh(Employee employee) {
        employeeList.remove(employee);
        notifyDataSetChanged();
    }
}
