package com.donhat.contactsmanagerapp.clickhandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.donhat.contactsmanagerapp.activities.AddContactActivity;

public class MainActivityClickHandlers {
    Context _context;

    public MainActivityClickHandlers(Context context) {
        _context = context;
    }

    public void onFABClicked(View view) {
        Intent intent = new Intent(_context.getApplicationContext(), AddContactActivity.class);
        _context.startActivity(intent);
    }
}
