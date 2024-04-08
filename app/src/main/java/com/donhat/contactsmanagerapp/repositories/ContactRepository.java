package com.donhat.contactsmanagerapp.repositories;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.donhat.contactsmanagerapp.DAOs.ContactDAO;
import com.donhat.contactsmanagerapp.databases.ContactDatabase;
import com.donhat.contactsmanagerapp.entities.Contact;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {
    private final ContactDAO _contactDAO;
    private final ExecutorService _executor;
    private final Handler _handler;

    public ContactRepository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        _contactDAO = contactDatabase.getContactDAO();

        // Used for background database operations
        _executor = Executors.newSingleThreadExecutor();

        // Used for updating the UI
        _handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contact contact) {
        _executor.execute(() -> _contactDAO.insert(contact));
    }

    public void deleteContact(Contact contact) {
        _executor.execute(() -> _contactDAO.delete(contact));
    }

    public LiveData<List<Contact>> getAllContacts() {
        return _contactDAO.getAllContacts();
    }
}
