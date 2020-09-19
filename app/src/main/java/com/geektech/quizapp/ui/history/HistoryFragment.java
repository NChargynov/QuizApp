package com.geektech.quizapp.ui.history;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.geektech.quizapp.QuizApp;
import com.geektech.quizapp.R;
import com.geektech.quizapp.models.History;
import com.geektech.quizapp.ui.history.recycler.HistoryAdapter;
import com.geektech.quizapp.ui.history.recycler.HistoryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements HistoryViewHolder.HistoryListener {

    private HistoryViewModel mViewModel;
    private HistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    private List<History> currentHistories;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        mViewModel.historyLiveData.observe(getActivity(), new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> histories) {
                if (histories != null){
                    historyAdapter.updateHistory(histories);
                    currentHistories = histories;
                }
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createRecyclerView(view);
    }

    private void createRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyAdapter = new HistoryAdapter(this);
        recyclerView.setAdapter(historyAdapter);
    }


    @Override
    public void onDotsClick(int position, View view) {
        showPopUp(position, view);
    }

    private void showPopUp(int position, View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.delete:
                    QuizApp.quizDataBase.quizDao().deleteById(currentHistories.get(position).getId());
                    return true;
                case R.id.share:
                    History currentHistory = currentHistories.get(position);
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_TEXT, "QuizApp id: " + currentHistory.getId()
                            +"\nCategory: " + currentHistory.getCategory()
                            +"\nCorrect Answers: " + currentHistory.getCorrectAnswer() + "/" + currentHistory.getAmount()
                            + "\nDifficulty: " + currentHistory.getDifficulty()
                            + "\nDate: " + currentHistory.getCreatedAt());
                    HistoryFragment.this.startActivity(Intent.createChooser(intent, ""));
                    return true;
            }
            return false;
        });
        popupMenu.show();
    }
}