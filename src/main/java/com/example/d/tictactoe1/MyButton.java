package com.example.d.tictactoe1;

import android.content.Context;
import android.widget.Button;
/**
 * Created by D on 2/2/2017.
 */
public class MyButton extends Button {
    int player;
    public MyButton(Context context) {
        super(context);
    }
    int getPlayer(){return player;}
    int setPlayer(int player){this.player=player;
    return player;}

    /*@Override
    public void setTextSize(float size) {
        super.setTextSize(size);
    }*/
}
