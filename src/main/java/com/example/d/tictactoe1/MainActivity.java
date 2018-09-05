package com.example.d.tictactoe1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyButton button[][];
    LinearLayout row[];
    LinearLayout myLayout;
    int n=3;
    public static final int NO_PLAYER=0;
    public static final int PLAYER_1=1;
    boolean gameOver;
    boolean player1Turn;

    public static final int PLAYER_2=2;
    public static final int PLAYER1_WINS=1;
    public static final int PLAYER2_WINS=2;
    public static final int DRAW=3;
    public static final int INCOMPLETE=0;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLayout= (LinearLayout) findViewById(R.id.activity_main_l);// doubt
        setUpBoard();

    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.newGame){
            resetBoard();
        }
        else if(id==R.id.boardSize_3)
        {
            n=3; setUpBoard();
        }
        else if(id==R.id.boardSize_4)
        {
            n=4; setUpBoard();
        }
        else if(id==R.id.boardSize_5)
        {
            n=5; setUpBoard();
        }
        return true;

    }

    private void resetBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                button[i][j].setPlayer(NO_PLAYER);
                button[i][j].setText("");

            }
        }
        gameOver=false;
        player1Turn=true;
    }
    private void setUpBoard() {
        player1Turn=true;
        gameOver=false;
        button=new MyButton[n][n];
        row=new LinearLayout[n];
        myLayout.removeAllViews();
        for (int i = 0; i < n; i++) {
            row[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            params.setMargins(5, 5, 5, 5);
            row[i].setLayoutParams(params);
            row[i].setOrientation(LinearLayout.HORIZONTAL);
            myLayout.addView(row[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                button[i][j] = new MyButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(5, 5, 5, 5);
                button[i][j].setLayoutParams(params);
                button[i][j].setText("");
                button[i][j].setPlayer(NO_PLAYER);
                button[i][j].setOnClickListener(this);
                row[i].addView(button[i][j]);
            }
        }
    }

    public void onClick(View v){
        MyButton b=(MyButton) v;
        if(gameOver){return;}
        if(b.getPlayer()!= NO_PLAYER)
        {
            Toast.makeText(this, "Invalid Move !!", Toast.LENGTH_SHORT).show();
        }


        if (player1Turn)
        {   b.setPlayer(PLAYER_1);
            b.setTextSize(120);
            b.setText("X");

        }
        else
        {   b.setPlayer(PLAYER_2);
            b.setTextSize(120);
            b.setText("O");
        }

        int status=gameStatus();

        if (status==PLAYER1_WINS||status==PLAYER2_WINS){
            Toast.makeText(this, "Player "+ status+" WINS", Toast.LENGTH_SHORT).show();
            gameOver=true;
        }
        if (status==DRAW){
            Toast.makeText(this, "DRAW", Toast.LENGTH_SHORT).show();
            gameOver=true;
            return;
        }
        player1Turn=!player1Turn;
    }

    private int gameStatus() {
         for (int i = 0; i < n; i++) {
             boolean flag = true;
             for (int j = 0; j < n; j++) {
                 if (button[i][j].getPlayer() == NO_PLAYER || button[i][0].getPlayer() != button[i][j].getPlayer()) {
                     flag = false;
                     break;
                 }
             }


             if (flag){
                 if(button[i][0].getPlayer()==PLAYER_1){
                     return PLAYER1_WINS;
                 }
                 else{
                     return PLAYER2_WINS;
                 }
             }
         }


        for (int j = 0; j < n; j++) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (button[i][j].getPlayer() == NO_PLAYER || button[0][j].getPlayer() != button[i][j].getPlayer()) {
                    flag = false;
                    break;
                }
            }


            if (flag){
                if(button[0][j].getPlayer()==PLAYER_1){
                    return PLAYER1_WINS;
                }
                else{
                    return PLAYER2_WINS;
                }
            }
        }

        boolean flag = true;
        for (int j = 0; j < n; j++) {
                if (button[j][j].getPlayer() == NO_PLAYER || button[0][0].getPlayer() != button[j][j].getPlayer()) {
                    flag = false;
                    break;
                }
        }
                if (flag){
                    if(button[0][0].getPlayer()==PLAYER_1){
                        return PLAYER1_WINS;
                    }
                    else{
                        return PLAYER2_WINS;
                    }
                }

        flag = true;
        for(int i=n-1;i>=0;i--)
        {int j=n-1-i;
            if(button[i][j].getPlayer()==NO_PLAYER||button[0][n-1].getPlayer()!=button[i][j].getPlayer())
            {
                flag=false;
                break;
            }

        }
        if (flag){
            if(button[0][n-1].getPlayer()==PLAYER_1){
                return PLAYER1_WINS;
            }
            else{
                return PLAYER2_WINS;
            }
        }
        for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (button[i][j].getPlayer() == NO_PLAYER) {
                            return INCOMPLETE;
                        }
            }
        }
        return DRAW;




    }

}
