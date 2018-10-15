package java.hackathon.filipe.hackathon.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import java.hackathon.filipe.hackathon.R;
import java.hackathon.filipe.hackathon.service.SendMessageBot;
import java.hackathon.filipe.hackathon.chatbot.ChatBotAdapter;
import java.hackathon.filipe.hackathon.databinding.ActivityMainBinding;
import java.hackathon.filipe.hackathon.decorator.CalculadorDeImposto;
import java.hackathon.filipe.hackathon.decorator.ICMS;
import java.hackathon.filipe.hackathon.decorator.ISS;
import java.hackathon.filipe.hackathon.decorator.Imposto;
import java.hackathon.filipe.hackathon.decorator.Orcamento;
import java.hackathon.filipe.hackathon.model.MessageBot;
import java.hackathon.filipe.hackathon.model.MessageType;


public class MainActivity extends AppCompatActivity implements SendMessageBot {


    private static final String TAG = "ERRO CHAT";
    private ConversationService myConversationService = null;
    private final String IBM_USERNAME = "92150c96-6ceb-46e9-a107-3178ee69379d";
    private final String IBM_PASSWORD = "Cimsk6AcNNPE";
    private final String IBM_WORKSPACE_ID = "d6db8b3f-b60a-42ef-8621-96c8521a37a7";
    private final String WELCOME_MESSAGE = "welcome";

    private ChatBotAdapter chatBotAdapter;


    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.executePendingBindings();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        chatBotAdapter = new ChatBotAdapter();
        binding.reyclerviewMessageList.setLayoutManager(layoutManager);
        binding.reyclerviewMessageList.setAdapter(chatBotAdapter);


        myConversationService =
                new ConversationService(
                        "2017-12-06",
                        IBM_USERNAME,
                        IBM_PASSWORD
                );
        firstMessage(this);
    }

    public void firstMessage(final SendMessageBot sendMessageBot) {
        enviaWatson(WELCOME_MESSAGE, sendMessageBot);
        binding.layoutChatbox.setVisibility(View.GONE);
        binding.layoutToggle.setVisibility(View.VISIBLE);
    }


    public void sendTextChatbot(View view) {
        binding.messageUser.setText("");
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.messageUser.getWindowToken(), 0);

    }


    public void enviaWatson(String string, final SendMessageBot sendMessageBot) {
        final MessageBot[] messageBot = new MessageBot[1];
        MessageRequest requestWelcome = new MessageRequest.Builder()
                .inputText(string)
                .build();
        myConversationService
                .message(IBM_WORKSPACE_ID, requestWelcome)
                .enqueue(new ServiceCallback<MessageResponse>() {
                    @Override
                    public void onResponse(final MessageResponse response) {
                        if (!response.getIntents().isEmpty()) {

                            if (response.getIntents().get(0).getIntent().equals("POO")) {
                                messageBot[0] = new MessageBot(response.getText().get(0), true, MessageType.TEXT_STUDENT, response.getIntents().get(0).getIntent());
                            } else
                                messageBot[0] = new MessageBot(response.getText().get(0), true, 1, response.getIntents().get(0).getIntent());
                        } else {
                            messageBot[0] = new MessageBot("Ainda estou aprendendo, não entendi o que você falou :(", true, MessageType.TEXT_BOT, "NAOENTENDE");

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sendMessageBot.sendMessageBot(messageBot[0]);
                            }
                        });

                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, e.getMessage());
                    }
                });
    }


    @Override
    public void sendMessageBot(MessageBot messageBot) {
        chatBotAdapter.addMessage(messageBot);
        chatBotAdapter.notifyDataSetChanged();
        binding.reyclerviewMessageList.scrollToPosition(0);
        MessageBot m1 = chatBotAdapter.lastMessage();
        System.out.println(m1.getTag());
        String tag = messageBot.getTag();
        if (tag.equals("inicio")) {
            binding.btnYes.setText("Claaaro!");
            binding.btnNo.setText("Não, está chato :/");
        } else if (tag.equals("LinearEstruturada")) {
            binding.btnYes.setText("Com certeza!");
            binding.btnNo.setText("Não, eu cansei :P");
        } else if (tag.equals("Modular")) {
            binding.btnYes.setText("Até que enfim!");
            binding.btnNo.setText("Demorou o/");
        } else if (tag.equals("POO")) {


        }


    }

    public void btnYes(View view) {

        MessageBot m = chatBotAdapter.lastMessage();
        String tag = m.getTag();

        chatBotAdapter.addMessage(new MessageBot(binding.btnYes.getText().toString(), false, MessageType.TEXT_USER, "USER"));
        chatBotAdapter.notifyDataSetChanged();
        binding.reyclerviewMessageList.scrollToPosition(0);

        if (tag.equals("Welcome")) {
            enviaWatson("baixonivel", this);
        } else if (tag.equals("inicio")) {
            enviaWatson("linearestruturada", this);
        } else if (tag.equals("LinearEstruturada")) {
            enviaWatson("modular", this);
        } else if (tag.equals("Modular")) {
            Intent it = new Intent(this, PooOrientacaoActivity.class);
            startActivity(it);
        }

    }

    public void btnNo(View view) {
        Intent it = new Intent(this, MenuActivity.class);
        startActivity(it);

    }

}
