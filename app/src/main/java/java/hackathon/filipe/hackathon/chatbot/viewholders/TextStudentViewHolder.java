package java.hackathon.filipe.hackathon.chatbot.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.hackathon.filipe.hackathon.model.MessageBot;
import java.hackathon.filipe.hackathon.R;
import java.hackathon.filipe.hackathon.chatbot.model.Conteudo;
import java.util.List;

public class TextStudentViewHolder extends ViewHolder {

    private TextView titulo;
    private TextView subtitulo;
    private ImageView imagemTema;
    private List<Conteudo> conteudos;

    public TextStudentViewHolder(View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.tv_titulo);
        subtitulo = itemView.findViewById(R.id.tv_subtitulo);
        imagemTema = itemView.findViewById(R.id.img_tema);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void bindType(MessageBot item) {

    }

    @Override
    public void bindType(Conteudo item) {
       titulo.setText(item.getTitulo());
       subtitulo.setText(item.getSubtitulo());
       imagemTema.setImageResource(item.getImagemTema());

    }


}
