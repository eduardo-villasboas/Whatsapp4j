package it.auties.whatsapp.model.sync;

import static it.auties.protobuf.base.ProtobufType.BYTES;
import static it.auties.protobuf.base.ProtobufType.INT64;
import static it.auties.protobuf.base.ProtobufType.MESSAGE;

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
public class AppStateSyncKeyData
    implements ProtobufMessage {

  @ProtobufProperty(index = 1, type = BYTES)
  private byte[] keyData;

  @ProtobufProperty(index = 2, type = MESSAGE, implementation = AppStateSyncKeyFingerprint.class)
  private AppStateSyncKeyFingerprint fingerprint;

  @ProtobufProperty(index = 3, type = INT64)
  private Long timestamp;
}
