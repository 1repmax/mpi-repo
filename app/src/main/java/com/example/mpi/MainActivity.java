package com.example.mpi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Allows navigation to the second activity.
     * Method is called when user taps the 2-nd activity button.
     * @param mainActivityView View object that is clicked by the user
     */
    public void navigateToSecondActivity(View mainActivityView) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    /**
     * Opens a check-box dialog when button is pressed.
     * @param mainActivityView View object that is clicked by the user
     */
    public void openDialog(View mainActivityView) {
        CheckBoxDialogFragment dialogFragment = new CheckBoxDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "CheckBoxDialogFragment");
    }


}