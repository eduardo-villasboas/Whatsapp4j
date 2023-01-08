package it.auties.whatsapp.model.sync;

import static it.auties.protobuf.base.ProtobufType.BYTES;

import it.auties.protobuf.base.ProtobufMessage;
import it.auties.protobuf.base.ProtobufProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Data
@Builder
@Jacksonized
@Accessors(fluent = true)
public class AppStateSyncKeyId
    implements ProtobufMessage {

  @ProtobufProperty(index = 1, type = BYTES)
  private byte[] keyId;
}
