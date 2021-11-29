package com.coding.informer.androidloginregistrationexample.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.coding.informer.androidloginregistrationexample.R;
import com.coding.informer.androidloginregistrationexample.database.AppDatabase;
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory;

import static com.coding.informer.androidloginregistrationexample.database.migrations.Migrations.MIGRATION_1_2;


public class DatabaseHelper extends SQLiteOpenHelper {

    private Cursor cursor = null;
    private static String DATABASE_NAME = "example_db";
    private static String DATABASE_VERSION = "1";

    private AppDatabase INSTANCE = null;

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, Integer.parseInt(DATABASE_VERSION));

    }

    public AppDatabase getDatabaseInstance(Context context){
        return getDatabase(context);
    }

    public AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = createDatabase(context);
        }
        return INSTANCE;
    }

    public static AppDatabase createDatabase(Context context){
        RoomDatabase.Builder<AppDatabase> builder =
                Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                        context.getString(R.string.database_name));

        return (builder.openHelperFactory(new AssetSQLiteOpenHelperFactory())
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2).build());

    }
}
