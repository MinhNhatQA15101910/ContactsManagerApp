package com.donhat.contactsmanagerapp.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.donhat.contactsmanagerapp.R;
import com.donhat.contactsmanagerapp.clickhandlers.AddContactActivityClickHandlers;
import com.donhat.contactsmanagerapp.databinding.ActivityAddContactBinding;
import com.donhat.contactsmanagerapp.entities.Contact;
import com.donhat.contactsmanagerapp.viewmodels.MyViewModel;

public class AddContactActivity extends AppCompatActivity {
    private ActivityAddContactBinding _activityAddContactBinding;
    private AddContactActivityClickHandlers _addContactActivityClickHandlers;
    private Contact _contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        _activityAddContactBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact);

        _contact = new Contact();
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        _addContactActivityClickHandlers = new AddContactActivityClickHandlers(_contact, this, viewModel);

        _activityAddContactBinding.setContact(_contact);
        _activityAddContactBinding.setClickHandler(_addContactActivityClickHandlers);
    }
}