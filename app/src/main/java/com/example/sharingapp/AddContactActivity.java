package com.example.sharingapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Add a new contact
 */
public class AddContactActivity extends AppCompatActivity {

    private ContactList contact_list = new ContactList();
    private Context context;

    private EditText username;
    private EditText email;

    private String username_str;
    private String email_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        context = getApplicationContext();
        contact_list.loadContacts(context);
    }

    public void saveContact(View view) {
        username_str = username.getText().toString();
        email_str = email.getText().toString();

        if (!validateInput()) {
            return;
        }

        Contact contact = new Contact(username_str, email_str, null);

        // Add contact
        AddContactCommand addContactCommand = new AddContactCommand(contact_list, contact, context);
        addContactCommand.execute();

        boolean success = addContactCommand.getIsExecuted();
        if (!success){
            return;
        }

        // End AddContactActivity
        finish();
    }

    public boolean validateInput() {
        if (username_str.equals("")) {
            username.setError("Empty field!");
            return false;
        }

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return false;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            return false;
        }

        if (!contact_list.isUsernameAvailable(username_str)){
            username.setError("Username already taken!");
            return false;
        }

        return true;
    }
}