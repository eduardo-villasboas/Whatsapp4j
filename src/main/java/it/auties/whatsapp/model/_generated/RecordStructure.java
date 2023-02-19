package it.auties.whatsapp.model._generated;

import it.auties.protobuf.base.ProtobufMessage;
import it.auties.protobuf.base.ProtobufName;
import it.auties.protobuf.base.ProtobufProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

import static it.auties.protobuf.base.ProtobufType.MESSAGE;

@AllArgsConstructor
@Data
@Jacksonized
@Builder
@ProtobufName("RecordStructure")
public class RecordStructure implements ProtobufMessage {
    @ProtobufProperty(index = 1, name = "currentSession", type = MESSAGE)
    private SessionStructure currentSession;

    @ProtobufProperty(implementation = SessionStructure.class, index = 2, name = "previousSessions", repeated = true, type = MESSAGE)
    private List<SessionStructure> previousSessions;
}