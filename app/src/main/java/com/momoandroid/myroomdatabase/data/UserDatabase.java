package com.momoandroid.myroomdatabase.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.momoandroid.myroomdatabase.models.User;

@Database(entities = User.class, version = 2)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public static UserDatabase instance;

    public static UserDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (UserDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "user_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
