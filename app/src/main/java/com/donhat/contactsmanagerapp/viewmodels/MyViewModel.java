package com.donhat.contactsmanagerapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.donhat.contactsmanagerapp.entities.Contact;
import com.donhat.contactsmanagerapp.repositories.ContactRepository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private final ContactRepository _contactRepository;

    private LiveData<List<Contact>> _allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        _contactRepository = new ContactRepository(application);
    }

    public LiveData<List<Contact>> getAllContacts() {
        _allContacts = _contactRepository.getAllContacts();
        return _allContacts;
    }

    public void addContact(Contact contact) {
        _contactRepository.addContact(contact);
    }

    public void deleteContact(Contact contact) {
        _contactRepository.deleteContact(contact);
    }
}
