package it.auties.whatsapp4j.response.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.auties.whatsapp4j.response.model.json.JsonResponseModel;
import lombok.NonNull;

/**
 * A json model that contains information about a change in a WhatsappGroup's subject
 *
 * @param subject   the new subject
 * @param timestamp the timestamp in seconds since {@link java.time.Instant#EPOCH}
 * @param authorJid the jid of the participant that changed the subject
 */
public final record SubjectChangeResponse(@NonNull String subject, @JsonProperty("s_t") long timestamp,
                                          @JsonProperty("s_o") @NonNull String authorJid) implements JsonResponseModel {
}
