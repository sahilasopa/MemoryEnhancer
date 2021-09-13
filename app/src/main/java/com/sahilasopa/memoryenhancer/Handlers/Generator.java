package com.sahilasopa.memoryenhancer.Handlers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sahilasopa.memoryenhancer.SentencesActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Generator {
    private Context mContext;

    public Generator() {
    }

    public Generator(Context mContext) {
        this.mContext = mContext;
    }


    final String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    public String generateString() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(mContext.getAssets().open("word.txt"))); // Correct File Is words.txt
            String line = reader.readLine();
            List<String> words = new ArrayList<>();
            while (line != null) {
                String[] wordsLine = line.split(" ");
                words.addAll(Arrays.asList(wordsLine));
                line = reader.readLine();
            }

            Random rand = new Random(System.currentTimeMillis());
            return words.get(rand.nextInt(words.size()));
        } catch (Exception e) {
            int length = 6;
            StringBuilder builder = new StringBuilder(length);

            for (int i = 0; i < length; i++) {

                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());

                // add Character one by one in end of sb
                builder.append(AlphaNumericString
                        .charAt(index));
            }

//            return builder.toString().toUpperCase(Locale.ROOT);
            return builder.toString().toUpperCase(Locale.ROOT);
        }
    }

    public int generateNumber(int max) {
        if (max > 0) {
            Random random = new Random();
            return random.nextInt(max);
        }
        return 0;
    }
}