package java.hackathon.filipe.hackathon.chatbot;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.hackathon.filipe.hackathon.R;
import java.hackathon.filipe.hackathon.chatbot.model.Conteudo;
import java.hackathon.filipe.hackathon.chatbot.viewholders.TextStudentViewHolder;
import java.hackathon.filipe.hackathon.chatbot.viewholders.ViewHolder;
import java.util.ArrayList;
import java.util.List;

public class PooAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TextView titulo;
    private TextView subtitulo;
    private ImageView imagemTema;

    private List<Conteudo> conteudosList;

    public PooAdapter(){
        conteudosList = new ArrayList<>();
        conteudosList.add(new Conteudo("Métodos", "O que é um método?", R.mipmap.metodos));
        conteudosList.add(new Conteudo("Classes", "O que é um método?", R.mipmap.classes));
        conteudosList.add(new Conteudo("Objetos", "O que é um método?", R.mipmap.metodos));
        conteudosList.add(new Conteudo("Métodos Especiais", "O que é um método?", R.mipmap.classes));
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_poo, viewGroup, false);
        return new TextStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Conteudo c = conteudosList.get(position);
        if (c != null) {
            ((ViewHolder) holder).bindType(c);
            if (position == 0) {
                if (holder instanceof TextStudentViewHolder) {

                    titulo = (TextView) holder.itemView.findViewById(R.id.tv_titulo);
                    subtitulo = (TextView) holder.itemView.findViewById(R.id.tv_subtitulo);
                    imagemTema = (ImageView) holder.itemView.findViewById(R.id.img_tema);
                    loadingMessage(c);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return conteudosList.size();
    }

    private void loadingMessage(Conteudo c) {
        titulo.setText(c.getTitulo());
        subtitulo.setText(c.getSubtitulo());
        imagemTema.setImageResource(c.getImagemTema());

    }
}
