package be.bxl.formation.demo_04_persistence_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tv_main_welcome);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Récuperation du username stocker dans les préférences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(prefs.getBoolean(LoginActivity.PREF_KEY_SAVE, false)) {
            String username = prefs.getString(LoginActivity.PREF_KEY_USERNAME, null);
            tvWelcome.setText("Hello " + username);
        }
        else {
            tvWelcome.setText("Welcome !");
        }
    }
}