package com.geektech.quizapp.ui.settings;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geektech.quizapp.QuizApp;
import com.geektech.quizapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private TextView tvClearAll;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvClearAll = view.findViewById(R.id.tv_clear_all);
        tvClearAll.setOnClickListener(v -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setTitle("Внимание!!!");
            alertBuilder.setMessage("Вы точно хотите очистить историю?");
            alertBuilder.setPositiveButton("Да", (dialog, which) ->
                    QuizApp.quizDataBase.quizDao().deleteAll());
            alertBuilder.setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        });
    }
}