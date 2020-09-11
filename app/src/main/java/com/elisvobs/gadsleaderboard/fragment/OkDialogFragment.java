package com.elisvobs.gadsleaderboard.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class OkDialogFragment extends DialogFragment {
    public static final String ARG_MESSAGE = "message";

    public OkDialogFragment() {
        Bundle defaultArgs = new Bundle();
        defaultArgs.putString(ARG_MESSAGE, "");
        setArguments(defaultArgs);
    }

    public static OkDialogFragment newInstance(String message) {
        OkDialogFragment dialog = new OkDialogFragment();
        assert dialog.getArguments() != null;
        dialog.getArguments().putString(ARG_MESSAGE, message);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        return new AlertDialog.Builder(getContext())
                .setMessage(getArguments().getString(ARG_MESSAGE))
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

}
