package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tetris.models.GameModelFactory;
import com.example.tetris.models.GameType;
import com.example.tetris.presenters.GamePresenter;
import com.example.tetris.presenters.GameTurn;
import com.example.tetris.views.GameFrame;
import com.example.tetris.views.GameViewFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameFrame gameFrame = findViewById(R.id.game_container);
        TextView gameScoreText = findViewById(R.id.game_score);
        TextView gameStatusText = findViewById(R.id.game_status);
        Button gameCtlBtn=findViewById(R.id.game_ctl_btn);
        GamePresenter gamePresenter = new GamePresenter();
        gamePresenter.setGameModel(GameModelFactory.newGameModel(GameType.TETRIS));
        gamePresenter.setGameView(GameViewFactory.newGameView(gameFrame,gameScoreText,gameStatusText,gameCtlBtn));


        Button fireButton = findViewById(R.id.fire_btn);
        Button upButton = findViewById(R.id.up_btn);
        Button downButton = findViewById(R.id.down_btn);
        Button leftButton = findViewById(R.id.left_btn);
        Button rightButton = findViewById(R.id.right_btn);

        gameCtlBtn.setOnClickListener(v->gamePresenter.changeStatus());
        fireButton.setOnClickListener(v->gamePresenter.turn(GameTurn.FIRE));
        upButton.setOnClickListener(v->gamePresenter.turn(GameTurn.UP));
        downButton.setOnClickListener(v->gamePresenter.turn(GameTurn.DOWN));
        leftButton.setOnClickListener(v->gamePresenter.turn(GameTurn.LEFT));
        rightButton.setOnClickListener(v->gamePresenter.turn(GameTurn.RIGHT));

        gamePresenter.init();
    }
}