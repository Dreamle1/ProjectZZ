package com.p.andrew.projectzz;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.LinkedList;
import java.util.List;

public class SIGame {

    private static List<Sprite> sprites;
    private static boolean playing;
    private static Player player1;
    private static Player player2;
    private static Bitmap pauseMenu;
    private List<Player> players;
    private static Ball ball;
    private static BackGround bg;
    public static final int HEIGHT = 480;
    public static final int WIDTH = 856;
    private static int HighScore;
    private static boolean pause;                       //if the game has paused
    private static boolean start;                       //if the game has started
    private int Difficulty;
    private static Resources res;
    private long startTime = System.nanoTime();
    private static int pauseMenuX;
    private static int pauseMenuY;
    private static Button resumeButton;
    private static Button backButton;
    private static Paint paint;


    public SIGame(Resources res) {
        sprites = new LinkedList<>();
        players = new LinkedList<>();
        bg = new BackGround(BitmapFactory.decodeResource(res,R.drawable.bg));
        ball = new Ball(BitmapFactory.decodeResource(res,R.drawable.ball1));
        player1 = new Player(BitmapFactory.decodeResource(res,R.drawable.playerbar),1);
        player2 = new Player(BitmapFactory.decodeResource(res,R.drawable.playerbar),2);
        players.add(player1);
        players.add(player2);
        HighScore = 0;
        pause = false;
        playing = false;
        Difficulty = 0;
        this.res = res;
        start = true;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static boolean getPause() {
        return pause;
    }

    public void update() {
        if (playing) {

            ball.update();
            Collision();
            long elapsed = (System.nanoTime() - startTime)/1000000;
            if (elapsed > 1000){
                GenerateSprites();
                startTime = System.nanoTime();
            }
            offBounds();
        }
        /*if(pause){
            if(resumeButton.getClicked()){
                unPause();
            }
        }*/
    }

    public static void draw(Canvas canvas){
        bg.draw(canvas);
        player1.draw(canvas);
        player2.draw(canvas);
        ball.draw(canvas);
        for(Sprite sprite:sprites){
            sprite.draw(canvas);
        }
        if (pause){
            drawPauseScreen(canvas);
        }
    }

    public void Collision() {
        if(ball.getrectangle().intersect(player1.getrectangle()) || ball.getrectangle().intersect(player2.getrectangle())){
            ball.dx = ball.dx * -1;
        }
        if(ball.getY() > HEIGHT || ball.getY() < 0){
            ball.dy = ball.dy * -1;
        }
    }

    public void Collision2(){

    }

    public void GenerateSprites(){
        int gen = (int) (Math.floor(Math.random() * 2) + Difficulty);
        for(int i = 0; i < gen; i++ ){
            Bitmap box = BitmapFactory.decodeResource(res, R.drawable.block);
            int range = WIDTH - (player1.getOffset() * 4);
            int randomx = (int) ((Math.floor(Math.random() * range / box.getWidth()) * box.getWidth())+ (player1.getOffset() * 2));
            int randomy = (int) (Math.floor(Math.random() * HEIGHT / box.getHeight()) * box.getHeight() );
            sprites.add(new Block(box,randomx,randomy));
        }
    }

    public void offBounds() {
        if (ball.getX() > WIDTH || ball.getX() < 0){
            ball.setX(WIDTH / 2);
            ball.setY(HEIGHT / 2);
            playing = false;
            if(ScorePanel.getScore() > HighScore)
            HighScore = ScorePanel.getScore();
            sprites.clear();
            ScorePanel.setScore(0);
            start = true;
        }
    }

    public static int getHighScore(){
        return HighScore;
    }

    public static boolean isPlaying() {
        return playing;
    }

    public static void pause(){
        pause = true;
        playing = false;
    }

    public static void unPause(){
        pause = false;
        if (!start) {
            playing = true;
        }
    }

    public void start() {
        start = false;
        playing = true;
    }

    public static void drawPauseScreen(Canvas canvas){
        canvas.drawBitmap(pauseMenu, pauseMenuX, pauseMenuY, null);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(HEIGHT / 10);
        canvas.drawText("Paused", WIDTH/ 2, pauseMenuY + (paint.getTextSize()), paint);
        paint.setTextSize(HEIGHT / 15);
        resumeButton.draw(canvas);
        backButton.draw(canvas);
    }

    public void setPause(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        pauseMenu = BitmapFactory.decodeResource(res,R.drawable.pausescreeen);
        pauseMenuX = WIDTH / 2 - pauseMenu.getWidth()/2;
        pauseMenuY = HEIGHT / 2 - pauseMenu.getHeight()/2;
        paint.setTextSize(HEIGHT / 15);
        resumeButton = new Button(pauseMenuX, (int) (pauseMenuY + 2*paint.getTextSize()+20),paint,"Resume",pauseMenu.getWidth(), (int) (paint.getTextSize()));
        backButton = new Button(pauseMenuX, (int) (pauseMenuY + 3*paint.getTextSize()+20),paint,"Back",pauseMenu.getWidth(), (int) (paint.getTextSize()));
    }

    public boolean getStart(){
        return start;
    }

    public Button getResumeButton(){
        return resumeButton;
    }

    public Button getBackButton(){
        return backButton;
    }


}
