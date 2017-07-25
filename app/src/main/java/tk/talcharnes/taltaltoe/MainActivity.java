package tk.talcharnes.taltaltoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent starterIntent;

    boolean playing = true;
    int turn = 0;
    GridLayout gameBoard;
    Button restartButton;
    TextView restartText;
    TextView winnerView;
    String winner;


    Button A1;
    Button A2;
    Button A3;
    Button B1;
    Button B2;
    Button B3;
    Button C1;
    Button C2;
    Button C3;

    int restartMarginTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        starterIntent = getIntent();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gameBoard = (GridLayout) findViewById(R.id.gameBoard);
        restartButton = (Button) findViewById(R.id.restartbutton);
        restartText = (TextView) findViewById(R.id.gameoverText);
        winnerView = (TextView) findViewById(R.id.winner_string);
        restartMarginTop = toolbar.getLayoutParams().height + 50;


        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) restartText.getLayoutParams();
        params.topMargin = restartMarginTop;

        A1 =(Button) findViewById(R.id.A1);
        A2 =(Button) findViewById(R.id.A2);
        A3 =(Button) findViewById(R.id.A3);
        B1 =(Button) findViewById(R.id.B1);
        B2 =(Button) findViewById(R.id.B2);
        B3 =(Button) findViewById(R.id.B3);
        C1 =(Button) findViewById(R.id.C1);
        C2 =(Button) findViewById(R.id.C2);
        C3 =(Button) findViewById(R.id.C3);
    }
    public void playGame(View view){
        if(playing) {
            if (turn % 2 == 0) {
                player1Click((Button) view);
                checkIfGameOver();
            } else {
                player2Click((Button) view);
                checkIfGameOver();
            }
            turn++;
        }
    }

    private void player1Click(Button view){
        view.setText(getString(R.string.player1));
        view.setClickable(false);
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }
    private void player2Click(Button view){
        view.setText(getString(R.string.player2));
        view.setClickable(false);
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    private boolean checkIfGameOver(){
        if(isSameKey(A1, A2, A3) || isSameKey(B1, B2, B3)
                || isSameKey(C1, C2, C3) || isSameKey(A1, B1, C1)
                || isSameKey(A2, B2, C2) || isSameKey(A3, B3, C3)
                || isSameKey(A1, B2, C3) || isSameKey(A3, B2, C1)){

            gameOver();

            return true;

        }
        else if(turn == 8){
            winner = getString(R.string.draw_game);
            gameOver();
            return true;
        }
        else return false;
    }
    private boolean isSameKey(Button b1, Button b2, Button b3) {
        if (!b1.getText().toString().isEmpty() && !b2.getText().toString().isEmpty() && !b3.getText().toString().isEmpty()) {
            if (b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText())) {
                if(b1.getText().equals(getString(R.string.player1))){
                    winner = getString(R.string.player_1_wins);
                }
                else {
                    winner = getString(R.string.player_2_wins);
                }
                return true;
            }
            else return false;
        }
        else return false;
    }

    public void restart(View view) {
        finish();
        startActivity(starterIntent);
    }

    private void gameOver(){
        playing = false;
        gameBoard.setAlpha(.3f);
        restartText.setVisibility(View.VISIBLE);
        restartButton.setVisibility(View.VISIBLE);
        winnerView.setText(winner);
        winnerView.setVisibility(View.VISIBLE);
    }
}
