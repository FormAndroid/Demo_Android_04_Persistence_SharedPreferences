package be.bxl.formation.demo_04_persistence_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String PREF_KEY_USERNAME = "username";
    public static final String PREF_KEY_PASSWORD = "password";
    public static final String PREF_KEY_SAVE = "remember";

    EditText etUsername, etPassword;
    CheckBox ckRememberMe;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_login_username);
        etPassword = findViewById(R.id.et_login_password);
        ckRememberMe = findViewById(R.id.ck_login_remember_me);
        btnLogin = findViewById(R.id.btn_login_valid);

        btnLogin.setOnClickListener(v -> login());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Recuperation des données des préférences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(prefs.getBoolean(PREF_KEY_SAVE, false)) {
            String username = prefs.getString(PREF_KEY_USERNAME, "");
            String password = prefs.getString(PREF_KEY_PASSWORD, "");

            etUsername.setText(username);
            etPassword.setText(password);
            ckRememberMe.setChecked(true);
        }

    }

    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        boolean remember = ckRememberMe.isChecked();

        // Fausse verification du mot de passe
        if(!password.equals("Test1234=")) {
            Toast.makeText(this, "Bad Password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sauvegarder (si necessaire) des infos
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        if(remember) {
            editor.putString(PREF_KEY_USERNAME, username);
            editor.putString(PREF_KEY_PASSWORD, password);
            editor.putBoolean(PREF_KEY_SAVE, true);
        }
        else {
            editor.remove(PREF_KEY_USERNAME);
            editor.remove(PREF_KEY_PASSWORD);
            // Alternative au remove -> editor.clear();
            editor.putBoolean(PREF_KEY_SAVE, false);
        }
        editor.apply(); // <- Ne pas l'oublier ;)

        // Acceder à la seconde activité
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
        finish();
    }


}