package hyp.mobile.com.br;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import hyp.mobile.com.br.adapter.BrokerAdapter;
import hyp.mobile.com.br.config.ConfiguracaoFirebase;
import hyp.mobile.com.br.model.Broker;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private SQLiteDatabase bancoDados;
    private ListView listaViewBroker;
    private ArrayAdapter adapter;
    private ArrayList<Broker> listaBrokers;
    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<String> itens;
    private ArrayList<Integer>  ids;
    private Broker broker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            //Lista
            listaViewBroker = (ListView) findViewById(R.id.brokerListID);

            //Banco de dados
            bancoDados = openOrCreateDatabase("app_hyp", MODE_PRIVATE, null);

            //Tabela de brokers cadastrados
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS broker(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR,  address VARCHAR, port VARCHAR, userName VARCHAR, userPass VARCHAR, " +
                    "clientID VARCHAR) ");

            listaViewBroker.setLongClickable(false);
            listaViewBroker.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(MainActivity.this, PanelActivity.class);
                    startActivity(intent);

                    //removerBroker(ids.get(position));
                    //recuperarBroker();
                }
            });

            recuperarBroker();

        }catch(Exception e){
            e.printStackTrace();
        }


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

    private void recuperarBroker(){

        try{
            //Recuperar brokers
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM broker ORDER BY id DESC", null);

            //Recuperar os ids das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaNameBroker = cursor.getColumnIndex("name");

            //Criar adaptador
            listaBrokers = new ArrayList<>();
            broker = new Broker();
           // adapter = new BrokerAdapter( MainActivity.this, listaBrokers );
           // listaViewBroker.setAdapter( adapter );

            itens = new ArrayList<String>();
            ids = new ArrayList<Integer>();


            itensAdaptador = new ArrayAdapter<String>(getApplicationContext(),

                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens);
            listaViewBroker.setAdapter( itensAdaptador );


            //listar as tarefas
            cursor.moveToFirst();
            //Log.i("Resultado: ", cursor.getString(indiceColunaTarefa) );

            while (cursor != null){
                Log.i("Resultado - ", "Broker: " + cursor.getString(indiceColunaNameBroker) );
                itens.add( cursor.getString(indiceColunaNameBroker) );
                ids.add( Integer.parseInt(cursor.getString(indiceColunaId)) );

                broker.setName( cursor.getString(cursor.getColumnIndex("name")) );
                broker.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                broker.setPort(cursor.getString(cursor.getColumnIndex("port")));
                broker.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
                broker.setUserPass(cursor.getString(cursor.getColumnIndex("userPass")));
                broker.setClientID(cursor.getString(cursor.getColumnIndex("clientID")));

                listaBrokers.add(broker);

                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerBroker(Integer id){

        try{
            bancoDados.execSQL("DELETE FROM broker WHERE id ="+id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
