package com.mcterni.board_game_trading;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class GameOverviewListFragment extends ListFragment implements GameOverviewList.GameOverviewListCallback {

    private GameOverviewList games = new GameOverviewList().searchGameOverviewLis("carcassone", this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter < GameOverview > adapter = new ArrayAdapter<GameOverview>(getActivity(), android.R.layout.simple_list_item_1, games);
        setListAdapter(adapter);

    }


    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess() {

    }
}
