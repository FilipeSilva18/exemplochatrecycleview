package java.hackathon.filipe.hackathon.chatbot.model;

import com.google.gson.annotations.SerializedName;

public class CadastroResponse {
    @SerializedName("_id")
    String id;
    @SerializedName("createAt")
    String createdAt;
    @SerializedName("__v")
    String v;

    public CadastroResponse(String id, String createdAt, String v) {
        this.id = id;
        this.createdAt = createdAt;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
