package java.hackathon.filipe.hackathon;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import java.hackathon.filipe.hackathon.chatbot.ChatBotAdapter;
import java.hackathon.filipe.hackathon.chatbot.model.Aluno;
import java.hackathon.filipe.hackathon.chatbot.model.CadastroResponse;
import java.hackathon.filipe.hackathon.chatbot.service.RetrofitConfig;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity implements SendMessageBot {


    private static final String TAG = "ERRO CHAT";
    private ConversationService myConversationService = null;
    private EditText messageUser;
    private final String IBM_USERNAME = "92150c96-6ceb-46e9-a107-3178ee69379d";
    private final String IBM_PASSWORD = "Cimsk6AcNNPE";
    private final String IBM_WORKSPACE_ID = "d6db8b3f-b60a-42ef-8621-96c8521a37a7";
    private final String WELCOME_MESSAGE = "welcome";

    private RecyclerView mMessageRecycler;
    private ChatBotAdapter chatBotAdapter;
    private Activity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageUser = findViewById(R.id.message_user);
        mMessageRecycler = findViewById(R.id.reyclerview_message_list);
        this.context = this;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        chatBotAdapter = new ChatBotAdapter();
        mMessageRecycler.setLayoutManager(layoutManager);
        mMessageRecycler.setAdapter(chatBotAdapter);

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
    }


    public void sendTextChatbot(View view) {


        Call<CadastroResponse> call = new RetrofitConfig().getAlunoService().cadastrarAluno(new Aluno("vinicius", "vinicius", "vinicius"));
        call.enqueue(new Callback<CadastroResponse>() {
            @Override
            public void onResponse(Call<CadastroResponse> call, Response<CadastroResponse> response) {
                Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CadastroResponse> call, Throwable t) {
                System.out.println("eroooo" + t.getMessage());

            }
        });



        MessageBot m = chatBotAdapter.lastMessage();

        chatBotAdapter.addMessage(new MessageBot(messageUser.getText().toString(), false, MessageType.TEXT_USER, "USER"));
        chatBotAdapter.notifyDataSetChanged();
        mMessageRecycler.scrollToPosition(0);
        enviaWatson(messageUser.getText().toString(), this);
        clearEditText();
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
                            messageBot[0] = new MessageBot(response.getText().get(0), true, 1, response.getIntents().get(0).getIntent());
                        }else{
                            messageBot[0] = new MessageBot("Ainda estou aprendendo, não entendi o que você falou :(", true, 1, "NAOENTENDE");

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
        System.out.println("FILIPEEE" + messageBot.toString());
        chatBotAdapter.addMessage(messageBot);
        chatBotAdapter.notifyDataSetChanged();
        mMessageRecycler.scrollToPosition(0);

    }

    public void clearEditText() {
        messageUser.setText("");
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(messageUser.getWindowToken(), 0);
    }


}
