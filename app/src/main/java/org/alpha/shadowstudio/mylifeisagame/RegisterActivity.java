package org.alpha.shadowstudio.mylifeisagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText contraseña;
    private EditText contraseña2;
    private EditText usuario;
    private EditText nombre;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmail);
        contraseña = findViewById(R.id.editTextContraseña);
        contraseña = findViewById(R.id.editTextContraseña2);
        usuario = findViewById(R.id.editTextUsuario);
        nombre = findViewById(R.id.editTextNombre);
        Button registrar = findViewById(R.id.registrarse);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contraseña.getText().toString().compareTo(contraseña2.getText().toString()) == 0) {
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), contraseña.getText().toString())
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Firebase", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        String se=user.getUid();
                                        Usuario us= new Usuario(nombre.getText().toString(),email.getText().toString(), usuario.getText().toString());
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference usuarioRef = database.getReference("Usuarios");
                                        String clau = usuarioRef.push().getKey();
                                        usuarioRef.child(clau).setValue(us);
                                        Intent intent= new Intent(getApplicationContext(), CargaActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Log.w("Firebase", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_LONG);
                }
            }

        });
    }
}
