package com.android.crocodile;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by v-ikomarov on 11/18/2014.
 */
public class Card implements Parcelable {
    private static final long TIMER = 600; // 10 min


    private int pointForBlock1;
    private int pointForBlock2;
    private int pointForBlock3;
    private int pointForBlock4;
    private int pointForBlock5;
    private long timer = TIMER;

    List<String> wordsList;



    private int id;

    public Card() {

    }

    public Card(List<String> list) {
        this.wordsList = new ArrayList<String>(list);
    }

    public Card(List<String> wordsList, int pointForBlock1, int pointForBlock2, int pointForBlock3, int pointForBlock4, int pointForBlock5) {
        this.wordsList = wordsList;
        this.pointForBlock1 = pointForBlock1;
        this.pointForBlock2 = pointForBlock2;
        this.pointForBlock3 = pointForBlock3;
        this.pointForBlock4 = pointForBlock4;
        this.pointForBlock5 = pointForBlock5;

    }

    public Card(List<String> wordsList, int pointForBlock1, int pointForBlock2, int pointForBlock3, int pointForBlock4, int pointForBlock5, long timer) {

        this(wordsList, pointForBlock1, pointForBlock2, pointForBlock3, pointForBlock4, pointForBlock5);
        this.timer = timer;

    }



    public List<String> getWordsList() {
        return wordsList;
    }


    public void setWordsList(List<String> wordsList) {
        this.wordsList = wordsList;
    }



    public int getPointForBlock1() {
        return pointForBlock1;
    }

    public void setPointForBlock1(int pointForBlock1) {
        this.pointForBlock1 = pointForBlock1;
    }

    public int getPointForBlock2() {
        return pointForBlock2;
    }

    public void setPointForBlock2(int pointForBlock2) {
        this.pointForBlock2 = pointForBlock2;
    }

    public int getPointForBlock3() {
        return pointForBlock3;
    }

    public void setPointForBlock3(int pointForBlock3) {
        this.pointForBlock3 = pointForBlock3;
    }

    public int getPointForBlock4() {
        return pointForBlock4;
    }

    public void setPointForBlock4(int pointForBlock4) {
        this.pointForBlock4 = pointForBlock4;
    }

    public int getPointForBlock5() {
        return pointForBlock5;
    }

    public void setPointForBlock5(int pointForBlock5) {
        this.pointForBlock5 = pointForBlock5;
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
       parcel.writeIntArray(new int[] {getPointForBlock1(), getPointForBlock2(), getPointForBlock3(), getPointForBlock4(), getPointForBlock5()});
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


        int[] dataint = new int[5];
        parcel.readIntArray(dataint);
        setPointForBlock1(dataint[0]);
        setPointForBlock2(dataint[1]);
        setPointForBlock3(dataint[2]);
        setPointForBlock4(dataint[3]);
        setPointForBlock5(dataint[4]);

        setTimer(parcel.readLong());

    }
}
