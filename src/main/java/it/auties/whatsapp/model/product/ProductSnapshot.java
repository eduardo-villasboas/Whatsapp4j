package it.auties.whatsapp.model.product;

import static it.auties.protobuf.base.ProtobufType.INT64;
import static it.auties.protobuf.base.ProtobufType.MESSAGE;
import static it.auties.protobuf.base.ProtobufType.STRING;
import static it.auties.protobuf.base.ProtobufType.UINT32;

import it.auties.protobuf.base.ProtobufMessage;
import it.auties.protobuf.base.ProtobufProperty;
import it.auties.whatsapp.model.message.standard.ImageMessage;
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
public class ProductSnapshot
    implements ProtobufMessage {

  @ProtobufProperty(index = 1, type = MESSAGE, implementation = ImageMessage.class)
  private ImageMessage productImage;

  @ProtobufProperty(index = 2, type = STRING)
  private String productId;

  @ProtobufProperty(index = 3, type = STRING)
  private String title;

  @ProtobufProperty(index = 4, type = STRING)
  private String description;

  @ProtobufProperty(index = 5, type = STRING)
  private String currencyCode;

  @ProtobufProperty(index = 6, type = INT64)
  private long priceAmount1000;

  @ProtobufProperty(index = 7, type = STRING)
  private String retailerId;

  @ProtobufProperty(index = 8, type = STRING)
  private String url;

  @ProtobufProperty(index = 9, type = UINT32)
  private Integer productImageCount;

  @ProtobufProperty(index = 11, type = STRING)
  private String firstImageId;

  @ProtobufProperty(index = 12, type = INT64)
  private long salePriceAmount1000;
}
