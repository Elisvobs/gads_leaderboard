package com.elisvobs.gadsleaderboard.fragment.submit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.elisvobs.gadsleaderboard.R;

public class SubmitFragment extends DialogFragment {

    private boolean success;

    public SubmitFragment() {
    }

    public static SubmitFragment newInstance() {
        return new SubmitFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = (TextView) inflater.inflate(R.layout.dialog_submit, container, false);
        if (success) {
            view.setText(R.string.submission_successful);
            view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_check_circle, 0, 0);
        } else {
            view.setText(R.string.submission_not_successful);
            view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_error, 0, 0);
        }
        return view;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        TextView view = (TextView) getView();
        if (view != null) {
            if (success) {
                view.setText(R.string.submission_successful);
                view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_check_circle, 0, 0);
            } else {
                view.setText(R.string.submission_not_successful);
                view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_error, 0, 0);
            }
        }
    }
}
