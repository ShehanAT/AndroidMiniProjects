package com.coding.informer.androidloginregistrationexample.database.migrations;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `user` (`user_id` INTEGER NOT NULL, "
                    + "`username` TEXT NOT NULL, `email` TEXT NOT NULL, " +
                    "`first_name` TEXT NOT NULL, `last_name` TEXT NOT NULL," +
                    "`password` TEXT NOT NULL, `password_salt` TEXT NOT NULL, PRIMARY KEY(`user_id`))");
        }
    };
}
