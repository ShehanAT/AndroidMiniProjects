package com.coding.informer.roommanualmigrationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.coding.informer.roommanualmigrationexample.dao.VehicleDAO;
import com.coding.informer.roommanualmigrationexample.database.AppDatabase;
import com.coding.informer.roommanualmigrationexample.models.Vehicle;

import java.util.List;

import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_1_2;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_2_3;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_3_4;
//import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_2_3;
//import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_3_4;
//import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_4_5;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask.execute(() -> {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "example_db")
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4).build();
        });
    }
}