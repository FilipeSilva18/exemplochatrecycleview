package java.hackathon.filipe.hackathon;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

import java.hackathon.filipe.hackathon.chatbot.ChatBotAdapter;
import java.hackathon.filipe.hackathon.databinding.ActivityMainBinding;
import java.hackathon.filipe.hackathon.decorator.CalculadorDeImposto;
import java.hackathon.filipe.hackathon.decorator.ICMS;
import java.hackathon.filipe.hackathon.decorator.ISS;
import java.hackathon.filipe.hackathon.decorator.Imposto;
import java.hackathon.filipe.hackathon.decorator.Orcamento;


public class MainActivity extends AppCompatActivity implements SendMessageBot{


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
    private LinearLayout toggleButtons;
    private LinearLayout edtView;
    private Button yes;
    private Button no;

    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.executePendingBindings();

        messageUser = findViewById(R.id.message_user);
        mMessageRecycler = findViewById(R.id.reyclerview_message_list);
        toggleButtons = findViewById(R.id.layout_toggle);
        edtView = findViewById(R.id.layout_chatbox);
        yes = findViewById(R.id.btn_yes);
        no = findViewById(R.id.btn_no);
        this.context = this;

        Imposto iss = new ISS(new ICMS());
        Imposto icms = new ICMS();
        System.out.println("----------------SINGLETON----------------");
        Orcamento orcamento = new Orcamento(500);
        CalculadorDeImposto calculador = new CalculadorDeImposto();
        calculador.RealizaCalculo(orcamento, iss);
        calculador.RealizaCalculo(orcamento, icms);
        System.out.println("----------------SINGLETON----------------");

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
        edtView.setVisibility(View.GONE);
        toggleButtons.setVisibility(View.VISIBLE);
    }


    public void sendTextChatbot(View view) {
        messageUser.setText("");
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(messageUser.getWindowToken(), 0);

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

                            if(response.getIntents().get(0).getIntent().equals("POO")){
                                messageBot[0] = new MessageBot(response.getText().get(0), true, MessageType.TEXT_STUDENT, response.getIntents().get(0).getIntent());
                            }else messageBot[0] = new MessageBot(response.getText().get(0), true, 1, response.getIntents().get(0).getIntent());
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
        mMessageRecycler.scrollToPosition(0);
        MessageBot m1 = chatBotAdapter.lastMessage();
        System.out.println(m1.getTag());
        String tag = messageBot.getTag();
        if (tag.equals("inicio")) {
            yes.setText("Claaaro!");
            no.setText("Não, está chato :/");
        } else if (tag.equals("LinearEstruturada")) {
            yes.setText("Com certeza!");
            no.setText("Não, eu cansei :P");
        } else if (tag.equals("Modular")) {
            yes.setText("Até que enfim!");
            no.setText("Demorou o/");
        }else if (tag.equals("POO")){
            yes.setText("Ook!");
            no.setText("Agora não :x");


        }


    }

    public void btnYes(View view) {

        MessageBot m = chatBotAdapter.lastMessage();
        String tag = m.getTag();

        chatBotAdapter.addMessage(new MessageBot(yes.getText().toString(), false, MessageType.TEXT_USER, "USER"));
        chatBotAdapter.notifyDataSetChanged();
        mMessageRecycler.scrollToPosition(0);

        if (tag.equals("Welcome")) {
            enviaWatson("baixonivel", this);
        } else if (tag.equals("inicio")) {
            enviaWatson("linearestruturada", this);
        } else if (tag.equals("LinearEstruturada")) {
            enviaWatson("modular", this);
        } else if (tag.equals("Modular")) {
            enviaWatson("poo", this);
        }else if(tag.equals("POO")){
            enviaWatson("poo", this);
        }

    }

    public void btnNo(View view) {
        enviaWatson("", this);

    }

}
