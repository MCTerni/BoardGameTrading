package com.mcterni.board_game_trading;

import java.util.ArrayList;

public class GameOverviewList extends ArrayList<GameOverview> {

    public GameOverviewList(){
        add(new GameOverview(0, "Teste 0", 1.0));
        add(new GameOverview(1, "Teste 1", 1.1));
        add(new GameOverview(2, "Teste 2", 1.2));
    }
}
