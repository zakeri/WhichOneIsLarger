package ir.ideacenter.whichoneislarger;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferenceManager {

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;
    private static SharedPreferenceManager instance = null;

    public  static SharedPreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceManager(context);
        }
        return instance;
    }

    private SharedPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public HighScoreUser getHighScoreUser() {
        String user = sharedPreferences.getString("high_score_user", null);
        if (user == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(user, HighScoreUser.class);
    }

    public void putHighScoreUser(int highScore, String userName) {
        HighScoreUser h = new HighScoreUser(userName, highScore);
        Gson gson = new Gson();
        String hs = gson.toJson(h, HighScoreUser.class);
        editor.putString("high_score_user", hs);
        editor.apply();
    }
}
