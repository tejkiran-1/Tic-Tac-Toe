package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private Button playAgin;
    private TextView res;
    private GridLayout gridLayout;
    int[][] winninngArray = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int activePlayer = 0; // 0 for player 1(red) & 1 for player 2(yellow)
    boolean gameEnded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgin = findViewById(R.id.playAgain);
        res = findViewById(R.id.gameTitle);
        gridLayout = findViewById(R.id.gridLayout);

        playAgin.setVisibility(View.INVISIBLE);
        playAgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res.setText("Enjoy The Game!");
                gameEnded = false;
                activePlayer = 0;

                for (int i = 0; i < gameState.length; i++) {
                    gameState[i] = 2;
                }

                for (int i = 0; i < gridLayout.getChildCount(); i++) {
                    ImageView imgCount = (ImageView) gridLayout.getChildAt(i);
                    imgCount.setImageDrawable(null);
                }

                for (int i = 0; i < gameState.length; i++) {
                    gameState[i] = 2;
                }
            }
        });
    }
    /*
    int[][] winninngArray = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 6}, {0, 4, 8}, {2, 4, 6}};
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int activePlayer = 0; // 0 for player 1(red) & 1 for player 2(yellow)
    boolean gameEnded = false;
    */
    public void dropIn(View view) {
        ImageView imgCount = (ImageView) view;
        int tag = Integer.parseInt(imgCount.getTag().toString());
        playAgin.setVisibility(View.INVISIBLE);
        if (gameState[tag] == 2 && gameEnded == false) {
            gameState[tag] = activePlayer;
            imgCount.setTranslationY(-1000);
            imgCount.animate().translationY(0).rotation(500).setDuration(500);
            if (activePlayer == 0) {
                imgCount.setImageResource(R.drawable.red);
                activePlayer = 1;
            }
            else {
                imgCount.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            for (int[] winningPos : winninngArray) {
                if (gameState[winningPos[0]] == gameState[winningPos[1]] && gameState[winningPos[1]] == gameState[winningPos[2]] && gameState[winningPos[0]] != 2) {
                    gameEnded = true;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Red";
                    }
                    else {
                        winner = "Yellow";
                    }
                    res.setText(winner + " won!");
                    res.animate().scaleX(1.5f).scaleY(1.5f).setDuration(1000);
                    playAgin.setVisibility(View.VISIBLE);
                    break;
                }
            }
        }
    }
}
