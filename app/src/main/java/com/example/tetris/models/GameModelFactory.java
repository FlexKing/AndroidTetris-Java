package com.example.tetris.models;

import com.example.tetris.presenters.GameModel;

public class GameModelFactory {
    private GameModelFactory(){

    }
    public static GameModel newGameModel(GameType type){
        switch (type){
            case TETRIS:
                return new TetrisGameModel();
            default:
                return null;
        }
    }
}
