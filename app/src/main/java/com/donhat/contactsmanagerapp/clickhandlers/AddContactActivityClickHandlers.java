package com.donhat.contactsmanagerapp.clickhandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.donhat.contactsmanagerapp.activities.MainActivity;
import com.donhat.contactsmanagerapp.entities.Contact;
import com.donhat.contactsmanagerapp.viewmodels.MyViewModel;

public class AddContactActivityClickHandlers {
    private final Contact _contact;
    private final Context _context;
    private final MyViewModel _myViewModel;

    public AddContactActivityClickHandlers(Contact contact, Context context, MyViewModel myViewModel) {
        _contact = contact;
        _context = context;
        _myViewModel = myViewModel;
    }

    public void onSubmitBtnClicked(View view) {
        if (_contact.getName() == null ||
                _contact.getName().trim().isEmpty() ||
                _contact.getEmail() == null ||
                _contact.getEmail().trim().isEmpty()) {
            Toast.makeText(_context, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        } else {
            // Add new contact
            Contact c = new Contact(_contact.getName().trim(), _contact.getEmail().trim());
            _myViewModel.addContact(c);

            // Navigate back
            Intent intent = new Intent(_context, MainActivity.class);
            _context.startActivity(intent);
        }
    }
}
