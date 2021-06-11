package org.example.whatsapp.command;

import it.auties.whatsapp4j.api.WhatsappAPI;
import it.auties.whatsapp4j.protobuf.chat.Chat;
import it.auties.whatsapp4j.protobuf.info.MessageInfo;
import it.auties.whatsapp4j.protobuf.message.TextMessage;

import java.util.Set;

public class HelloCommand implements Command{
    @Override
    public void onCommand(WhatsappAPI api, Chat chat, MessageInfo message) {
        api.sendMessage(chat, new TextMessage("Hello :)"), message);
    }

    @Override
    public String command() {
        return "/hello";
    }

    @Override
    public Set<String> aliases() {
        return Set.of("/hi", "/morning");
    }
}
