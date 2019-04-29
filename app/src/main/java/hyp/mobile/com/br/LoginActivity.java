package hyp.mobile.com.br;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText emailLogin;
    private EditText passwdLogin;
    private Button botaoLogar;
    private TextView registroUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = (EditText) findViewById(R.id.loginEmailId);
        passwdLogin = (EditText) findViewById(R.id.loginPasswdId);
        botaoLogar = (Button) findViewById(R.id.loginBotaoId);
        registroUsuario = (TextView) findViewById(R.id.redirecionaCadastroId);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}
