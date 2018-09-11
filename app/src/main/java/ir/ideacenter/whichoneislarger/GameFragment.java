package ir.ideacenter.whichoneislarger;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameFragment extends Fragment {
    private final int GAME_LEVEL_MAX = 10;
    private final int GAME_TIME_MAX_MILLIS = 10000;
    private final int GAME_TIME_TICK_MILLIS = 1000;
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

    CountDownTimer gameTimer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gameLevel = 0;
        userScore = 0;
        currentLeftNumber = 0;
        currentRightNumber = 0;
        gameFinished = false;

        gameTimer = new CountDownTimer(GAME_TIME_MAX_MILLIS, GAME_TIME_TICK_MILLIS) {
            @Override
            public void onTick(long millisUntilFinished) {
                gameLevelBoard.setText(getString(R.string.game_timer, (int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                gameFinished = true;
                gameLevelBoard.setText(getString(R.string.game_finished));
            }
        };
        gameTimer.start();

        findViews(view);
        setButtonOnClicks();
        generateNumbers();
        gameLevel++;
        updateBoards();
    }

    @Override
    public void onPause() {
        super.onPause();

        gameTimer.cancel();
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

        /*if (gameLevel == GAME_LEVEL_MAX) {
            gameFinished = true;
        }
        else {
            gameLevel++;
        }*/
    }

    private void updateBoards() {
        userScoreBoard.setText(getString(R.string.user_points, userScore));
        /*if (!gameFinished) {
            gameLevelBoard.setText(getString(R.string.game_level, gameLevel, GAME_LEVEL_MAX));
        }
        else
            gameLevelBoard.setText(getString(R.string.game_finished));*/

    }

    private void generateNumbers() {
        if (gameFinished) return;
        currentLeftNumber = generateInt();
        currentRightNumber = generateInt();
        leftButton.setText(String.valueOf(currentLeftNumber));
        rightButton.setText(String.valueOf(currentRightNumber));
    }

    private void findViews(View view) {
        leftButton = (Button) view.findViewById(R.id.left_button);
        rightButton = (Button) view.findViewById(R.id.right_button);
        equalButton = (Button) view.findViewById(R.id.equal_button);
        userScoreBoard = (TextView) view.findViewById(R.id.user_score);
        gameLevelBoard = (TextView) view.findViewById(R.id.game_level);
    }

    private int generateInt() {
        Random randomInt = new Random();
        int i = randomInt.nextInt();
        if (i < 0) i *= -1;
        return (i % NUMBER_RANGE);
    }
}
