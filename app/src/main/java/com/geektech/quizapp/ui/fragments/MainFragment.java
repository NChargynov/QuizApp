package com.geektech.quizapp.ui.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.geektech.quizapp.MainViewModel;
import com.geektech.quizapp.R;
import com.geektech.quizapp.ui.QuizActivity;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView textViewAmount;
    private SeekBar seekBar;
    private int progressSeekBar;
    private String categoryDataSpinner;
    private String difficultyDataSpinner;
    private Spinner categorySpinner;
    private Spinner difficultySpinner;
    private Button buttonStart;
//    private Button plus, minus;
//    private TextView counter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.message.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
            }
        });

//        mViewModel.counterData.observe(getViewLifecycleOwner(), new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                counter.setText(integer.toString());
//            }
//        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewAmount = view.findViewById(R.id.tv_amount);
        seekBar = view.findViewById(R.id.seek_bar);
        difficultySpinner = view.findViewById(R.id.difficult_spinner);
        categorySpinner = view.findViewById(R.id.category_spinner);
        buttonStart = view.findViewById(R.id.btn_start);
//        minus = view.findViewById(R.id.btn_minus);
//        plus = view.findViewById(R.id.btn_plyus);
//        counter = view.findViewById(R.id.tv_amount2);

        buttonStart.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), QuizActivity.class);
            intent.putExtra("category", categoryDataSpinner);
            intent.putExtra("difficulty", difficultyDataSpinner);
            intent.putExtra("progress", progressSeekBar);
            startActivity(intent);
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryDataSpinner = parent.getItemAtPosition(position).toString();
//                Log.d("ololo",  categoryDataSpinner + "category");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                difficultyDataSpinner = parent.getItemAtPosition(position).toString();
//                Log.d("ololo", difficultDataSpinner + " difficult");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewAmount.setText(String.valueOf(progress));
                progressSeekBar = progress;
//                Log.d("ololo" , progressSeekBar + " seekBar progress");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mViewModel.plusOnClick();
//            }
//        });
//        minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mViewModel.minusOnClick();
//            }
//        });
    }
}