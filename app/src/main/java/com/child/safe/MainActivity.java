package com.child.safe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.child.safe.impl.UserRequestImpl;
import com.child.safe.interfaces.UserRequest;
import com.child.safe.interfaces.VolleyCallback;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText editUn, editPW;
    UserRequest userRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        initializeViews();
        initializeListener();
    }

    private void initializeListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = editUn.getText().toString();
                String pw = editPW.getText().toString();

                if (un.equals("") || pw.equals("")) {
                    Toast.makeText(MainActivity.this, "Please Don't Leave Empty Fields", Toast.LENGTH_SHORT).show();
                } else {
                    userRequest.login(MainActivity.this, un, pw, null, new VolleyCallback() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(MainActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(VolleyError error) {
                            Log.e("ERROR", error.getMessage());
                            Toast.makeText(MainActivity.this, "Unexpected error happen", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void initializeViews() {
        btnLogin = findViewById(R.id.btnLogin);
        editUn = findViewById(R.id.editUsername);
        editPW = findViewById(R.id.editPassword);
        userRequest = new UserRequestImpl();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}