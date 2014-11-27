package com.android.crocodile.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.crocodile.Card;
import com.android.crocodile.R;
import com.android.crocodile.Utils;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by v-ikomarov on 11/19/2014.
 */
public class ActiveCardActivity extends Activity implements View.OnClickListener {

    private ArrayList<Card> playersCards;
    private Integer playerId;
    private String playerName;
    private Integer activeCardId = 0;
    private ImageButton btnGetNextCard;
    private ImageButton btnGetPreviousCard;
    private TextView txtPlayerName;
    private TextView txtCardID;

   private TextView txtWord1;
   private TextView txtWord2;
   private TextView txtWord3;
   private TextView txtWord4;
   private TextView txtWord5;
   private TextView txtWord6;
   private TextView txtWord7;
   private TextView txtWord8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_card);

        btnGetNextCard = (ImageButton) findViewById(R.id.btnGetNextCard);
        btnGetNextCard.setOnClickListener(this);
        btnGetPreviousCard = (ImageButton) findViewById(R.id.btnGetPreviousCard);
        btnGetPreviousCard.setOnClickListener(this);
        txtPlayerName = (TextView) findViewById(R.id.nameOfPlayer);
        txtCardID = (TextView) findViewById(R.id.cardID);


        txtWord1 = (TextView) findViewById(R.id.txtWord1);
        txtWord2 = (TextView) findViewById(R.id.txtWord2);
        txtWord3 = (TextView) findViewById(R.id.txtWord3);
        txtWord4 = (TextView) findViewById(R.id.txtWord4);
        txtWord5 = (TextView) findViewById(R.id.txtWord5);
        txtWord6 = (TextView) findViewById(R.id.txtWord6);
        txtWord7 = (TextView) findViewById(R.id.txtWord7);
        txtWord8 = (TextView) findViewById(R.id.txtWord8);


        Intent intent = getIntent();
        playersCards =  intent.getParcelableArrayListExtra(Utils.PLAYERS_CARDS);
        activeCardId = Integer.parseInt(intent.getStringExtra(Utils.ACTIVE_CARD_ID));


        playerName = intent.getStringExtra(Utils.NAME_OF_PLAYERS);

        playerId = Integer.parseInt(intent.getStringExtra(Utils.PLAYER_ID));

        if(!playersCards.isEmpty()) {
            updateCardForPlayer();
        }
        else {
            Toast.makeText(this, getString(R.string.noAvailableCards), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {
        if(!playersCards.isEmpty()) {
            switch (view.getId()) {
                case R.id.btnGetNextCard:

                    //if(playersCards.size() <= 1) {
                    if(activeCardId == playersCards.size() - 1) {

                        Toast.makeText(this, getString(R.string.noAvailableCards),  Toast.LENGTH_SHORT).show();
                    }
                    else {
                        activeCardId = activeCardId + 1;
                        // playersCards.remove(activeCardId);
                        updateCardForPlayer();

                    }
                    break;
                case R.id.btnGetPreviousCard:
                    //if(playersCards.size() <= 1) {
                    if(activeCardId == 0) {

                        Toast.makeText(this, getString(R.string.noAvailableCards),  Toast.LENGTH_SHORT).show();
                    }
                    else {
                        activeCardId = activeCardId - 1;
                        // playersCards.remove(activeCardId);
                        updateCardForPlayer();


                    }
                    break;
            }
        }
        else {
            Toast.makeText(this, getString(R.string.noAvailableCards), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChoosePlayerActivity.class);
       // intent.putParcelableArrayListExtra(Utils.PLAYERS_CARDS, playersCards);
        intent.putExtra(Utils.PLAYER_ID, playerId.toString());
        intent.putExtra(Utils.ACTIVE_CARD_ID, activeCardId.toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void updateCardForPlayer() {
        Integer  cardIDForDisplay = activeCardId + 1;
        txtPlayerName.setText(playerName);
        txtCardID.setText(cardIDForDisplay.toString());
        txtWord1.setText(playersCards.get(activeCardId).getWord1());
        txtWord2.setText(playersCards.get(activeCardId).getWord2());
        txtWord3.setText(playersCards.get(activeCardId).getWord3());
        txtWord4.setText(playersCards.get(activeCardId).getWord4());
        txtWord5.setText(playersCards.get(activeCardId).getWord5());
        txtWord6.setText(playersCards.get(activeCardId).getWord6());
        txtWord7.setText(playersCards.get(activeCardId).getWord7());
        txtWord8.setText(playersCards.get(activeCardId).getWord8());
    }
}
