package com.android.crocodile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.android.crocodile.R;
import com.android.crocodile.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v-ikomarov on 11/18/2014.
 */
public class NameOfPlayersActivity extends Activity implements View.OnClickListener {
    private Button startGame;
    private Integer numberOfPlayers;
    LinearLayout llNameOfPlayersActivity;
    private List<EditText> nameOfPlayers;
    private List<TextView> textOfPlayers;

    public List<EditText> getNameOfPlayers() {
        return nameOfPlayers;
    }

    public void setNameOfPlayers(List<EditText> nameOfPlayers) {
        this.nameOfPlayers = nameOfPlayers;
    }

    public List<TextView> getTextOfPlayers() {
        return textOfPlayers;
    }

    public void setTextOfPlayers(List<TextView> textOfPlayers) {
        this.textOfPlayers = textOfPlayers;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_of_players);

        startGame = (Button) findViewById(R.id.startGame);
        llNameOfPlayersActivity = (LinearLayout) findViewById(R.id.ll_numberOfPlayers);

        startGame.setOnClickListener(this);

        setNumberOfPlayers(Integer.parseInt(getIntent().getStringExtra(Utils.NUMBER_OF_PLAYERS)));



        setNameOfPlayers(new ArrayList<EditText>());
        setTextOfPlayers(new ArrayList<TextView>());
        for(int i = 0; i < getNumberOfPlayers(); i++) {

            TextView textView  = new TextView(this);
            textView.setText("Player " + (i+1));
            getNameOfPlayers().add(i, new EditText(this));
            getTextOfPlayers().add(i, textView);


            llNameOfPlayersActivity.addView(getTextOfPlayers().get(i));
            llNameOfPlayersActivity.addView(getNameOfPlayers().get(i));

        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startGame:
                int i;
                for(i = 0; i < getNumberOfPlayers(); i++) {
                    if (getNameOfPlayers().get(i).getText().toString().isEmpty()) {
                        Toast.makeText(this, getString(R.string.namesOfPlayers), Toast.LENGTH_SHORT).show();
                        break;
                    }
                   if(i == (getNumberOfPlayers() - 1)) {
                       Intent intent = new Intent(this, ChoosePlayerActivity.class);
                       intent.putExtra(Utils.NUMBER_OF_PLAYERS, getNumberOfPlayers().toString());

                       ArrayList<String> playersNames = new ArrayList<String>();
                       for(int j = 0; j < getNumberOfPlayers() ; j++) {

                           playersNames.add(j, getNameOfPlayers().get(j).getText().toString());
                       }

                       intent.putStringArrayListExtra(Utils.NAME_OF_PLAYERS,  playersNames);



                       startActivity(intent);
                   }

                }
                break;
        }
    }
}
