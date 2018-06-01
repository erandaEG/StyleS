package com.example.earzo.styles.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.earzo.styles.R;
import com.example.earzo.styles.extra.UserSession;
import com.example.earzo.styles.models.Product;
import com.example.earzo.styles.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class activity_login extends activity_base {

    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.AppTheme);


        List<Product> existingProductList = Product.listAll(Product.class);

        if(existingProductList.size() == 0){

            String json;

            try{
                AssetManager mngr = getAssets();
                Scanner a = new Scanner(mngr.open("products.json"));

                StringBuffer sb = new StringBuffer();

                while(a.hasNext()){
                    sb.append(a.nextLine());
                }

                json = sb.toString();

                List<Product> productList = new Gson().fromJson(json, new TypeToken<List<Product>>(){}.getType());
                Product.saveInTx(productList);

                /*for(Product product : productList){
                    product.save();
                }*/

            }catch(IOException e){
                e.printStackTrace();
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onResume(){
        super.onResume();

        setTitle(R.string.app_name);
    }


    /** Called when the user taps the text: "No account? Create one!" */
    public void openRegisterScreen(View view){

        Intent intent = new Intent(activity_login.this, activity_register.class);
        startActivity(intent);
    }


    /** Called when the user taps the text: "Forgot Password?" */
    public void openChangePasswordScreen(View view){

        Intent intent = new Intent(this, activity_forgottenPasswordChange.class);
        startActivity(intent);
    }

    /** Called when the user taps the Login button */
    public void login(View view) {
        EditText editTextUserEmail = (EditText) findViewById(R.id.editText_registeredEmail);
        EditText editTextPW = (EditText) findViewById(R.id.editText_password);
        String email = editTextUserEmail.getText().toString().trim();
        String password = editTextPW.getText().toString();

        validate(email, password);
    }

    public void validate(String email, String PW){
        List<User> userList = User.find(User.class, "user_email = ? and password = ?", email, PW);

        if(userList.size() == 1){
            UserSession userSession = UserSession.getUserSession(this);
            userSession.setUID(userList.get(0).getId());
            userSession.setLogInStatus(true);
            Intent intent = new Intent(this, activity_navigationDrawer.class);
            startActivity(intent);

            finish();
        }else if(email.isEmpty() && PW.isEmpty()){
            Toast.makeText(this, R.string.error_emptyEMAILandPW, Toast.LENGTH_SHORT).show();
        }else if(email.isEmpty()){
            EditText editTextUserEmail = (EditText) findViewById(R.id.editText_registeredEmail);
            editTextUserEmail.requestFocus();
            editTextUserEmail.setError(getString(R.string.error_emptyEMAIL));
        }else if(PW.isEmpty()){
            EditText editTextPW = (EditText) findViewById(R.id.editText_password);
            editTextPW.requestFocus();
            editTextPW.setError(getString(R.string.error_emptyPW));
        }else{
            counter--;
            final TextView textViewErrorMsg = findViewById(R.id.textView_error);

            if(counter == 0){
                findViewById(R.id.button_login).setEnabled(false);

                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        textViewErrorMsg.setText(getString(R.string.error_3attempts, millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        textViewErrorMsg.setText("");
                        findViewById(R.id.button_login).setEnabled(true);
                        counter = 3;
                    }
                }.start();
            }else{
                Toast.makeText(this, R.string.error_emailORpw, Toast.LENGTH_SHORT).show();
            }
        }
    }



    //call this method on the onClick() method of any button to create a crash by runtime exception and get a Crashlytics report
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
}
