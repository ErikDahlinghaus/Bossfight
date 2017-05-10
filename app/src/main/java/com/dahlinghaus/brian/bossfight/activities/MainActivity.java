package com.dahlinghaus.brian.bossfight.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dahlinghaus.brian.bossfight.R;
import com.dahlinghaus.brian.bossfight.models.Enemy;
import com.dahlinghaus.brian.bossfight.models.Player;

public class MainActivity extends AppCompatActivity {
    Enemy myEnemy;
    Player newPlayer = Player.getPlayerInstance();

    TextView bossMessage;
    TextView bossHp;
    TextView experiencePoints;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attaches layout XML ID tag to TextView variable
        bossMessage = (TextView) findViewById(R.id.bossMessage);
        bossHp = (TextView) findViewById(R.id.bossHp);
        experiencePoints = (TextView) findViewById(R.id.experiencePoints);
        output = (TextView) findViewById(R.id.output);

        // Spawn a new monster
        spawnNewMonster();

        // Show experience of player on load
        updateExperienceView();
    }

    // Battle Button
    protected void battle(View v) {

        myEnemy.health -= newPlayer.attack();
        output.setText("You did " + newPlayer.attack() + " damage!");
        if(myEnemy.health <= 0) {
            bossHp.setText("0");
        }else {
            bossHp.setText(String.format("%d", myEnemy.getHealth()));
        }
        if (myEnemy.isDead()) {
            // Show nice message to the user congratulating them on their success
            output.setText("You did " + newPlayer.attack() + " damage, and killed the " + myEnemy.getName() + "!");

            // Gain XP
            newPlayer.gainExp();
            updateExperienceView();

            // Spawn new monster to fight
            spawnNewMonster();
        }
    }

    private void spawnNewMonster() {
        myEnemy = new Enemy();
        bossMessage.setText(myEnemy.getName() + " has appeared!");
        bossHp.setText(String.format("%d", myEnemy.getHealth()));
    }

    private void updateExperienceView() {
        experiencePoints.setText(String.format("%d", newPlayer.getExp()));
    }
    // END Battle Button


    // Stats View
//    protected void gotoStats(View v) {
//        Intent intent = new Intent(this, StatsActivity.class);
//        startActivity(intent);
//    }
    // END Stats View
}
