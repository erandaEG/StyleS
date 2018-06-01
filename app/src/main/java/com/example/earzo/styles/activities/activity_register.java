package com.example.earzo.styles.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.earzo.styles.R;
import com.example.earzo.styles.models.User;

import java.util.List;

public class activity_register extends activity_base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void onResume(){
        super.onResume();

        setTitle(R.string.app_name);
    }

    /** Called when the user taps the Register button */
    public void openLoginScreen(View view) {
        EditText editTextNewFirstName = (EditText) findViewById(R.id.editText_newFirstName);
        EditText editTextNewLastName = (EditText) findViewById(R.id.editText_newLastName);
        EditText editTextNewEmail = (EditText) findViewById(R.id.editText_newUserEmail);
        EditText editTextNewPW = (EditText) findViewById(R.id.editText_newPassword);
        EditText editTextConfirmNewPW = (EditText) findViewById(R.id.editText__confirmNewPassword);

        String firstName = editTextNewFirstName.getText().toString().trim();
        String lastName = editTextNewLastName.getText().toString().trim();
        String email = editTextNewEmail.getText().toString().trim();
        String newPW = editTextNewPW.getText().toString();
        String confirmPW = editTextConfirmNewPW.getText().toString();

        validateInputs(new EditText[]{editTextNewFirstName, editTextNewEmail, editTextNewPW, editTextConfirmNewPW}, firstName, lastName, email, newPW, confirmPW);
    }


    public void validateInputs(EditText[] userDetails, String firstName, String lastName, String email, String newPW, String confirmPW){

        List<User> userList = User.find(User.class, "user_email = ? and first_name = ? and last_name = ?", email, firstName, lastName);
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        int count = 0;

        for(EditText currentDetail : userDetails){
            if(currentDetail.getText().toString().trim().isEmpty()){
                currentDetail.requestFocus();
                currentDetail.setError(getString(R.string.error_emptyFields));
                count++;
            }
        }

        if(count == 0) {
            if (userList.size() == 1) {
                toast.makeText(this, R.string.error_existingUser, Toast.LENGTH_SHORT).show();
            } else if (newPW.equals(confirmPW)) {
                User user = new User(email, firstName, lastName, newPW);
                user.save();

                Intent intent = new Intent(this, activity_login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                this.finish();
            } else {
                toast.makeText(this, R.string.error_PWunmatch, Toast.LENGTH_SHORT).show();
            }

            count = 0;
        }
    }
}
