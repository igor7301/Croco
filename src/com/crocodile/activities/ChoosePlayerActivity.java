package com.crocodile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.crocodile.Card;
import com.crocodile.CardManager;
import com.android.crocodile.R;
import com.crocodile.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v-ikomarov on 11/19/2014.
 */
public class ChoosePlayerActivity extends Activity implements View.OnClickListener{
    private Integer numberOfPlayers;
    private List<Button> lstButtons;
    private List<Card> lstActiveCards;
    private List<Card> lstAvailableCards;
    private List<List<Card>> lstDividedCards;
    private List<Integer> activeCardsID;
    private List<String> nameOfPlayers;
    LinearLayout llChoosePlayersActivity;

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public List<Button> getLstButtons() {
        return lstButtons;
    }

    public void setLstButtons(List<Button> lstButtons) {
        this.lstButtons = lstButtons;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_player);
        llChoosePlayersActivity = (LinearLayout) findViewById(R.id.ll_choose_players);

        setNumberOfPlayers(Integer.parseInt(getIntent().getStringExtra(Utils.NUMBER_OF_PLAYERS)));




        CardManager cardManager  = new CardManager(this);
        //cardManager.saveCardsIntoDB(cardManager.createCards());



        //lstAvailableCards = cardManager.loadCardsFromDB();
        lstAvailableCards = cardManager.loadCardsFromXML();
        lstDividedCards = cardManager.divideCardsBetweenPlayers(lstAvailableCards, numberOfPlayers);
        activeCardsID = cardManager.setActiveCardsForStart(numberOfPlayers);

        nameOfPlayers = new ArrayList<String>();
        setLstButtons(new ArrayList<Button>());
        for(int i = 0; i < getNumberOfPlayers(); i++) {

            Button btn = new Button(this);
            nameOfPlayers.add(i,getIntent().getStringArrayListExtra(Utils.NAME_OF_PLAYERS).get(i) );
            btn.setText(nameOfPlayers.get(i) + " " + getResources().getString(R.string.points) + getPointsForPlayer(i));
            btn.setId(i);
            btn.setOnClickListener(this);
            getLstButtons().add(i, btn);
            llChoosePlayersActivity.addView(btn);
        }

        lstAvailableCards = null;
        System.gc(); //delete available cards. we will not use them anymore

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(RESULT_OK == resultCode) {
            switch (requestCode ) {
                case  Utils.REQUEST_CODE_ACTIVE_CARD:
                    ArrayList<Card> lstUpdatedCards = data.getParcelableArrayListExtra(Utils.PLAYERS_CARDS);
                    lstDividedCards
                            .set(Integer.parseInt(data.getStringExtra(Utils.PLAYER_ID)), lstUpdatedCards);

                    activeCardsID.set(Integer.parseInt(data.getStringExtra(Utils.PLAYER_ID)),
                            Integer.parseInt(data.getStringExtra(Utils.ACTIVE_CARD_ID)));
                    for(int i = 0; i < getNumberOfPlayers(); i++) {

                        getLstButtons().get(i)
                                .setText(nameOfPlayers.get(i) + " " +
                                        getResources().getString(R.string.points) + getPointsForPlayer(i));

                    }
                    break;
            }
        }
        else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }

    public int getPointsForPlayer(int playerId){
        List<Card> playersCard = lstDividedCards.get(playerId);
        int points = 0;
        for(int i = 0; i < playersCard.size(); i++){
           points = points +
                   playersCard.get(i).getPointsForBlock(1) +
                   playersCard.get(i).getPointsForBlock(2) +
                   playersCard.get(i).getPointsForBlock(3) +
                   playersCard.get(i).getPointsForBlock(4) +
                   playersCard.get(i).getPointsForBlock(5);
        }
        return points;
    }

    @Override
    public void onClick(View view) {
        Integer playerId;

        for (playerId = 0; playerId < getNumberOfPlayers(); playerId++) {

            if (getLstButtons().get(playerId).getId() == view.getId()) {

                Intent intent = new Intent(this, ActiveCardActivity.class);

                intent.putParcelableArrayListExtra(Utils.PLAYERS_CARDS, (ArrayList<Card>) lstDividedCards.get(playerId));
                intent.putExtra(Utils.PLAYER_ID, playerId.toString());
                intent.putExtra(Utils.ACTIVE_CARD_ID, activeCardsID.get(playerId).toString());
                intent.putExtra(Utils.NAME_OF_PLAYERS, nameOfPlayers.get(playerId));

                startActivityForResult(intent, Utils.REQUEST_CODE_ACTIVE_CARD);

            }

        }
    }
}
