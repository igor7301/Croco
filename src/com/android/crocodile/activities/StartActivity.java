package com.android.crocodile.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.crocodile.R;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by v-ikomarov on 11/24/2014.
 */
public class StartActivity extends Activity implements View.OnClickListener {
    private Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        startGame = (Button) findViewById(R.id.btnStart);
        startGame.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStart:
                Intent intent = new Intent(this, NumberOfPlayersActivity.class);
                startActivity(intent);
                break;
        }
    }

}
