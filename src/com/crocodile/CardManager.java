package com.crocodile;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.android.crocodile.R;

import java.util.*;

/**
 * Created by v-ikomarov on 11/18/2014.
 */
public class CardManager {
    private final static  String MY_LOG = "MY_LOG";
    private static final String DB_EMPTY_STR = "База данных пустая" ;
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public CardManager(Context context) {
       this.context = context;
    }


   public List<List<Card>> divideCardsBetweenPlayers(List<Card> availableCards, int numberOfPlayers){
       List<List<Card>> dividedCards = new ArrayList<List<Card>>();
       int playerNumber = 0;



       for(int i = 0; i < numberOfPlayers; i++){
            dividedCards.add(i, new ArrayList<Card>());
       }


       Iterator iterator = availableCards.iterator();
      while(iterator.hasNext()) {
          if (playerNumber == numberOfPlayers ) {
              playerNumber = 0;
          }

          dividedCards.get(playerNumber).add((Card) iterator.next());
          iterator.remove();
          playerNumber++;
       }

       return dividedCards;

   }

    public List<Card> createCards() {
        List<Card> cards = new ArrayList<Card>();



//        cards.add(new Card("Дом", "Сад", "Окот", "кот", "лог", "пот", "дог", "рот"));
//        cards.add(new Card("Дом2", "Сад2", "Окот2", "кот2", "лог2", "пот2", "до2г", "ро2т"));


//        cards.add(new Card("home3", "car3", "cat3", "fog3", "sum3", "train3", "frog3", "swing3"));
//        cards.add(new Card("home4", "car4", "cat4", "fog4", "sum4", "train4", "frog4", "swing4"));
//        cards.add(new Card("home5", "car5", "cat5", "fog5", "sum5", "train5", "frog5", "swing5"));
//        cards.add(new Card("home6", "car6", "cat6", "fog6", "sum6", "train6", "frog6", "swing6"));
//        cards.add(new Card("home7", "car7", "cat7", "fog7", "sum7", "train7", "frog7", "swing7"));
//        cards.add(new Card("home8", "car8", "cat8", "fog8", "sum8", "train8", "frog8", "swing8"));


        return cards;
    }

//    public void saveCardsIntoDB(List<Card> cards ) {
//
//
//        dbHelper = new DBHelper(context);
//
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete(DBHelper.CARD_TABLE, null,null);
//
//        ContentValues cv;
//        Cursor c = db.rawQuery("SELECT * FROM " + DBHelper.CARD_TABLE, null);
//
//
//        for (Card card: cards) {
//            cv = setCV(card);
//
//            String currentCard  = card.getWord1().toLowerCase().trim() + card.getWord2().toLowerCase().trim() +card.getWord3().toLowerCase().trim() + card.getWord4().toLowerCase().trim() + card.getWord5().toLowerCase().trim() +                    card.getWord6().toLowerCase().trim() +                    card.getWord7().toLowerCase().trim() +                    card.getWord8().toLowerCase().trim();
//            if (c.moveToFirst()) {
//                do {
//                    String dbCard = c.getString(c.getColumnIndex(DBHelper.WORD1)).toLowerCase().trim() +                    c.getString(c.getColumnIndex(DBHelper.WORD2)).toLowerCase().trim() +                    c.getString(c.getColumnIndex(DBHelper.WORD3)).toLowerCase().trim() +                    c.getString(c.getColumnIndex(DBHelper.WORD4)).toLowerCase().trim() +                    c.getString(c.getColumnIndex(DBHelper.WORD5)).toLowerCase().trim() +                    c.getString(c.getColumnIndex(DBHelper.WORD6)).toLowerCase().trim() +                    c.getString(c.getColumnIndex(DBHelper.WORD7)).toLowerCase().trim() +                    c.getString(c.getColumnIndex(DBHelper.WORD8)).toLowerCase().trim();
//                    if (currentCard.equals(dbCard)) {
//                        break;
//                    }
//                }
//                while (c.moveToNext());
//
//                if(c.isAfterLast()) {
//
//                    db.insert(DBHelper.CARD_TABLE, null, cv);
//                }
//
//            }
//            else {
//                db.insert(DBHelper.CARD_TABLE, null, cv);
//            }
//
//        }
//
//
//        c = db.rawQuery("SELECT * FROM " + DBHelper.CARD_TABLE, null);
//        logCursor(c);
//
//    }
//
//    private ContentValues setCV(Card card) {
//        ContentValues cv  = new ContentValues();
//        cv.put(DBHelper.WORD1, card.getWord1());
//        cv.put(DBHelper.WORD2, card.getWord2());
//        cv.put(DBHelper.WORD3, card.getWord3());
//        cv.put(DBHelper.WORD4, card.getWord4());
//        cv.put(DBHelper.WORD5, card.getWord5());
//        cv.put(DBHelper.WORD6, card.getWord6());
//        cv.put(DBHelper.WORD7, card.getWord7());
//        cv.put(DBHelper.WORD8, card.getWord8());
//
//        return cv;
//    }

