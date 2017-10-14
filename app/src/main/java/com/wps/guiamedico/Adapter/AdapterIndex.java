package com.wps.guiamedico.Adapter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wps.guiamedico.MainActivity;
import com.wps.guiamedico.MapsActivity;
import com.wps.guiamedico.R;
import com.wps.guiamedico.Model.Estabelecimento;
import com.wps.guiamedico.indexActivity;


/**
 * Created by grupoSisSaúde on 04/09/2017.
 */

public class AdapterIndex extends RecyclerView.Adapter<AdapterIndex.ViewHolder> {
    private List<Estabelecimento> values;
    private Context pagina;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterIndex(List<Estabelecimento> myDataset,Context pagina) {
        values = myDataset;
        this.pagina = pagina;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public RelativeLayout txtLinha;
        public View layout;

        public ViewHolder(View v)
        {
            super(v);
            layout = v;
            txtLinha = (RelativeLayout) v.findViewById(R.id.linha);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, Estabelecimento item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterIndex.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Estabelecimento name = values.get(position);
        holder.txtHeader.setText(name.getNomeFantasia());
        holder.txtLinha.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(pagina,name.getNomeFantasia().toString(), Toast.LENGTH_SHORT).show();
                Intent chamaTela = new Intent(pagina,MapsActivity.class);
                chamaTela.putExtra("nome",name.getNomeFantasia().toString());
                chamaTela.putExtra("telefone","Telefone: " + name.getTelefone());
                chamaTela.putExtra("endereco",name.getLogradouro() + ", " + name.getBairro() +" - " + name.getUf() + ", " +  name.getCep());
                chamaTela.putExtra("latitude",Float.toString(name.getLat()));
                chamaTela.putExtra("longitude",Float.toString(name.getLongitude()));
                pagina.startActivity(chamaTela);
            }
        });

        holder.txtFooter.setText("Telefone: " + name.getTelefone());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
