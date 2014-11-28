package com.android.crocodile;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by v-ikomarov on 11/18/2014.
 */
public class Card implements Parcelable {
    private String word1;
    private String word2;
    private String word3;
    private String word4;
    private String word5;
    private String word6;
    private String word7;
    private String word8;
    private int pointForBlock1;
    private int pointForBlock2;
    private int pointForBlock3;
    private int pointForBlock4;
    private int pointForBlock5;

    private int id;

    public Card() {

    }

    public Card(String word1, String word2, String word3, String word4, String word5, String word6, String word7, String word8, int pointForBlock1, int pointForBlock2, int pointForBlock3, int pointForBlock4, int pointForBlock5) {
        this.word1 = word1;
        this.word2 = word2;
        this.word3 = word3;
        this.word4 = word4;
        this.word5 = word5;
        this.word6 = word6;
        this.word7 = word7;
        this.word8 = word8;
        this.pointForBlock1 = pointForBlock1;
        this.pointForBlock2 = pointForBlock2;
        this.pointForBlock3 = pointForBlock3;
        this.pointForBlock4 = pointForBlock4;
        this.pointForBlock5 = pointForBlock5;
    }


    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public String getWord3() {
        return word3;
    }

    public void setWord3(String word3) {
        this.word3 = word3;
    }

    public String getWord4() {
        return word4;
    }

    public void setWord4(String word4) {
        this.word4 = word4;
    }

    public String getWord5() {
        return word5;
    }

    public void setWord5(String word5) {
        this.word5 = word5;
    }

    public String getWord6() {
        return word6;
    }

    public void setWord6(String word6) {
        this.word6 = word6;
    }

    public String getWord7() {
        return word7;
    }

    public void setWord7(String word7) {
        this.word7 = word7;
    }

    public String getWord8() {
        return word8;
    }

    public void setWord8(String word8) {
        this.word8 = word8;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flag) {
       parcel.writeStringArray(new String[] {getWord1(), getWord2(), getWord3(), getWord4(), getWord5(), getWord6(), getWord7(), getWord8()});
        parcel.writeIntArray(new int[] {getPointForBlock1(), getPointForBlock2(), getPointForBlock3(), getPointForBlock4(), getPointForBlock5()});


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
        String[] data = new String[8];
        parcel.readStringArray(data);
        setWord1(data[0]);
        setWord2(data[1]);
        setWord3(data[2]);
        setWord4(data[3]);
        setWord5(data[4]);
        setWord6(data[5]);
        setWord7(data[6]);
        setWord8(data[7]);

        int[] dataint = new int[5];
        parcel.readIntArray(dataint);
        setPointForBlock1(dataint[0]);
        setPointForBlock2(dataint[1]);
        setPointForBlock3(dataint[2]);
        setPointForBlock4(dataint[3]);
        setPointForBlock5(dataint[4]);

    }
}
