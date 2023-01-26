package com.example.tetris.presenters;

public class GamePresenter {
    private GameView gameView;
    private GameModel gameModel;
    private GameStatus localStatus;
    public  void setGameView (GameView gameView){
        this.gameView=gameView;
    }
    public void setGameModel (GameModel gameModel){
        this.gameModel=gameModel;
    }
    public void init(){
        gameModel.init();
        gameView.init(gameModel.getGameSize());
        gameModel.setGameOverListener(()->setStatus(GameStatus.OVER));
        gameModel.setScoreUpdatedListener(gameView::setScore);
        setStatus(GameStatus.START);
    }
    public void turn(GameTurn turn){
        gameModel.turn(turn);
    }
    public void changeStatus(){
        if (localStatus==GameStatus.PLAYING){
            pauseGame();
        } else {
            startGame();
        }
    }
    private void pauseGame (){
        setStatus(GameStatus.PAUSED);
        gameModel.pauseGame();
    }

    private void startGame(){
        setStatus(GameStatus.PLAYING);
        gameModel.startGame(gameView::draw);
    }
    private void setStatus(GameStatus status){
        if (status==GameStatus.OVER|| status == GameStatus.START){
            gameModel.newGame();
        }
        localStatus=status;
        gameView.setStatus(status);
    }
}
