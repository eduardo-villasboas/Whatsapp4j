package it.auties.whatsapp.model.sync;

import static it.auties.protobuf.base.ProtobufType.UINT32;

import it.auties.protobuf.base.ProtobufMessage;
import it.auties.protobuf.base.ProtobufProperty;
import java.util.ArrayList;
import java.util.List;
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
public class AppStateSyncKeyFingerprint
    implements ProtobufMessage {

  @ProtobufProperty(index = 1, type = UINT32)
  private Integer rawId;

  @ProtobufProperty(index = 2, type = UINT32)
  private Integer currentIndex;

  @ProtobufProperty(index = 3, type = UINT32, repeated = true, packed = true)
  private List<Integer> deviceIndexes;

  public static class AppStateSyncKeyFingerprintBuilder {

    public AppStateSyncKeyFingerprintBuilder deviceIndexes(List<Integer> deviceIndexes) {
      if (this.deviceIndexes == null) {
        this.deviceIndexes = new ArrayList<>();
      }
      this.deviceIndexes.addAll(deviceIndexes);
      return this;
    }
  }
}