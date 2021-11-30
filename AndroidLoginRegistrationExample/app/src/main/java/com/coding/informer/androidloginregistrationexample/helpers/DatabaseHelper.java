package com.coding.informer.androidloginregistrationexample.helpers;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.coding.informer.androidloginregistrationexample.R;
import com.coding.informer.androidloginregistrationexample.database.AppDatabase;
import com.coding.informer.androidloginregistrationexample.models.User;
import com.coding.informer.androidloginregistrationexample.models.UserFieldType;
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory;

import static android.provider.Telephony.Carriers.PASSWORD;
import static com.coding.informer.androidloginregistrationexample.database.migrations.Migrations.MIGRATION_1_2;
import static com.coding.informer.androidloginregistrationexample.models.UserFieldType.PASSWORD_SALT;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static AppDatabase INSTANCE;
    private Cursor cursor = null;
    private static String DATABASE_NAME = "example_db";
    private static String DATABASE_VERSION = "1";

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

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = createDatabase(context);
        }
        return INSTANCE;
    }

    public static AppDatabase createDatabase(Context context){
        AsyncTask.execute(() -> {
            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "example_db")
                    .addMigrations(MIGRATION_1_2).build();

//            return db;
        });


        RoomDatabase.Builder<AppDatabase> builder =
                Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                        context.getString(R.string.database_name));

        return (builder.openHelperFactory(new AssetSQLiteOpenHelperFactory())
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2).build());

    }

    public static String getUserFieldSQLQuery(Context context, UserFieldType userFieldType, String inputUsername) {
        AppDatabase appDatabase = getDatabase(context);
        User foundUser = appDatabase.getUserDAO().findByUsername(inputUsername);
        if(foundUser != null) {
            switch (userFieldType) {
                case PASSWORD:
                    Toast.makeText(context, foundUser.toString(), Toast.LENGTH_LONG);
                    return foundUser.getPassword();
                case PASSWORD_SALT:
                    Toast.makeText(context, foundUser.toString(), Toast.LENGTH_LONG);
                    return foundUser.getPasswordSalt();
            }
        }
        Toast.makeText(
                context,
                "An user with that username was not found in the database!",
                Toast.LENGTH_LONG);
        return null;
    }

}
