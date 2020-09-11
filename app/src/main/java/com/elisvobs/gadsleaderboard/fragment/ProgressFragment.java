package com.elisvobs.gadsleaderboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.elisvobs.gadsleaderboard.R;

public class ProgressFragment extends DialogFragment {
    private static final String ARG_MESSAGE = "message";

    public ProgressFragment() {
        Bundle defaultArgs = new Bundle();
        defaultArgs.putString(ARG_MESSAGE, "");
        setArguments(defaultArgs);
        setCancelable(false);
    }

    public static ProgressFragment newInstance(String message) {
        ProgressFragment dialog = new ProgressFragment();
        assert dialog.getArguments() != null;
        dialog.getArguments().putString(ARG_MESSAGE, message);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_progress, container, false);
        TextView txtMessage = view.findViewById(R.id.txtMessage);
        assert getArguments() != null;
        txtMessage.setText(getArguments().getString(ARG_MESSAGE));
        return view;
    }
}
