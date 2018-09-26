package ir.ideacenter.whichoneislarger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HighScoreFragment extends Fragment {

    TextView highScore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_high_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        HighScoreUser hsu = SharedPreferenceManager.getInstance(getActivity()).getHighScoreUser();
        if (hsu == null) {
            highScore.setText("High Score: None!");
        }
        else {
            highScore.setText(
                    String.format("High Score: %d from %s", hsu.getHighScore(), hsu.getUserName())
            );
        }
    }

    private void findViews(View view) {
        highScore = (TextView) view.findViewById(R.id.high_score_result);
    }
}
