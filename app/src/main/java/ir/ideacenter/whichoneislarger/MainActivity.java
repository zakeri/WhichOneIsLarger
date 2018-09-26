package ir.ideacenter.whichoneislarger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button startGame;
    Button highScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        configure();
    }

    private void findViews() {
        startGame = (Button) findViewById(R.id.start_game);
        highScores = (Button) findViewById(R.id.high_score);
    }

    private void configure() {
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetNameDialog getNameDialog = new GetNameDialog(
                        MainActivity.this,
                        new OnNameSelectedListener() {
                            @Override
                            public void onNameSelected(String name) {
                                Log.d("TAG", "playerName = " + name);

                                GameFragment gameFragment = new GameFragment();

                                Bundle bundle = new Bundle();
                                bundle.putString("player_name", name);
                                gameFragment.setArguments(bundle);

                                getSupportFragmentManager().beginTransaction()
                                        .add(R.id.fragment_game_container, gameFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        });
                getNameDialog.show();

            }
        });

        highScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HighScoreFragment highScoreFragment = new HighScoreFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_high_score, highScoreFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
