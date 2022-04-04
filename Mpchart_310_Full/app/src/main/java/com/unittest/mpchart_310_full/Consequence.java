package com.unittest.mpchart_310_full;


import android.os.Parcel;
import android.os.Parcelable;

public class Consequence implements Parcelable {
    private String date;
    private Long score;

    public Consequence(String date, Long score) {
        this.date = date;
        this.score = score;
    }

    protected Consequence(Parcel in) {
        date = in.readString();
        if (in.readByte() == 0) {
            score = null;
        } else {
            score = in.readLong();
        }
    }

    public static final Creator<Consequence> CREATOR = new Creator<Consequence>() {
        @Override
        public Consequence createFromParcel(Parcel in) {
            return new Consequence(in);
        }

        @Override
        public Consequence[] newArray(int size) {
            return new Consequence[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        if (score == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(score);
        }
    }
}