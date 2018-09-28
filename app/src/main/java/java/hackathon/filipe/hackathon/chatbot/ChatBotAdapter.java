package java.hackathon.filipe.hackathon.chatbot;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eyalbira.loadingdots.LoadingDots;

import org.w3c.dom.Text;

import java.hackathon.filipe.hackathon.MessageBot;
import java.hackathon.filipe.hackathon.MessageType;
import java.hackathon.filipe.hackathon.R;
import java.hackathon.filipe.hackathon.chatbot.viewholders.TextBotViewHolder;
import java.hackathon.filipe.hackathon.chatbot.viewholders.TextUserViewHolder;
import java.hackathon.filipe.hackathon.chatbot.viewholders.ViewHolder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatBotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MessageBot> messages;
    private TextView textViewBot;
    private TextView textViewTime;
    private LoadingDots loadingDots;

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
            if (message.isBot() && (position == 0)) {
                if (holder instanceof TextBotViewHolder) {
                    textViewBot = (TextView) holder.itemView.findViewById(R.id.text_message_body_bot);
                    loadingDots = (LoadingDots) holder.itemView.findViewById(R.id.loading_type_message_bot);
                    textViewTime = (TextView) holder.itemView.findViewById(R.id.text_message_time);
                    loadingDots.setVisibility(View.VISIBLE);
                    loadingMessage(message.getMessage());
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
        if(message!=null)
        messages.add(0, message);
        notifyDataSetChanged();
    }

    public MessageBot lastMessage() {
        return messages.get(0);
    }

    private void loadingMessage(final String message) {
        textViewBot.setText("");
        textViewBot.setVisibility(View.GONE);

        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm");

        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        final String horaAtual = dateFormat_hora.format(data_atual);

        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                loadingDots.setVisibility(View.GONE);
                textViewBot.setVisibility(View.VISIBLE);
                textViewBot.setText(message);
                textViewTime.setText(horaAtual);
            }
        }.start();
    }


}
