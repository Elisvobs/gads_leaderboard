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

public class ConfirmFragment extends DialogFragment {
    private static final String ARG_MESSAGE = "message";
    public static final int RESULT_OK = 1;

    public ConfirmFragment() {
        Bundle defaultArgs = new Bundle();
        defaultArgs.putString(ARG_MESSAGE, "");
        setArguments(defaultArgs);
    }

    public static ConfirmFragment newInstance(String message) {
        ConfirmFragment dialog = new ConfirmFragment();
        assert dialog.getArguments() != null;
        dialog.getArguments().putString(ARG_MESSAGE, message);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm, container, false);
        TextView txtMessage = view.findViewById(R.id.txtMessage);
        assert getArguments() != null;
        txtMessage.setText(getArguments().getString(ARG_MESSAGE));
        view.findViewById(R.id.btnOk)
                .setOnClickListener(view1 -> {
                    OnClickListener listener = (OnClickListener) getActivity();
                    if (listener != null)
                        listener.onConfirm(ConfirmFragment.this);

                    dismiss();
                });
        view.findViewById(R.id.btnClose)
                .setOnClickListener(view12 -> {
                    OnClickListener listener = (OnClickListener) getActivity();
                    if (listener != null)
                        listener.onDismiss(ConfirmFragment.this);

                    dismiss();
                });

        return view;
    }

    public static interface OnClickListener {
        void onConfirm(ConfirmFragment confirmFragment);

        void onDismiss(ConfirmFragment confirmFragment);
    }
}
