package app.javafile.test.myapplicationfireb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button log , sign ;
    EditText email , pass ;

    private ProgressDialog progressDialog;
    private FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//--------------------------------------------------------------------------------------------------
        auth = FirebaseAuth.getInstance() ;
       /* if (auth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }*/
//--------------------------------------------------------------------------------------------------
        progressDialog = new ProgressDialog(this) ;
        log = findViewById(R.id.LoginBot) ;
        sign = findViewById(R.id.SignBot) ;
        email = findViewById(R.id.EmailEditText) ;
        pass = findViewById(R.id.passEditText) ;

    }

    public void LOGIN ( View view ) {

        String Email = email.getText().toString().trim() ;
        String Pass = pass.getText().toString().trim();

        progressDialog.setMessage("    TeData.....");
        progressDialog.show();

        auth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Successful ",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this,"NotSuccessful ",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        }) ;
    }


    public void SIGN ( View view ) {

        String Email = email.getText().toString() ;
        String Pass = pass.getText().toString();

        if (TextUtils.isEmpty(Email)){
            Toast.makeText(this,"Enter your email ",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Pass)){
            Toast.makeText(this,"Enter your pass ",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("    TeData.....");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Successful ",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"NotSuccessful ",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
