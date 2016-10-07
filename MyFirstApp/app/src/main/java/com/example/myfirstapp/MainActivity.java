package com.example.myfirstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public final static String USER_NAME = "testuser";
    private final static String PASSWORD = "test123";
    private AlertDialog alertDialog;
    private final static String EMPTY_STR = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
       // alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }
        );

    }
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        final StringBuilder message = new StringBuilder();
        boolean validUser = true;
        Intent intent = new Intent(this, WelcomeUserActivity.class);
        final EditText userName = (EditText) findViewById(R.id.email);

       /* userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(charSequence.length() < 8) {
                    alertDialog.setMessage("Minimum allowed characters 8");
                    alertDialog.show();
                    userName.setText(emptyStr);
                    return;
                }
                if (charSequence.length() > 12) {
                    alertDialog.setMessage("Maximum characters allowed 12");
                    alertDialog.show();
                    userName.setText(emptyStr);
                    return;
                }
                for (int index = 0; index < charSequence.length(); index++) {
                    if(!Character.isLetter(charSequence.charAt(index))) {
                        alertDialog.setMessage("Username accepts A-Z, a-z only, no numerals, no special characters");
                        alertDialog.show();
                        userName.setText(emptyStr);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
        String userId = userName.getText().toString();
        EditText pass = (EditText) findViewById(R.id.password);

        if (!validateUser(userId, userName, pass)) {
            return;
        }
        message.append(R.string.welcome + userId + R.string.goodToGo);
       /* if (!PASSWORD.equals(pass.getText().toString())) {
            if (!validUser) {
                message.append("invalid entry in Username");
            } else {
                message.append("Password is not correct.");
            }
        } else {
            if(validUser) {
                message.append("Welcome " + userId);
            }
        }*/
        intent.putExtra(EXTRA_MESSAGE, message.toString());
        startActivity(intent);
    }
    private boolean validateUser(String userId, EditText user, EditText pass) {
        boolean validUser = true;
        if (!userId.equals(USER_NAME)) {
            alertDialog.setMessage("invalid entry in Username");
            alertDialog.show();
            user.setText(EMPTY_STR);
            validUser = false;
        }
        if(userId.length() < 8) {
            alertDialog.setMessage("Minimum allowed characters 8");
            alertDialog.show();
            user.setText(EMPTY_STR);
            validUser = false;
        }
        if (userId.length() > 12) {
            alertDialog.setMessage("Maximum characters allowed 12");
            alertDialog.show();
            user.setText(EMPTY_STR);
            validUser = false;
        }
        for (int index = 0; index < userId.length(); index++) {
            if(!Character.isLetter(userId.charAt(index))) {
                alertDialog.setMessage("Username accepts A-Z, a-z only, no numerals, no special characters");
                alertDialog.show();
                user.setText(EMPTY_STR);
                validUser = false;
                break;
            }
        }
        if (!PASSWORD.equals(pass.getText().toString())) {
            alertDialog.setMessage("Password is incorrect");
            alertDialog.show();
            pass.setText(EMPTY_STR);
            validUser = false;
        }
        return validUser;
    }
}
