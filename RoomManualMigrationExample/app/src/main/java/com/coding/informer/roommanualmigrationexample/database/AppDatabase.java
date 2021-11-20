package com.coding.informer.roommanualmigrationexample.database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RenameTable;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

import com.coding.informer.roommanualmigrationexample.models.Fruit;

@Database(version = 2, entities = {Fruit.class})
public abstract class AppDatabase extends RoomDatabase {



}