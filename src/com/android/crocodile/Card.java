package com.android.crocodile;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;

/**
 * Created by v-ikomarov on 11/18/2014.
 */
public class Card implements Parcelable {
    private static final long TIMER = 600; // 10 min

    private long timer = TIMER;

    List<String> wordsList;
    int[] pointsForBlocks;



    private int id;

    public Card() {

    }

    public Card(List<String> list) {
        this.wordsList = new ArrayList<String>(list);
    }

    public Card(List<String> wordsList, int[] pointsForBlocks) {
        this.wordsList = wordsList;
        this.pointsForBlocks = pointsForBlocks;

    }

    public Card(List<String> wordsList, int[] pointsForBlocks, long timer) {

        this(wordsList, pointsForBlocks);
        this.timer = timer;

    }

    public List<String> getWordsList() {
        return wordsList;
    }


    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }

    public int[] getPointsForBlocks() {
        return pointsForBlocks;
    }

    public void setPointsForBlocks(int[] pointsForBlocks) {
        this.pointsForBlocks = pointsForBlocks;
    }

    public int getPointsForBlock(int numberOfBlock) {
        return pointsForBlocks[numberOfBlock - 1];
    }

    public void setPointsForBlock(int blockNumber, int points) {
        this.pointsForBlocks[blockNumber - 1] = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeInt(getWordsList().size());
        parcel.writeStringArray((String[]) getWordsList().toArray());


        parcel.writeInt(getPointsForBlocks().length);
        parcel.writeIntArray(getPointsForBlocks());

       parcel.writeLong(getTimer());


    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        // распаковываем объект из Parcel
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private Card(Parcel parcel) {

        String[] data = new String[parcel.readInt()];
        parcel.readStringArray(data);
        setWordsList(Arrays.asList(data));

        int[] points = new int[parcel.readInt()];
        parcel.readIntArray(points);
        setPointsForBlocks(points);




//
//        int[] dataint = new int[5];
//        parcel.readIntArray(dataint);
//        setPointForBlock1(dataint[0]);
//        setPointForBlock2(dataint[1]);
//        setPointForBlock3(dataint[2]);
//        setPointForBlock4(dataint[3]);
//        setPointForBlock5(dataint[4]);

        setTimer(parcel.readLong());

    }
}
