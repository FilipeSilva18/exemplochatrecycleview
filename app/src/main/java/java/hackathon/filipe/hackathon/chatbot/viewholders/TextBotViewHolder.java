package java.hackathon.filipe.hackathon.chatbot.viewholders;

import android.view.View;
import android.widget.TextView;

import java.hackathon.filipe.hackathon.MessageBot;
import java.hackathon.filipe.hackathon.R;


public class TextBotViewHolder extends ViewHolder {



    TextView textView;

    public TextBotViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text_message_body_bot);
    }

    @Override
    public void bindType(MessageBot item) {
        textView.setText(item.getMessage());
    }
}
