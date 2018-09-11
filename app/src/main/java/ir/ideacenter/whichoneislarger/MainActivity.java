package ir.ideacenter.whichoneislarger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameFragment gameFragment = new GameFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_game_container, gameFragment)
                .addToBackStack(null)
                .commit();
    }
}
