package com.example.peter.criminalintent;

import java.util.UUID;

/**
 * Created by peter on 03.02.2017.
 * класс Crime и уровня модели CriminalIntent
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    public Crime() {
// Генерирование уникального идентификатора
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
