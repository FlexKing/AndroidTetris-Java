package com.example.tetris.views;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tetris.presenters.GameStatus;
import com.example.tetris.presenters.GameView;
import com.example.tetris.presenters.Point;

public class GameViewImpl implements GameView {
    private final GameFrame gameFrame;
    private final TextView gameScoreText;
    private final TextView gameStatusText;
    private final Button gameCtlButton;
    GameViewImpl(GameFrame gameFrame,TextView gameScoreText,TextView gameStatusText,Button gameCtlButton){
        this.gameFrame=gameFrame;
        this.gameScoreText=gameScoreText;
        this.gameStatusText=gameStatusText;
        this.gameCtlButton=gameCtlButton;
    }
    @Override
    public void init(int gameSize) {
     gameFrame.init(gameSize);
    }
    @Override
    public void draw(Point[][] points) {
            gameFrame.setPoints(points);
            gameFrame.invalidate();
    }
    @Override
    public void setScore(int score) {
            gameScoreText.setText("Score: "+ score);
    }
    @Override
    public void setStatus(GameStatus status) {
        gameStatusText.setText(status.getValue());
        gameStatusText.setVisibility(status==GameStatus.PLAYING ? View.INVISIBLE : View.VISIBLE);
        gameCtlButton.setText(status==GameStatus.PLAYING? "Pause" : "Start");
    }
}
