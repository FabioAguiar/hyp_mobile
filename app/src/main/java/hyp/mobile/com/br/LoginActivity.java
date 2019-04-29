package hyp.mobile.com.br;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hyp.mobile.com.br.config.ConfiguracaoFirebase;
import hyp.mobile.com.br.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText emailLogin;
    private EditText passwdLogin;
    private Button botaoLogar;
    private TextView registroUsuario;
    private Usuario usuario;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();

        emailLogin = (EditText) findViewById(R.id.loginEmailId);
        passwdLogin = (EditText) findViewById(R.id.loginPasswdId);
        botaoLogar = (Button) findViewById(R.id.loginBotaoId);
        registroUsuario = (TextView) findViewById(R.id.redirecionaCadastroId);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario();
                usuario.setEmail(emailLogin.getText().toString());
                usuario.setSenha(passwdLogin.getText().toString());
                validarLogin();
            }
        });

        registroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastroUsuario.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void verificarUsuarioLogado(){
        auth = ConfiguracaoFirebase.getFirebaseAuth();

        if(auth.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }

    private void validarLogin(){

        auth = ConfiguracaoFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    abrirTelaPrincipal();
                    Toast.makeText(LoginActivity.this, "Logado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Erro ao logar!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
