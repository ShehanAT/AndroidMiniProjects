package com.coding.informer.androidloginregistrationexample.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.coding.informer.androidloginregistrationexample.R;
import com.coding.informer.androidloginregistrationexample.daos.UserDAO;
import com.coding.informer.androidloginregistrationexample.models.User;
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory;

import static com.coding.informer.androidloginregistrationexample.database.migrations.Migrations.MIGRATION_1_2;

@Database(entities={User.class}, version=1)
abstract public class AppDatabase extends RoomDatabase {
    public abstract UserDAO getUserDAO();

    private RoomDatabase createDatabase(Context context) {
        Builder builder = Room.databaseBuilder(
                context, AppDatabase.class,
                context.getString(R.string.database_name)
            );
        return builder.openHelperFactory(new AssetSQLiteOpenHelperFactory())
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2).build();
    }
}
