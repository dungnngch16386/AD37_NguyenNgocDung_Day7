package com.example.ad37_nguyenngocdung_day7;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    List<Contact> contacts;
    IonClickContact ionClickContact;
    SharedPreferences sharedPreferences;
    Context context;

    public void setIonClickContact(IonClickContact ionClickContact) {
        this.ionClickContact = ionClickContact;
    }

    public ContactAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, final int position) {
        final Contact contact = contacts.get(position);

        if (contact.getName().equals("")) {
            holder.tvPhone.setText(contact.getPhone());
        } else if (!contact.getName().equals("")) {
            holder.tvPhone.setText(contact.getName());
        }

        holder.tvCountry.setText(contact.getCountry());

        // Tao short menu
        boolean fullMode = sharedPreferences.getBoolean("full_mode", true);

        if (fullMode) {
            holder.tvCountry.setVisibility(View.VISIBLE);
        } else {
            holder.tvCountry.setVisibility(View.GONE);
        }

        holder.tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickContact.onClickPhone(contact.getName(), position);
            }
        });

        holder.imgMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickContact.onClickInformation();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPhone, tvCountry;
        ImageView imgMoreInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPhone = itemView.findViewById(R.id.tvPhone);
            imgMoreInfo = itemView.findViewById(R.id.imgMoreInfo);
            tvCountry = itemView.findViewById(R.id.tvCountry);
        }
    }
}
