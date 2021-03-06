package com.coding.informer.androidloginregistrationexample.daos;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.coding.informer.androidloginregistrationexample.models.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> all();

    @Query("SELECT * FROM user WHERE user_id in (:findUserId)")
    User findById(int findUserId);

    @Query("SELECT * FROM user WHERE username in (:username)")
    User findByUsername(String username);

    @Insert
    void insertAll(User... users);

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);
}