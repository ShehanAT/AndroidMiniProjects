package com.coding.informer.roommanualmigrationexample.database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RenameTable;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

import com.coding.informer.roommanualmigrationexample.dao.VehicleDAO;
import com.coding.informer.roommanualmigrationexample.models.Vehicle;

@Database(version=1, entities={Vehicle.class})
public abstract class AppDatabase extends RoomDatabase{
    public abstract VehicleDAO getVehicleDAO();

    public static final String NAME = "example_db";

}