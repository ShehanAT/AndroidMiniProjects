package com.coding.informer.roommanualmigrationexample.database;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RenameTable;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.coding.informer.roommanualmigrationexample.R;
import com.coding.informer.roommanualmigrationexample.dao.VehicleDAO;
import com.coding.informer.roommanualmigrationexample.models.Vehicle;
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory;

import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_1_2;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_2_3;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_3_4;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_4_5;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_5_6;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_6_7;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_7_8;
import static com.coding.informer.roommanualmigrationexample.database.migrations.Migration1.MIGRATION_8_9;


@Database(version=9, entities={Vehicle.class})
public abstract class AppDatabase extends RoomDatabase{
    public abstract VehicleDAO getVehicleDAO();

    private static String NAME = "example_db";

    public static AppDatabase createDatabase(Context context){
        RoomDatabase.Builder<AppDatabase> builder =
                Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                        context.getString(R.string.database_name));

        return (builder.openHelperFactory(new AssetSQLiteOpenHelperFactory())
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7, MIGRATION_7_8, MIGRATION_8_9).build());

    }

}