package com.momoandroid.myroomdatabase.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.momoandroid.myroomdatabase.models.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserDao {
    @Insert
    Completable insertData(User user);

    @Update
    Completable updateData(User user);

    @Query("delete from user_table")
    Completable deleteData();

    @Query("select * from user_table")
    Single<List<User>> getAllDataUser();
}
