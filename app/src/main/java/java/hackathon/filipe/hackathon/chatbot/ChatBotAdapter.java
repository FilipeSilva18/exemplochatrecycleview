package java.hackathon.filipe.hackathon.chatbot;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.hackathon.filipe.hackathon.MessageBot;
import java.hackathon.filipe.hackathon.MessageType;
import java.hackathon.filipe.hackathon.R;
import java.hackathon.filipe.hackathon.chatbot.viewholders.TextBotViewHolder;
import java.hackathon.filipe.hackathon.chatbot.viewholders.TextUserViewHolder;
import java.hackathon.filipe.hackathon.chatbot.viewholders.ViewHolder;
import java.util.ArrayList;
import java.util.List;

public class ChatBotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MessageBot> messages;
    private TextView textViewBot;

    public ChatBotAdapter(){
        messages = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view;

        switch (type) {

            case MessageType.TEXT_BOT:
                view = LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.message_bot, viewGroup, false);
                return new TextBotViewHolder(view);
            case MessageType.TEXT_USER:
                view = LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.message_user, viewGroup, false);
                return new TextUserViewHolder(view);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageBot message = messages.get(position);

        if (message != null) {
            ((ViewHolder) holder).bindType(message);
            if (message.isBot()) {
                if (holder instanceof TextBotViewHolder) {
                    textViewBot = (TextView) holder.itemView.findViewById(R.id.text_message_body_bot);
                    textViewBot.setText(message.getMessage());
                }
            }else{
                if (holder instanceof TextBotViewHolder) {
                    textViewBot = (TextView) holder.itemView.findViewById(R.id.text_message_body_user);
                    textViewBot.setText(message.getMessage());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (messages != null) {
            MessageBot object = messages.get(position);
            if (object != null) {
                return object.getType();
            }
        }

        return 0;
    }

    public void addMessage(MessageBot message) {
        messages.add(0, message);
        notifyDataSetChanged();
    }

    public MessageBot lastMessage() {
        return messages.get(0);
    }


}
