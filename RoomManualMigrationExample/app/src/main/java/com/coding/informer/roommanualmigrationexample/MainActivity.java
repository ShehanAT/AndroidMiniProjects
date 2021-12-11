package com.coding.informer.roommanualmigrationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.coding.informer.roommanualmigrationexample.database.AppDatabase;
import com.coding.informer.roommanualmigrationexample.models.Vehicle;


import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_1_2;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_2_3;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_3_4;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_4_5;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_5_6;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_6_7;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_7_8;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_8_9;


public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask.execute(() -> {
            Log.d(TAG, "Before running migrations");

            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "example_db")
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7, MIGRATION_7_8, MIGRATION_8_9).build();

            Vehicle newVehicle = new Vehicle();
//            newVehicle.setModel("BMW");
            newVehicle.setName("G42");

            db.getVehicleDAO().insertAll(newVehicle);
            Log.d(TAG, "After running migrations");
        });
    }
}