    private void logCursor(Cursor c) {
        if (c.moveToFirst()) {

            do {
                Log.d(MY_LOG, "ID_" + c.getString(c.getColumnIndex(DBHelper.ID)) + ": " +
                        c.getString(c.getColumnIndex(DBHelper.WORD1)) + " " +
                        c.getString(c.getColumnIndex(DBHelper.WORD1)) + " " +
                        c.getString(c.getColumnIndex(DBHelper.WORD2)) + " " +
                        c.getString(c.getColumnIndex(DBHelper.WORD3)) + " " +
                        c.getString(c.getColumnIndex(DBHelper.WORD4)) + " " +
                        c.getString(c.getColumnIndex(DBHelper.WORD5)) + " " +
                        c.getString(c.getColumnIndex(DBHelper.WORD6)) + " " +
                        c.getString(c.getColumnIndex(DBHelper.WORD7)) + " " +
                        c.getString(c.getColumnIndex(DBHelper.WORD8)) + " ");

            }
            while (c.moveToNext());

        }
        else {
            Log.d(MY_LOG, "----DB is empty------");
        }
    }

    public List<Card>  loadCardsFromXML(){
        List<Card> loadedCards = new ArrayList<Card>();
        Resources res = context.getResources();
        TypedArray ta = res.obtainTypedArray(R.array.cards);
        int n = ta.length();

        for (int i = 0; i < n; ++i) {
                loadedCards.add(i, getCardFromXML(i));

        }
        ta.recycle();
        return loadedCards;
    }

    private Card getCardFromXML(int i) {
        Resources res = context.getResources();
        TypedArray ta = res.obtainTypedArray(R.array.cards);
        String[] ar = res.getStringArray(ta.getResourceId(i, 0));

        return  new Card(Arrays.asList(ar), new int[] {0, 0, 0, 0, 0});
    }

//    public List<Card> loadCardsFromDB() {
//        List<Card> loadedCards = new ArrayList<Card>();
//        dbHelper = new DBHelper(context);
//        db = dbHelper.getReadableDatabase();
//        Cursor c;
//
//        c = db.rawQuery("SELECT * FROM " + DBHelper.CARD_TABLE, null);
//        int i = 0;
//        if(c.moveToFirst()){
//            do {
//
//                loadedCards.add(i, getCardFromDB(c));
//            } while (c.moveToNext());
//            Collections.shuffle(loadedCards);
//            return loadedCards;
//        }
//        else {
//            Toast.makeText(context, DB_EMPTY_STR, Toast.LENGTH_SHORT).show();
//            return null;
//        }
//    }

//    private Card getCardFromDB(Cursor c) {
//        Card card = new Card();
//        card.setId(Integer.parseInt(c.getString(c.getColumnIndex(DBHelper.ID))));
//        card.setWord1(c.getString(c.getColumnIndex(DBHelper.WORD1)));
//        card.setWord2(c.getString(c.getColumnIndex(DBHelper.WORD2)));
//        card.setWord3(c.getString(c.getColumnIndex(DBHelper.WORD3)));
//        card.setWord4(c.getString(c.getColumnIndex(DBHelper.WORD4)));
//        card.setWord5(c.getString(c.getColumnIndex(DBHelper.WORD5)));
//        card.setWord6(c.getString(c.getColumnIndex(DBHelper.WORD6)));
//        card.setWord7(c.getString(c.getColumnIndex(DBHelper.WORD7)));
//        card.setWord8(c.getString(c.getColumnIndex(DBHelper.WORD8)));
//
//
//        return card;
//    }


    public List<Integer> setActiveCardsForStart(Integer numberOfPlayers) {
        List<Integer> startCardsID = new ArrayList<Integer>();
        for(int i = 0; i < numberOfPlayers; i++) {
            startCardsID.add(i, 0);
        }
        return startCardsID;
    }

    public Integer getActiveCards(List<Integer> activeCardsID, Integer numberOfPlayer) {
        return activeCardsID.get(numberOfPlayer);
    }
}
