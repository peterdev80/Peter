package com.example.peter.database;

/**
 * Created by peter on 24.03.2017.
 */

public class CrimeDbSchema {
    public static final class CrimeTable {
        public static final String NAME = "crimes";

    public static final class Cols {
        public static final String UUID = "uuid";
        public static final String TITLE = "title";
        public static final String DATE = "date";
        public static final String SOLVED = "solved";
        public static final String SUSPECT = "suspect";
    }
}
}
