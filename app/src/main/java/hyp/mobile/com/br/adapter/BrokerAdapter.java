package hyp.mobile.com.br.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hyp.mobile.com.br.R;
import hyp.mobile.com.br.model.Broker;

public class BrokerAdapter extends ArrayAdapter<Broker> {

    private ArrayList<Broker> brokers;
    private Context context;

    public BrokerAdapter(@NonNull Context c, @NonNull ArrayList<Broker> objects) {
        super(c, 0, objects);
        this.brokers = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        //Verifica se a lista de contatos esta vazia
        if( brokers != null ){

            // Inicializa objeto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta view a partir do xml
            view = inflater.inflate(R.layout.lista_broker, parent, false);

            //recupera elemento para exibição
            TextView nameBroker = (TextView) view.findViewById(R.id.tv_name);
            TextView addressBroker = (TextView) view.findViewById(R.id.tv_address);

            Broker broker = brokers.get( position );
            nameBroker.setText(broker.getName());
            addressBroker.setText(broker.getAddress());
        }

        return view;
    }


}
