package com.android.crocodile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.android.crocodile.Card;
import com.android.crocodile.R;
import com.android.crocodile.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by v-ikomarov on 11/19/2014.
 */
public class ActiveCardActivity extends Activity implements View.OnClickListener {
    private SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");

    private final  static int POINTS_BLOCK1 = 2;
    private final  static int POINTS_BLOCK2 = 3;
    private final  static int POINTS_BLOCK3 = 2;
    private final  static int POINTS_BLOCK4 = 5;
    private final  static int POINTS_BLOCK5 = 7;

    private ArrayList<Card> playersCards;
    private Integer playerId;
    private String playerName;
    private Integer activeCardId = 0;
    private ImageButton btnGetNextCard;
    private ImageButton btnGetPreviousCard;
    private TextView txtPlayerName;
    private TextView txtTimer;
    private TextView txtCardID;
    private Button btnBlock1;
    private Button btnBlock2;
    private Button btnBlock3;
    private Button btnBlock4;
    private Button btnBlock5;
    private ImageView imgPointForBlock1;
    private ImageView imgPointForBlock2;
    private ImageView imgPointForBlock3;
    private ImageView imgPointForBlock4;
    private ImageView imgPointForBlock5;
    private CountDownTimer timer;
    private Date myDate = new Date();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_card);

        btnGetNextCard = (ImageButton) findViewById(R.id.btnGetNextCard);
        btnGetNextCard.setOnClickListener(this);
        btnGetPreviousCard = (ImageButton) findViewById(R.id.btnGetPreviousCard);
        btnGetPreviousCard.setOnClickListener(this);
        txtPlayerName = (TextView) findViewById(R.id.nameOfPlayer);
        txtTimer = (TextView) findViewById(R.id.tvTimerOnCard);
        txtCardID = (TextView) findViewById(R.id.cardID);
        btnBlock1 = (Button) findViewById(R.id.btnBlock1);
        btnBlock2 = (Button) findViewById(R.id.btnBlock2);
        btnBlock3 = (Button) findViewById(R.id.btnBlock3);
        btnBlock4 = (Button) findViewById(R.id.btnBlock4);
        btnBlock5 = (Button) findViewById(R.id.btnBlock5);
        imgPointForBlock1  = (ImageView) findViewById(R.id.imgPointsBlock1);
        imgPointForBlock2  = (ImageView) findViewById(R.id.imgPointsBlock2);
        imgPointForBlock3  = (ImageView) findViewById(R.id.imgPointsBlock3);
        imgPointForBlock4  = (ImageView) findViewById(R.id.imgPointsBlock4);
        imgPointForBlock5  = (ImageView) findViewById(R.id.imgPointsBlock5);
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
                    points = imgPointForBlock1;

                    if ( playersCards.get(activeCardId).getPointForBlock1() == 0 ) {
                        points.setImageResource(R.drawable.points_block1_active);
                        playersCards.get(activeCardId).setPointForBlock1(POINTS_BLOCK1);

                    } else {
                        points.setImageResource(R.drawable.points_block1_inactive);
                        playersCards.get(activeCardId).setPointForBlock1(0);
                    }
                    break;

                case R.id.btnBlock2:
                   points = imgPointForBlock2;

                    if ( playersCards.get(activeCardId).getPointForBlock2() == 0 ) {
                        points.setImageResource(R.drawable.points_block2_active);
                        playersCards.get(activeCardId).setPointForBlock2(POINTS_BLOCK2);

                    } else {
                        points.setImageResource(R.drawable.points_block2_inactive);
                        playersCards.get(activeCardId).setPointForBlock2(0);
                    }
                    break;

                case R.id.btnBlock3:
                    points = imgPointForBlock3;
                    if ( playersCards.get(activeCardId).getPointForBlock3() == 0 ) {
                        points.setImageResource(R.drawable.points_block3_active);
                        playersCards.get(activeCardId).setPointForBlock3(POINTS_BLOCK3);

                    } else {
                        points.setImageResource(R.drawable.points_block3_inactive);
                        playersCards.get(activeCardId).setPointForBlock3(0);
                    }
                    break;

                case R.id.btnBlock4:
                    points = imgPointForBlock4;

                    if (playersCards.get(activeCardId).getPointForBlock4() == 0 ) {
                        points.setImageResource(R.drawable.points_block4_active);
                        playersCards.get(activeCardId).setPointForBlock4(POINTS_BLOCK4);

                    } else {
                        points.setImageResource(R.drawable.points_block4_inactive);
                        playersCards.get(activeCardId).setPointForBlock4(0);
                    }
                    break;

                case R.id.btnBlock5:
                    points = imgPointForBlock5;

                    if ( playersCards.get(activeCardId).getPointForBlock5() == 0 ) {
                        points.setImageResource(R.drawable.points_block5_active);
                        playersCards.get(activeCardId).setPointForBlock5(POINTS_BLOCK5);

                    } else {
                        points.setImageResource(R.drawable.points_block5_inactive);
                        playersCards.get(activeCardId).setPointForBlock5(0);
                    }
                    break;
            }
        }





    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChoosePlayerActivity.class);
        intent.putParcelableArrayListExtra(Utils.PLAYERS_CARDS, playersCards);
        intent.putExtra(Utils.PLAYER_ID, playerId.toString());
        intent.putExtra(Utils.ACTIVE_CARD_ID, activeCardId.toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void updateCardForPlayer() {
        Integer  cardIDForDisplay = activeCardId + 1;

        btnBlock1.setText(playersCards.get(activeCardId).getWordsList().get(0) + "\n" +
                playersCards.get(activeCardId).getWordsList().get(1) + "\n" +
                playersCards.get(activeCardId).getWordsList().get(2));
        btnBlock2.setText(playersCards.get(activeCardId).getWordsList().get(3) + "\n" +
                playersCards.get(activeCardId).getWordsList().get(4));
        btnBlock3.setText(playersCards.get(activeCardId).getWordsList().get(5));
        btnBlock4.setText(playersCards.get(activeCardId).getWordsList().get(6));
        btnBlock5.setText(playersCards.get(activeCardId).getWordsList().get(7));
        btnBlock1.setTextSize(getResources().getDimension(R.dimen.textSizeOnBlockButtonsOnActiveCard));
        btnBlock2.setTextSize(getResources().getDimension(R.dimen.textSizeOnBlockButtonsOnActiveCard));
        btnBlock3.setTextSize(getResources().getDimension(R.dimen.textSizeOnBlockButtonsOnActiveCard));
        btnBlock4.setTextSize(getResources().getDimension(R.dimen.textSizeOnBlockButtonsOnActiveCard));
        btnBlock5.setTextSize(getResources().getDimension(R.dimen.textSizeOnBlockButtonsOnActiveCard));


        txtPlayerName.setText(playerName);
        txtCardID.setText(cardIDForDisplay.toString());



        showTimer(playersCards.get(activeCardId).getTimer());


        if (playersCards.get(activeCardId).getPointForBlock1() > 0) {
           imgPointForBlock1.setImageResource(R.drawable.points_block1_active);

        }
        else { imgPointForBlock1.setImageResource(R.drawable.points_block1_inactive); }


        if (playersCards.get(activeCardId).getPointForBlock2() > 0) {
            imgPointForBlock2.setImageResource(R.drawable.points_block2_active);

        }
        else { imgPointForBlock2.setImageResource(R.drawable.points_block2_inactive); }

        if (playersCards.get(activeCardId).getPointForBlock3() > 0) {
            imgPointForBlock3.setImageResource(R.drawable.points_block3_active);

        }
        else { imgPointForBlock3.setImageResource(R.drawable.points_block3_inactive); }

        if (playersCards.get(activeCardId).getPointForBlock4() > 0) {
            imgPointForBlock4.setImageResource(R.drawable.points_block4_active);

        }
        else { imgPointForBlock4.setImageResource(R.drawable.points_block4_inactive); }

        if (playersCards.get(activeCardId).getPointForBlock5() > 0) {
            imgPointForBlock5.setImageResource(R.drawable.points_block5_active);

        }
        else { imgPointForBlock5.setImageResource(R.drawable.points_block5_inactive); }


    }

    public void showTimer(long timeInSecond) {
        //Create timer
        if(timer != null) {
            timer.cancel();
            timer = null;
            System.gc();
        }

        timer = new CountDownTimer(timeInSecond * 1000, 1000) {
            @Override
            public void onTick(long l) {
                //Log.d("MY LOG", String.valueOf(l / 1000));

                playersCards.get(activeCardId).setTimer(l/1000);

                myDate.setTime(l);
                //Log.d("MY LOG", "card: " + activeCardId + ". Timer: " + formatter.format(myDate));
                txtTimer.setText(formatter.format(myDate));


            }

            @Override
            public void onFinish() {
               // Log.d("MY LOG", "FINISH");
                playersCards.get(activeCardId).setTimer(0);

            }
        }.start();
    }

}
