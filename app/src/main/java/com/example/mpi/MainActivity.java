package com.example.mpi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String[] themes;
    private AutoCompleteTextView textInputLayout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateDropdown();
    }

    /**
     * Recreate the dropdown box since change of themes automatically recreates activities.
     */
    @Override
    protected void onResume() {
        super.onResume();
        populateDropdown();
    }

    /**
     * Create a dropdown box using custom layout and ArrayAdapter.
     */
    private void populateDropdown() {
        createDropdown();
        setThemeChangerListener(themes, textInputLayout);
    }

    /**
     * Adds listener to the drop-down items. When user interacts with one of the items then
     * the theme of the application is changed to either:
     *      light theme
     *      dark theme
     *      default theme which follows Systems configuration
     * @param themes supported themes
     * @param textInputLayout dropdown with clickable items
     */
    private void setThemeChangerListener(String[] themes, AutoCompleteTextView textInputLayout) {
        textInputLayout.setOnItemClickListener((parent, view, position, id) -> {
            if (themes[position].contentEquals("Dark")) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else if (themes[position].contentEquals("Light")) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
        });
    }

    private void createDropdown() {
        themes = getResources().getStringArray(R.array.themes);
        ArrayAdapter<String> themeAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, themes);
        textInputLayout = findViewById(R.id.autoCompleteTextView);
        textInputLayout.setAdapter(themeAdapter);
    }

    /**
     * Navigates user to the second activity when button is pressed.
     * @param mainActivityView view of main activity that user is interacting with
     */
    public void toSecondActivity(View mainActivityView) {
        Intent secondActivity = new Intent(this, SecondActivity.class);
        startActivity(secondActivity);
    }

    /**
     * Saves the value that was passed in editable text field into shared preferences
     * for retrieval in another activity.
     * @param mainActivityView View that user is interacting with
     */
    public void save(View mainActivityView) {
        EditText editText = findViewById(R.id.editTextTextPersonName);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String persistableValue = editText.getText().toString();
        sharedPreferences.edit().putString("savedKey", persistableValue).apply();
    }

}