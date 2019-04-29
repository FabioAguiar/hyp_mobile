package hyp.mobile.com.br;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import hyp.mobile.com.br.config.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {

    private Button deslogar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deslogar = (Button) findViewById(R.id.botaoDeslogarId);



        deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = ConfiguracaoFirebase.getFirebaseAuth();
                auth.signOut();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
