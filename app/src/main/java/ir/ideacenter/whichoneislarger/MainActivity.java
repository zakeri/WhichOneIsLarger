package ir.ideacenter.whichoneislarger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int GAME_LEVEL_MAX = 10;
    private final int NUMBER_RANGE = 30;
    private final int LEFT_BUTTON = 0;
    private final int RIGHT_BUTTON = 1;
    private final int EQUAL_BUTTON = 2;

    TextView userScoreBoard;
    TextView gameLevelBoard;
    Button leftButton;
    Button rightButton;
    Button equalButton;

    private int gameLevel;
    private int userScore;
    private int currentLeftNumber;
    private int currentRightNumber;
    Boolean gameFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLevel = 0;
        userScore = 0;
        currentLeftNumber = 0;
        currentRightNumber = 0;
        gameFinished = false;

        findViews();
        setButtonOnClicks();
        generateNumbers();
        gameLevel++;
        updateBoards();
    }

    private void setButtonOnClicks() {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameFinished) return;
                evaluate(LEFT_BUTTON);
                generateNumbers();
                updateBoards();
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameFinished) return;
                evaluate(RIGHT_BUTTON);
                generateNumbers();
                updateBoards();
            }
        });

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameFinished) return;
                evaluate(EQUAL_BUTTON);
                generateNumbers();
                updateBoards();
            }
        });
    }

    private void evaluate(int whichPressed) {
        if (whichPressed == LEFT_BUTTON) {
            if (currentLeftNumber > currentRightNumber) userScore++;
        }
        else if (whichPressed == RIGHT_BUTTON) {
            if (currentLeftNumber < currentRightNumber) userScore++;
        }
        else if (whichPressed == EQUAL_BUTTON) {
            if (currentLeftNumber == currentRightNumber) userScore++;
        }

        if (gameLevel == GAME_LEVEL_MAX) {
            gameFinished = true;
        }
        else {
            gameLevel++;
        }
    }

    private void updateBoards() {
        userScoreBoard.setText(getString(R.string.user_points, userScore));
        if (!gameFinished)
            gameLevelBoard.setText(getString(R.string.game_level, gameLevel, GAME_LEVEL_MAX));
        else
            gameLevelBoard.setText(getString(R.string.game_finished));

    }

    private void generateNumbers() {
        if (gameFinished) return;
        currentLeftNumber = generateInt();
        currentRightNumber = generateInt();
        leftButton.setText(String.valueOf(currentLeftNumber));
        rightButton.setText(String.valueOf(currentRightNumber));
    }

    private void findViews() {
        leftButton = (Button) findViewById(R.id.left_button);
        rightButton = (Button) findViewById(R.id.right_button);
        equalButton = (Button) findViewById(R.id.equal_button);
        userScoreBoard = (TextView) findViewById(R.id.user_score);
        gameLevelBoard = (TextView) findViewById(R.id.game_level);
    }

    private int generateInt() {
        Random randomInt = new Random();
        int i = randomInt.nextInt();
        if (i < 0) i *= -1;
        return (i % NUMBER_RANGE);
    }

}
