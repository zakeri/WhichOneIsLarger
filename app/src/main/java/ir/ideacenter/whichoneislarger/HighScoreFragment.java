package ir.ideacenter.whichoneislarger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;

public class HighScoreFragment extends Fragment {

    TextView highScore;
    RecyclerView rankList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_high_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        /*HighScoreUser hsu = SharedPreferenceManager.getInstance(getActivity()).getHighScoreUser();
        if (hsu == null) {
            highScore.setText("High Score: None!");
        }
        else {
            highScore.setText(
                    String.format("High Score: %d from %s", hsu.getHighScore(), hsu.getUserName())
            );
        }*/
        setupRankList();
    }

    private void setupRankList() {
        HighScoreList hsl = SharedPreferenceManager.getInstance(getActivity()).getHighScoreList();
        Comparator<HighScoreUser> userComparator = new Comparator<HighScoreUser>() {
            @Override
            public int compare(HighScoreUser o1, HighScoreUser o2) {
                if (o1.getHighScore() < o2.getHighScore())
                    return +1;
                else if (o1.getHighScore() > o2.getHighScore())
                    return -1;
                else
                    return 0;
            }
        };
        Collections.sort(hsl.getHighScoreUserList(), userComparator);

        HighScoreUserAdapter userAdapter = new HighScoreUserAdapter(hsl.getHighScoreUserList());
        rankList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rankList.setAdapter(userAdapter);
    }

    private void findViews(View view) {
        highScore = (TextView) view.findViewById(R.id.high_score_result);
        rankList = (RecyclerView) view.findViewById(R.id.ranklist);
    }
}
