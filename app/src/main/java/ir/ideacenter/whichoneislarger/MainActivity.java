package ir.ideacenter.whichoneislarger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        configure();
    }

    private void findViews() {
        startGame = (Button) findViewById(R.id.start_game);
    }

    private void configure() {
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameFragment gameFragment = new GameFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_game_container, gameFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
