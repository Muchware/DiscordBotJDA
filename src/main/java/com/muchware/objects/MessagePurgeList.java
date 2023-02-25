package com.muchware.objects;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.ArrayList;
import java.util.List;

public class MessagePurgeList {
   public static List<Message> get(MessageChannel channel, int amount) {
      List<Message> messages = new ArrayList<>();
      int i = amount + 1;
      for (Message message : channel.getIterableHistory().cache(false))
      {
         if (!message.isPinned()) {
            messages.add(message);
         }
         if(--i <= 0) break;
      }
      return messages;
   }
}