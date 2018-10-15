package java.hackathon.filipe.hackathon.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.hackathon.filipe.hackathon.R;
import java.hackathon.filipe.hackathon.chatbot.model.Conteudo;
import java.hackathon.filipe.hackathon.chatbot.viewholders.ViewHolder;
import java.hackathon.filipe.hackathon.model.MessageBot;
import java.hackathon.filipe.hackathon.model.PadraoProjeto;
import java.util.ArrayList;
import java.util.List;

public class PadroesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TextView nome;
    private ImageView img;
    private List<PadraoProjeto> listPadroes;
    private AdapterListener listener;

    public class PadroesViewHolder extends ViewHolder {
        private TextView nome;
        private ImageView imagem;
        private List<PadraoProjeto> padroes;

        public PadroesViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.tv_nome_padrao);
            imagem = itemView.findViewById(R.id.img_1);
        }

        @Override
        public void bindType(MessageBot item) {

        }

        @Override
        public void bindType(Conteudo item) {

        }

        public void bindType(PadraoProjeto item) {
            nome.setText(item.getNome());
            imagem.setImageResource(item.getFoto());
        }

        public void bind(final PadraoProjeto padraoProjeto, final AdapterListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setOnClickItem(padraoProjeto);
                }
            });
        }
    }


    public PadroesAdapter(AdapterListener adapterListener) {
        this.listener = adapterListener;
        listPadroes = new ArrayList<>();
        listPadroes.add(new PadraoProjeto("strategy", R.mipmap.classes));
        listPadroes.add(new PadraoProjeto("decorator", R.mipmap.metodos));
        listPadroes.add(new PadraoProjeto("singleton", R.mipmap.laura));

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_padrao_list, viewGroup, false);
        return new PadroesAdapter.PadroesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PadroesAdapter.PadroesViewHolder) {
            ((PadroesViewHolder) holder).nome.setText(listPadroes.get(position).getNome());
            ((PadroesViewHolder) holder).imagem.setImageResource(listPadroes.get(position).getFoto());
            ((PadroesViewHolder) holder).bind(listPadroes.get(position), listener);
        }
    }


    @Override
    public int getItemCount() {
        return listPadroes.size();
    }


    public interface AdapterListener {
        void setOnClickItem(PadraoProjeto padraoProjeto);
    }

}


