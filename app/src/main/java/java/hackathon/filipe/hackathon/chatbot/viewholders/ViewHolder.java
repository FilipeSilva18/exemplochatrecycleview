package java.hackathon.filipe.hackathon.chatbot.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.hackathon.filipe.hackathon.model.MessageBot;
import java.hackathon.filipe.hackathon.chatbot.model.Conteudo;

public abstract class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindType(MessageBot item);
    public abstract void bindType(Conteudo item);
}
