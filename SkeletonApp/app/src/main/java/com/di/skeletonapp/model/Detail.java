package com.di.skeletonapp.model;

import java.io.Serializable;

public class Detail implements Serializable {

    String mLetter;
    String mNumber;

    public Detail(String letter, String number) {
        mLetter = letter;
        mNumber = number;
    }

    public String getLetter() {
        return mLetter;
    }

    public String getNumber() {
        return mNumber;
    }

    public String getNextLetter() {

        char c = mLetter.charAt(0);
        c++;
        return Character.toString(c);
    }

    public String getNextNumber() {

        Integer i = Integer.parseInt(mNumber);
        i++;
        return Integer.toString(i);
    }

}
