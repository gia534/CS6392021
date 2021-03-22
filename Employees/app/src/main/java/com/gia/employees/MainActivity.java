package com.gia.employees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private TextView empTV;
    private EditText edTxtFirstName;
    private EditText edTxtLastName;
    private Button mButton;
    private ArrayList<String> firstName;
    private ArrayList<String> lastName;
    private  String oneName;
    private String secondName;
    private long counter;

    //private FirebaseRecyclerOptions<Employee> options;
    private  DatabaseReference nameRef;
    private static final String TAG = "MainActivity";
    private FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = new ArrayList<String>();
        lastName = new ArrayList<String>();
        empTV = findViewById(R.id.employeeList_tv);
        edTxtFirstName = findViewById(R.id.firstName_editText);
        edTxtLastName = findViewById(R.id.lastName_editText);
        mButton = findViewById(R.id.add_employee_button);

        database =  FirebaseDatabase.getInstance();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        nameRef = database.getReference().child("employees");
        nameRef.keepSynced(true);

        nameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //make for loop in order to get each snapshot instance
                String holder = "";

                counter = 0;
                 for (DataSnapshot ds : snapshot.getChildren()) {
                     Employee names = (Employee) ds.getValue(Employee.class);
                     firstName.add(names.getFirstName());
                     lastName.add(names.getLastName());
                     holder = holder + " " + names.getFirstName() + " " + names.getLastName() + "\n";

                     Log.i(TAG, counter + "First name is: " + names.getFirstName() + ". Last name is " + names.getLastName());
                     counter++;
                 }
                empTV.setText(holder);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Failed to read value
                Log.w(TAG, "Failed to read value", error.toException());
            }
        });

    }

    public void addEmployee(View view) {
        String increment = Long.toString(counter);

        String employeeFirstName = edTxtFirstName.getText().toString();
        String employeeLastName = edTxtLastName.getText().toString();

        Employee newEmployee = new Employee(employeeFirstName, employeeLastName);

        nameRef.child(increment.toString()).setValue(newEmployee);
        edTxtFirstName.getText().clear(); // clears the text inside the editText after hte Button has been clicked
        edTxtLastName.getText().clear(); // clears the text inside the editText after hte Button has been clicked

        //Hiding the keyboard after the button has been clicked
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputManager != null){
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
