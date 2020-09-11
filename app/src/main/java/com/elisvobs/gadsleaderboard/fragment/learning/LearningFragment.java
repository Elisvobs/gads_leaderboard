package com.elisvobs.gadsleaderboard.fragment.learning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.elisvobs.gadsleaderboard.R;
import com.elisvobs.gadsleaderboard.adapter.LearningAdapter;
import com.elisvobs.gadsleaderboard.fragment.OkDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningFragment extends Fragment {

    private LearningViewModel viewModel;
    private DialogFragment errorDialogFragment;

    public LearningFragment() {
    }

    public static LearningFragment newInstance() {
        return new LearningFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LearningViewModel.class);
        errorDialogFragment = OkDialogFragment.newInstance(getString(R.string.network_error));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning, container, false);
        final View emptyView = view.findViewById(R.id.emptyview);

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.refreshList());

        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final LearningAdapter adapter = new LearningAdapter(getContext());
        recyclerView.setAdapter(adapter);
        viewModel.getLearningLeaders()
                .observe(getViewLifecycleOwner(), learning -> {
                    adapter.setItems(learning);
                    swipeRefreshLayout.setRefreshing(false);
                    if (learning.size() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                });
        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (error) {
                swipeRefreshLayout.setRefreshing(false);
                if (adapter.getItemCount() > 0) {
                    Toast.makeText(getContext(), getString(R.string.network_error), Toast.LENGTH_SHORT)
                            .show();
                } else {
                    recyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                    errorDialogFragment.show(getParentFragmentManager(), "LearningLeadersFragment_OkDialog");
                }
            }
        });

        return view;
    }
}