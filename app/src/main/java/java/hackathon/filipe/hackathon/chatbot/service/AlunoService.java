package java.hackathon.filipe.hackathon.chatbot.service;

import java.hackathon.filipe.hackathon.chatbot.model.Aluno;
import java.hackathon.filipe.hackathon.chatbot.model.CadastroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AlunoService {
    @GET("?plot=full")
    Call<Aluno> buscarTodos(@Query("s") String filme, @Query("apikey") String apikey, @Query("page") String cont);

    @POST("InstitutoProjetoVida")
    Call<CadastroResponse> cadastrarAluno(@Body Aluno aluno);
}
