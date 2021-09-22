package com.example.mpi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.text.TextUtils.isEmpty;

/**
 * Second activity contains the view and logic for retrieving the value
 * that was saved in main activity.
 */
public class SecondActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    /**
     * Reads the value that was saved in shared preferences in the first activity and display
     * the value to the user through edit text or toast components.
     * @param view View that user is interacting with
     */
    public void read(View view) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String savedValue = sharedPreferences.getString("savedKey", "");
        if (isEmpty(savedValue)) {
            Toast.makeText(getApplicationContext(), "Nothing found", Toast.LENGTH_SHORT).show();
        } else {
            EditText editText = findViewById(R.id.editTextTextPersonName2);
            editText.setText(savedValue);
        }
    }
}
