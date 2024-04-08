package com.donhat.contactsmanagerapp.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.donhat.contactsmanagerapp.R;
import com.donhat.contactsmanagerapp.adapters.ContactAdapter;
import com.donhat.contactsmanagerapp.clickhandlers.MainActivityClickHandlers;
import com.donhat.contactsmanagerapp.databases.ContactDatabase;
import com.donhat.contactsmanagerapp.databinding.ActivityMainBinding;
import com.donhat.contactsmanagerapp.entities.Contact;
import com.donhat.contactsmanagerapp.viewmodels.MyViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Data Source
    private ContactDatabase _contactDatabase;
    private ArrayList<Contact> _contacts = new ArrayList<>();

    // Adapter
    private ContactAdapter _contactAdapter;

    // Binding
    private ActivityMainBinding _activityMainBinding;
    private MainActivityClickHandlers _mainActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Data Binding
        _activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        _mainActivityClickHandlers = new MainActivityClickHandlers(this);

        _activityMainBinding.setClickHandler(_mainActivityClickHandlers);

        // RecyclerView
        RecyclerView contactsRecyclerView = _activityMainBinding.contactsRecyclerView;
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsRecyclerView.setHasFixedSize(true);

        // Database
        _contactDatabase = ContactDatabase.getInstance(this);

        // ViewModel
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // Loading the data from Room DB
        viewModel.getAllContacts().observe(this, contacts -> {
            _contacts.clear();
            for (Contact c : contacts) {
                Log.v("TAGY", c.getName());
                _contacts.add(c);
            }

            _contactAdapter.notifyDataSetChanged();
        });

        // Adapter
        _contactAdapter = new ContactAdapter(_contacts);

        // Linking the Recycler View with the adapter.
        contactsRecyclerView.setAdapter(_contactAdapter);

        // Swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact contact = _contacts.get(viewHolder.getAdapterPosition());
                viewModel.deleteContact(contact);
            }
        }).attachToRecyclerView(contactsRecyclerView);
    }
}