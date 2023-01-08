package it.auties.whatsapp.model.business;

import static it.auties.protobuf.base.ProtobufType.INT32;
import static it.auties.protobuf.base.ProtobufType.MESSAGE;
import static it.auties.protobuf.base.ProtobufType.STRING;

import it.auties.protobuf.base.ProtobufMessage;
import it.auties.protobuf.base.ProtobufProperty;
import it.auties.whatsapp.model.button.NativeFlowButton;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

/**
 * A model class that represents a native flow
 */
@AllArgsConstructor(staticName = "of")
@Data
@Builder
@Jacksonized
@Accessors(fluent = true)
public final class BusinessNativeFlow
    implements ProtobufMessage {

  /**
   * The buttons of this flow
   */
  @ProtobufProperty(index = 1, type = MESSAGE, implementation = NativeFlowButton.class, repeated = true)
  private List<NativeFlowButton> buttons;

  /**
   * The parameters of this flow as json
   */
  @ProtobufProperty(index = 2, type = STRING)
  private String parameters;

  /**
   * The version of this flow
   */
  @ProtobufProperty(index = 3, type = INT32)
  private int version;

  public static class BusinessNativeFlowBuilder {

    public BusinessNativeFlowBuilder buttons(List<NativeFlowButton> buttons) {
      if (this.buttons == null) {
        this.buttons = new ArrayList<>();
      }
      this.buttons.addAll(buttons);
      return this;
    }
  }
}
