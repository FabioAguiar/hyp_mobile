package hyp.mobile.com.br;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import hyp.mobile.com.br.config.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ){
            case R.id.item_deslogar:
                deslogarUsuario();
                return true;
            case  R.id.item_adicionar_broker:
                abrirTelaCadastroBroker();
                return true;
        }

        return true;
    }

    private void deslogarUsuario(){
        auth = ConfiguracaoFirebase.getFirebaseAuth();
        auth.signOut();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void abrirTelaCadastroBroker(){
        Intent intent = new Intent(MainActivity.this, CadastroBrokerActivity.class);
        startActivity(intent);
    }
}
