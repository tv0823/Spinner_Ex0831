package com.example.spinner_ex0831;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{
    Spinner classes_spin;
    ListView studentsLv;
    TextView firstNameTv, lastNameTv, birthDateTv, phoneNumTv;

    String[] classes;
    String[][] classStudents, classBirthDates, classPhoneNumbers;

    int classNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classes_spin = findViewById(R.id.classes_spin);
        studentsLv = findViewById(R.id.studentsLv);

        firstNameTv = findViewById(R.id.firstNameTv);
        lastNameTv = findViewById(R.id.lastNameTv);
        birthDateTv = findViewById(R.id.birthDateTv);
        phoneNumTv = findViewById(R.id.phoneNumTv);

        classes_spin.setOnItemSelectedListener(this);

        classes = getResources().getStringArray(R.array.classes);
        ArrayAdapter<String> spinnAdp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, classes);
        classes_spin.setAdapter(spinnAdp);

        studentsLv.setOnItemClickListener(this);
        studentsLv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        classStudents = new String[][]{getResources().getStringArray(R.array.class1Students), getResources().getStringArray(R.array.class2Students), getResources().getStringArray(R.array.class3Students), getResources().getStringArray(R.array.class4Students)};
        classBirthDates = new String[][]{getResources().getStringArray(R.array.class1BirthDates), getResources().getStringArray(R.array.class2BirthDates), getResources().getStringArray(R.array.class3BirthDates), getResources().getStringArray(R.array.class4BirthDates)};
        classPhoneNumbers = new String[][]{getResources().getStringArray(R.array.class1PhoneNumbers), getResources().getStringArray(R.array.class2PhoneNumbers), getResources().getStringArray(R.array.class3PhoneNumbers), getResources().getStringArray(R.array.class4PhoneNumbers)};
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long rowid) {
        clearTv();
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, classStudents[pos]);
        studentsLv.setAdapter(adp);
        classNum = pos;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long rowid) {
        getFirstAndLastName(classStudents[classNum][pos]);
        birthDateTv.setText("Birth date: "+ classBirthDates[classNum][pos]);
        phoneNumTv.setText("Phone number: " + classPhoneNumbers[classNum][pos]);
    }

    public void getFirstAndLastName(String fullName){
        boolean foundSpace = false;
        int i = 0;
        while(!foundSpace){
            if(fullName.charAt(i) == ' '){
                foundSpace = true;
            }
            i++;
        }

        String firstName = fullName.substring(0,i);
        String lastName = fullName.substring(i);
        firstNameTv.setText("First name: " + firstName);
        lastNameTv.setText("Last name: " + lastName);

    }

    public void clearTv(){
        firstNameTv.setText("");
        lastNameTv.setText("");
        birthDateTv.setText("");
        phoneNumTv.setText("");
    }
}