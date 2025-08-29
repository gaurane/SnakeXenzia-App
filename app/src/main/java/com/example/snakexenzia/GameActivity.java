package com.example.snakexenzia;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    public void gameOver() {
        runOnUiThread(() -> {
            Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
