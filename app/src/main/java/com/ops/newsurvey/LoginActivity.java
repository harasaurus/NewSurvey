package com.ops.newsurvey;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.id;
import static android.R.attr.switchMinWidth;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class LoginActivity extends AppCompatActivity {
    User mUser;
    PrefManager mPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initializing User object
        mUser = new User(this);

        //Views from the layout
        EditText user =(EditText) findViewById(R.id.loginusername);
        EditText password =(EditText) findViewById(R.id.loginpassword);

        //For Session Management
        mPrefManager =new PrefManager(this);
        String usr = mPrefManager.getUser();
        String psw = mPrefManager.getPassword();

        if(!(usr.equals("0")||psw.equals("0"))){
            user.setText(usr);
            password.setText(psw);
            login(findViewById(R.id.loginButton));
        }

        //TO remove errors from the views
       user.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int id = v.getId();
                removeWarning(id);
                return true;
            }
        });

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int id = v.getId();
                removeWarning(id);
                return true;
            }
        });

        //setting intent to go to signup if user don't have an account
        TextView noAccount = (TextView) findViewById(R.id.no_account);
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
    }

    public void login(View view){
        EditText usernameView = (EditText) findViewById(R.id.loginusername);
        String user= usernameView.getText().toString();

        EditText passwordView = (EditText) findViewById(R.id.loginpassword);
        String pass = passwordView.getText().toString();
            if(authenticate(user,pass))
            {
                startSession(user,pass);
            }else{
                showWarning(R.id.loginusername,R.string.loginError);
                showWarning(R.id.loginpassword,R.string.loginError);
            }
        }

    public boolean authenticate(String user, String pass){
        if(mUser.exist(user)){
            if(mUser.passwordMatches(pass))
                return true;
            else
                return false;
        }
        else{
            return false;
        }
    }


    private void startSession(String user,String pass){
        mUser.initialize();
        mPrefManager.startSession(user, pass);
        Intent intent = new Intent(this,HomeActivity.class);

        startActivity(intent);
    }

    private void showWarning(int id, int warning){
        TextView warningView = (TextView) findViewById(id);
        warningView.setVisibility(View.VISIBLE);
        String warn = getString(warning);
        warningView.setError(warn);
    }

    private void removeWarning(int id)
    {
        TextView view = (TextView) findViewById(id);
        view.setError(null);
    }

}
