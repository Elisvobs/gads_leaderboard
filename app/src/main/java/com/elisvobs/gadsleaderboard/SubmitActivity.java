package com.elisvobs.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.elisvobs.gadsleaderboard.fragment.ConfirmFragment;
import com.elisvobs.gadsleaderboard.fragment.OkDialogFragment;
import com.elisvobs.gadsleaderboard.fragment.ProgressFragment;
import com.elisvobs.gadsleaderboard.fragment.submit.SubmitFragment;
import com.elisvobs.gadsleaderboard.fragment.submit.SubmitViewModel;
import com.elisvobs.gadsleaderboard.model.Submission;

public class SubmitActivity extends AppCompatActivity implements ConfirmFragment.OnClickListener {
    private SubmitViewModel viewModel;
    private Submission submission = new Submission();
    private ProgressFragment progressDialog;
    private SubmitFragment submitResultDialog;
    private OkDialogFragment okDialog;
    private ConfirmFragment confirmDialog;
    TextView firstName, lastName, emailAdd, projectUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        okDialog = OkDialogFragment.newInstance(getString(R.string.error_message));
        confirmDialog = ConfirmFragment.newInstance(getString(R.string.confirmation));
        progressDialog = ProgressFragment.newInstance(getString(R.string.submitting));
        submitResultDialog = SubmitFragment.newInstance();

        viewModel = new ViewModelProvider(this).get(SubmitViewModel.class);
        viewModel.getStatus().observe(this, status -> {
            if (status != SubmitViewModel.STATUS_NEUTRAL) {
                progressDialog.dismiss();
                submitResultDialog.setSuccess(status == SubmitViewModel.STATUS_OK);
                submitResultDialog.show(getSupportFragmentManager(), "SubmitActivity_SubmitResultDialog");
            }
        });

        findViewById(R.id.btn_back).setOnClickListener(
                view -> startActivity(new Intent(this, MainActivity.class)));

        firstName = findViewById(R.id.name);
        lastName = findViewById(R.id.surname);
        emailAdd = findViewById(R.id.email);
        projectUrl = findViewById(R.id.url);

        submit();
    }

    private void submit() {
        findViewById(R.id.button).setOnClickListener(view -> {
            submission.setFirstName(firstName.getText().toString().trim());
            submission.setLastName(lastName.getText().toString().trim());
            submission.setEmail(emailAdd.getText().toString().trim());
            submission.setProjectUrl(projectUrl.getText().toString().trim());
            boolean filledForm = submission.getFirstName().length() > 0
                    && submission.getLastName().length() > 0
                    && submission.getEmail().length() > 0
                    && submission.getProjectUrl().length() > 0;
            if (!filledForm)
                okDialog.show(getSupportFragmentManager(), "SubmitActivity_OkDialog");
            else
                confirmDialog.show(getSupportFragmentManager(), "SubmitActivity_ConfirmDialog");
        });
    }

    @Override
    public void onConfirm(ConfirmFragment confirmFragment) {
        progressDialog.show(getSupportFragmentManager(), "SubmitActivity_ProgressDialog");
        viewModel.submit(submission);
    }

    @Override
    public void onDismiss(ConfirmFragment confirmFragment) {
    }

}