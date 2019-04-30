package hyp.mobile.com.br;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroBrokerActivity extends AppCompatActivity {

    private EditText name;
    private EditText address;
    private EditText port;
    private EditText userName;
    private EditText userPass;
    private EditText clientID;
    private Button botaoCadastrar;
    private SQLiteDatabase bancoDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_broker);




        try{
            //Recuperar Componentes
            name = (EditText) findViewById(R.id.nameBrokerId);
            address = (EditText) findViewById(R.id.addressBrokerId);
            port = (EditText) findViewById(R.id.portBrokerId);
            userName = (EditText) findViewById(R.id.userNameBrokerId);
            userPass = (EditText) findViewById(R.id.userPassBrokerId);
            clientID = (EditText) findViewById(R.id.clientIDBrokerId);
            botaoCadastrar = (Button) findViewById(R.id.botaoCadastrarBrokerId);

            //Banco de dados
            bancoDados = openOrCreateDatabase("app_hyp", MODE_PRIVATE, null);

            //Tabela tarefas
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS broker(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR,  address VARCHAR, port VARCHAR, userName VARCHAR, userPass VARCHAR, " +
                    "clientID VARCHAR) ");

            botaoCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String textoName = name.getText().toString();
                    String textoAddress = address.getText().toString();
                    String textoPort = port.getText().toString();
                    String textoUserName = userName.getText().toString();
                    String textoUserPass = userPass.getText().toString();
                    String textoClientID = clientID.getText().toString();

                    salvarBroker(textoName, textoAddress, textoPort, textoUserName, textoUserPass, textoClientID);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void salvarBroker(String textoName, String textoAddress, String textoPort,
                              String textoUserName, String textoUserPass, String textoClientID){

        try{
            if(textoName.equals("") || textoAddress.equals("") || textoPort.equals("") ||
                    textoUserName.equals("") || textoUserPass.equals("") || textoClientID.equals("")){
                Toast.makeText(CadastroBrokerActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            }else{
                bancoDados.execSQL("INSERT INTO broker (name, address, port, userName, userPass, clientID) " +
                        "values ('" + textoName +  "', '" + textoAddress + "', '" + textoPort + "', " +
                        "'" + textoUserName+ "', '" + textoUserPass + "', '" + textoClientID + "' ) ");
                Toast.makeText(CadastroBrokerActivity.this, "Broker salvo com sucesso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CadastroBrokerActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }

}
