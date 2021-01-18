package com.project.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.net.CookieHandler;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private ImageView profileImage;
    private TextView name, email, id;
    private Button signOut;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage=findViewById(R.id.profileImage);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        id=findViewById(R.id.id);
        signOut=findViewById(R.id.signOut);
        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if(status.isSuccess()){
                            goToActivity5();
                        }
                        else{
                            Toast.makeText(ProfileActivity.this, "Logout Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void goToActivity5(){
        startActivity(new Intent(ProfileActivity.this, Activity5.class));
        finish();
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void HandleSignInResult(GoogleSignInResult result, CookieHandler Picasso){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            name.setText(account.getDisplayName());
            email.setText(account.getEmail());
            id.setText(account.getId());

        }
    }
}