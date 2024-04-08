package com.donhat.contactsmanagerapp.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.donhat.contactsmanagerapp.DAOs.ContactDAO;
import com.donhat.contactsmanagerapp.entities.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();

    // Singleton Pattern
    private static ContactDatabase dbInstance;

    public static synchronized ContactDatabase getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ContactDatabase.class,
                            "contacts_db"
                    ).fallbackToDestructiveMigration()
                    .build();
        }

        return dbInstance;
    }
}
