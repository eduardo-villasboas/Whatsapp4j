package it.auties.whatsapp.protobuf.setting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Accessors(fluent = true)
public final class UnarchiveChatsSetting implements Setting {
  @JsonProperty("1")
  @JsonPropertyDescription("bool")
  private boolean unarchiveChats;

  @Override
  public String indexName() {
    return "unknown";
  }
}
