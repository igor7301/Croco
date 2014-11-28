package com.android.crocodile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.android.crocodile.Card;
import com.android.crocodile.R;
import com.android.crocodile.Utils;

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
    private Button btnBlock1;
    private Button btnBlock2;
    private Button btnBlock3;
    private Button btnBlock4;
    private Button btnBlock5;




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
        btnBlock1 = (Button) findViewById(R.id.btnBlock1);
        btnBlock2 = (Button) findViewById(R.id.btnBlock2);
        btnBlock3 = (Button) findViewById(R.id.btnBlock3);
        btnBlock4 = (Button) findViewById(R.id.btnBlock4);
        btnBlock5 = (Button) findViewById(R.id.btnBlock5);
        btnBlock1.setOnClickListener(this);
        btnBlock2.setOnClickListener(this);
        btnBlock3.setOnClickListener(this);
        btnBlock4.setOnClickListener(this);
        btnBlock5.setOnClickListener(this);


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
        ImageView points;
            switch (view.getId()) {
                case R.id.btnGetNextCard:
                    if(!playersCards.isEmpty()) {
                        if (activeCardId == playersCards.size() - 1) {
                            Toast.makeText(this, getString(R.string.noAvailableCards), Toast.LENGTH_SHORT).show();
                        } else {
                            activeCardId = activeCardId + 1;
                            updateCardForPlayer();

                        }
                    }
                    else {
                        Toast.makeText(this, getString(R.string.noAvailableCards), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnGetPreviousCard:
                    if(!playersCards.isEmpty()) {
                        if (activeCardId == 0) {

                            Toast.makeText(this, getString(R.string.noAvailableCards), Toast.LENGTH_SHORT).show();
                        } else {
                            activeCardId = activeCardId - 1;
                            updateCardForPlayer();
                        }
                    }
                    else {
                        Toast.makeText(this, getString(R.string.noAvailableCards), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnBlock1:
                    points = (ImageView) findViewById(R.id.imgPointsBlock1);
                    if (points.isSelected()) {
                        points.setSelected(false);
                    } else {
                        points.setSelected(true);
                    }

                    if (points.isSelected()) {
                        points.setImageResource(R.drawable.points_three_active);

                    } else {
                        points.setImageResource(R.drawable.points_three_inactive);
                    }
                    break;

                case R.id.btnBlock2:
                   points = (ImageView) findViewById(R.id.imgPointsBlock2);
                    if (points.isSelected()) {
                        points.setSelected(false);
                    } else {
                        points.setSelected(true);
                    }

                    if (points.isSelected()) {
                        points.setImageResource(R.drawable.points_two_active);

                    } else {
                        points.setImageResource(R.drawable.points_two_inactive);
                    }
                    break;

                case R.id.btnBlock3:
                    points = (ImageView) findViewById(R.id.imgPointsBlock3);
                    if (points.isSelected()) {
                        points.setSelected(false);
                    } else {
                        points.setSelected(true);
                    }

                    if (points.isSelected()) {
                        points.setImageResource(R.drawable.points_two_active);

                    } else {
                        points.setImageResource(R.drawable.points_two_inactive);
                    }
                    break;

                case R.id.btnBlock4:
                    points = (ImageView) findViewById(R.id.imgPointsBlock4);
                    if (points.isSelected()) {
                        points.setSelected(false);
                    } else {
                        points.setSelected(true);
                    }

                    if (points.isSelected()) {
                        points.setImageResource(R.drawable.points_five_active);

                    } else {
                        points.setImageResource(R.drawable.points_five_inactive);
                    }
                    break;

                case R.id.btnBlock5:
                    points = (ImageView) findViewById(R.id.imgBlock5);
                    if (points.isSelected()) {
                        points.setSelected(false);
                    } else {
                        points.setSelected(true);
                    }

                    if (points.isSelected()) {
                        points.setImageResource(R.drawable.points_seven_active);

                    } else {
                        points.setImageResource(R.drawable.points_seven_inactive);
                    }
                    break;
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

        btnBlock1.setText(playersCards.get(activeCardId).getWord1() + "\n" +
                playersCards.get(activeCardId).getWord2() + "\n" +
                playersCards.get(activeCardId).getWord3());
        
        btnBlock2.setText(playersCards.get(activeCardId).getWord4() + "\n" +
                playersCards.get(activeCardId).getWord5());

        btnBlock3.setText(playersCards.get(activeCardId).getWord6());
        btnBlock4.setText(playersCards.get(activeCardId).getWord7());
        btnBlock5.setText(playersCards.get(activeCardId).getWord8());


        txtPlayerName.setText(playerName);
        txtCardID.setText(cardIDForDisplay.toString());




    }
}
