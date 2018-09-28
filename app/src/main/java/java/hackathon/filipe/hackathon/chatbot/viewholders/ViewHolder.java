package java.hackathon.filipe.hackathon.chatbot.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.hackathon.filipe.hackathon.MessageBot;

public abstract class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindType(MessageBot item);
}
