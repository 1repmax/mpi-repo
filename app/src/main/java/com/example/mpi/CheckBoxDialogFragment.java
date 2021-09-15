package com.example.mpi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

/**
 * Custom dialog fragment implementation.
 */
public class CheckBoxDialogFragment extends DialogFragment {

    /**
     * Override the parent classes onCreateDialog method and prepare custom
     * Dialog window with the help of the provided builder class.
     * @param savedInstanceState reference to the Bundle object that is used during creation
     *                           of the Activity
     * @return Dialog window with the multi-choice checkbox
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ArrayList<String> selectedItems = new ArrayList<>();
        String[] authors = getResources().getStringArray(R.array.choices_array);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_title)
                .setMultiChoiceItems(R.array.choices_array, null, (dialog, which, isChecked) -> {
                    if (isChecked) {
                        selectedItems.add(authors[which]);
                        displayToast(authors, which, "checked");
                    } else if (selectedItems.contains(authors[which])) {
                        selectedItems.remove(authors[which]);
                        displayToast(authors, which, "unchecked");
                    }
                })
                .setPositiveButton(R.string.ok_dialog_button, null)
                .setNegativeButton(
                        R.string.close_dialog_button,
                        (dialog, which) -> displayToast("You closed dialog"))
                .create();

        // Override the OnShowListener to display a toast when Positive Button is clicked
        alertDialog.setOnShowListener(dialog -> {
            Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> displayToast("You clicked OK"));
        });

        return alertDialog;
    }

    /**
     * Displays a toast message stating if the check box was checked or unchecked.
     * @param authors Authors of the application
     * @param which Index of the item clicked in the check-box
     * @param state 'checked' or 'unchecked'
     */
    private void displayToast(final String[] authors, final int which, final String state) {
        Toast.makeText(getContext(), authors[which] + " " + state, Toast.LENGTH_SHORT).show();
    }

    private void displayToast(final String toastText) {
        Toast.makeText(getContext(), toastText, Toast.LENGTH_SHORT).show();
    }
}
