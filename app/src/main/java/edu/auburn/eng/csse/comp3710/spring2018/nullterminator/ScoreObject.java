package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Will on 2018-04-10.
 */

public class ScoreObject {

    Context context;

    public int lastScore;
    public int highScore;

    public final int READ_BLOCK_SIZE = 100;

    private String filename = "scores.txt";

    public ScoreObject(Context contextIn) {
        context = contextIn;
        readFile();
    }

    private void readFile() {
        try {
            InputStream inputStream = context.openFileInput(filename);
            InputStreamReader inputStreamReader
                    = new InputStreamReader(inputStream);
            BufferedReader bufferedReader
                    = new BufferedReader(inputStreamReader);
            highScore = Integer.parseInt(bufferedReader.readLine());
            lastScore = Integer.parseInt(bufferedReader.readLine());
            inputStream.close();
        }
        catch (FileNotFoundException e) {
            String textToWrite = "0\n0";
            FileOutputStream outputStream;
            try {
                outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(textToWrite.getBytes());
                outputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            readFile();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    public void writeLastScore(String scoreIn) {
        String textToWrite = Integer.toString(highScore) + "\n" + scoreIn;
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(textToWrite.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        readFile();
    }

    public void writeHighScore(String scoreIn) {
        String textToWrite = scoreIn + "\n" + scoreIn;
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(textToWrite.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        readFile();
    }
}
