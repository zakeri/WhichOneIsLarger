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

    public void putHighScoreUser(HighScoreUser hsu) {
        Gson gson = new Gson();
        String hs = gson.toJson(hsu, HighScoreUser.class);
        editor.putString("high_score_user", hs);
        editor.apply();
    }

    public HighScoreList getHighScoreList() {
        String hsl = sharedPreferences.getString("high_score_list", null);
        if (hsl == null) {
            return new HighScoreList();
        }
        Gson gson = new Gson();
        return gson.fromJson(hsl, HighScoreList.class);
    }

    public void putHighScoreList(HighScoreList list) {
        Gson gson = new Gson();
        String hsl = gson.toJson(list, HighScoreList.class);
        editor.putString("high_score_list", hsl);
        editor.apply();
    }

}
