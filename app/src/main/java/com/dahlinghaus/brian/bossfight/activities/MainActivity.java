package com.dahlinghaus.brian.bossfight.activities;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dahlinghaus.brian.bossfight.R;
import com.dahlinghaus.brian.bossfight.factories.EnemyFactory;
import com.dahlinghaus.brian.bossfight.models.Enemy;
import com.dahlinghaus.brian.bossfight.models.Player;

public class MainActivity extends AppCompatActivity {

    // Set up "global" objects
    Player newPlayer;
    EnemyFactory enemyFactory;
    Enemy myEnemy;

    // Set up screen elements
    TextView bossMessage;
    TextView bossHp;
    TextView experiencePoints;
    TextView output;

    // Handle App Start
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attaches layout XML ID tag to TextView variable
        bossMessage = (TextView) findViewById(R.id.bossMessage);
        bossHp = (TextView) findViewById(R.id.bossHp);
        experiencePoints = (TextView) findViewById(R.id.experiencePoints);
        output = (TextView) findViewById(R.id.output);

        // Set up player
        newPlayer = Player.getInstance();

        // Set up enemy factory
        enemyFactory = EnemyFactory.getInstance();
        AssetManager am = getAssets();
        enemyFactory.loadEnemiesFromFile(am);

        // Spawn a new monster
        spawnNewMonster();

        // Show experience of player on load
        updateExperienceView();
    }

    // Battle Button
    public void battle(View v) {
        // Do damage to enemy based on the player and set monster HP on screen
        if( myEnemy.doDamage(newPlayer) ) {
            writeTo(output, "You did %d damage!", newPlayer.getLastDamageDealt());
        } else {
            writeTo(output, "Your attack missed!");
        }
        updateBossHPView();

        // Check for dead enemy and spawn a new one if necessary
        if( myEnemy.isDead() ) {
            // Gain XP
            int exp_gain = newPlayer.gainExp(myEnemy);
            updateExperienceView();

            // Show nice message to the user congratulating them on their success
            writeTo(output, "You did %d damage, and killed the %s! Gained %d EXP", newPlayer.getLastDamageDealt(), myEnemy.getName(), exp_gain);

            // Spawn new monster to fight
            spawnNewMonster();
        }
    }

    private void spawnNewMonster() {
        myEnemy = enemyFactory.next();
        writeTo(bossMessage, "%s has appeared!", myEnemy.getName());
        updateBossHPView();
    }

    private void updateBossHPView() {
        writeTo(bossHp, myEnemy.getCurrentHP());
    }

    private void updateExperienceView() {
        writeTo(experiencePoints, newPlayer.getExp());
    }
    // END Battle Button

    // Helpers to write to TextViews
    // use like writeTo(bossMessage, "something %d", integerthing);
    private void writeTo(TextView v, String s) {
        v.setText(s);
    }

    private void writeTo(TextView v, String s, Object... args) {
        writeTo(v, String.format(s, args));
    }

    private void writeTo(TextView v, Object o) {
        String s = String.valueOf(o);
        writeTo(v, s);
    }


    // Stats View
//    protected void gotoStats(View v) {
//        Intent intent = new Intent(this, StatsActivity.class);
//        startActivity(intent);
//    }
    // END Stats View
}
