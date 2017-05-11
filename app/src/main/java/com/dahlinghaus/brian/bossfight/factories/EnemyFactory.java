package com.dahlinghaus.brian.bossfight.factories;

import android.content.res.AssetManager;

import com.dahlinghaus.brian.bossfight.exceptions.EnemyValidationException;
import com.dahlinghaus.brian.bossfight.helpers.Logger;
import com.dahlinghaus.brian.bossfight.models.Enemy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by edahlinghaus on 5/10/17.
 */

public final class EnemyFactory {
    // Array of valid monsters
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    // Allow us to rotate through each mob one by one
    private static int next_counter = 0;
    public Enemy next() {
        if( next_counter >= enemies.size() ) {
            next_counter = 0;
        }
        Enemy e = enemies.get(next_counter).clone();
        next_counter += 1;
        return e;
    }

    // Lookup monster by name
    public Enemy searchName(String name) {
        for(Enemy enemy : enemies) {
            if( enemy.getName() == name ) {
                return enemy.clone();
            }
        }
        return null;
    }

    // Get a random monster
    public Enemy random() {
        Random r = new Random();
        int index = r.nextInt(enemies.size());
        return enemies.get(index).clone();
    }

    // implement singleton
    // use: MonsterFactory.getInstance()
    private static EnemyFactory INSTANCE = null;
    public static synchronized EnemyFactory getInstance() {
        if( INSTANCE == null ) {
            INSTANCE = new EnemyFactory();
        }
        return INSTANCE;
    }

    // constructor for the instance class (object)
    public EnemyFactory() {
        if( INSTANCE != null ) {
            // TODO: Throw an exception here instead of just a message
            Logger.error("Can not load a second copy of EnemyFactory");
        }
    }

    // file loading
    public void loadEnemiesFromFile(AssetManager am) {
        InputStream is = null;
        BufferedReader br = null;
        String line = "";

        try {
            // Open reader for FILE_PATH
            is = am.open("enemies.csv");
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            // While we have new lines, load current line in to line and increment line
            while ((line = br.readLine()) != null) {
                if( !line.startsWith("//") ) {
                    handleLine(line);
                }
            }
        } catch (FileNotFoundException e) {
            handleLoadException(e);
        } catch (IOException e) {
            handleLoadException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    handleLoadException(e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    handleLoadException(e);
                }
            }
        }
    }

    // handle each line of the CSV file
    private final String CSV_SPLIT_BY = ",";
    private void handleLine(String s) {
        try {
            String[] enemy_string_array = s.split(CSV_SPLIT_BY);
            Enemy enemy = new Enemy(enemy_string_array);
            enemies.add(enemy);
        } catch ( EnemyValidationException e ) {
            Logger.error(e.getMessage());
        }
    }

    private void handleLoadException(Exception e) {
        Logger.error_f("Could not load monsters because: ", e.getMessage());
        e.printStackTrace();
    }
}
