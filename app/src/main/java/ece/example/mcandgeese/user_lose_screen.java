package ece.example.mcandgeese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ece.example.mcandgeese.R;

public class user_lose_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lose_screen);
    }
    public void goToMainScreen(View view) {
        Intent intent = new Intent(user_lose_screen.this, MainActivity.class);
        startActivity(intent);
    }
}