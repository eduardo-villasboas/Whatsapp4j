package it.auties.whatsapp4j.response.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.auties.whatsapp4j.response.model.json.JsonResponseModel;

import java.util.Objects;

/**
 * A json model that contains information about a requested profile picture
 */
public final record ChatPictureResponse(int status, String url, String tag) implements JsonResponseModel {
    @JsonCreator
    public ChatPictureResponse(@JsonProperty("eurl") String url, @JsonProperty("tag") String tag, @JsonProperty("status") Integer status) {
        this(Objects.requireNonNullElse(status, 200), url, tag);
    }
}
