package com.coding.informer.androidloginregistrationexample.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.coding.informer.androidloginregistrationexample.daos.UserDAO;
import com.coding.informer.androidloginregistrationexample.models.User;

@Database(entities={User.class}, version=1)
abstract public class AppDatabase extends RoomDatabase {
    public abstract UserDAO getUserDAO();


}
