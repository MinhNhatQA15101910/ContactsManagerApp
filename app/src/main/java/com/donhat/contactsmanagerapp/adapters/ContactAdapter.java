package com.donhat.contactsmanagerapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.donhat.contactsmanagerapp.R;
import com.donhat.contactsmanagerapp.databinding.LayoutContactItemBinding;
import com.donhat.contactsmanagerapp.entities.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contact> _contacts;

    public ContactAdapter(ArrayList<Contact> contacts) {
        _contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutContactItemBinding layoutContactItemBinding
                = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_contact_item,
                parent,
                false
        );

        return new ContactViewHolder(layoutContactItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact currentContact = _contacts.get(position);

        holder.layoutContactItemBinding.setContact(currentContact);
    }

    @Override
    public int getItemCount() {
        if (_contacts != null) {
            return _contacts.size();
        }

        return 0;
    }

    public void set_contacts(ArrayList<Contact> _contacts) {
        this._contacts = _contacts;

        notifyDataSetChanged();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        private final LayoutContactItemBinding layoutContactItemBinding;

        public ContactViewHolder(LayoutContactItemBinding layoutContactItemBinding) {
            super(layoutContactItemBinding.getRoot());
            this.layoutContactItemBinding = layoutContactItemBinding;
        }
    }
}
