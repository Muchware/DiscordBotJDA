package com.muchware.events;

import com.muchware.objects.EmbedMessage;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SetInformation extends ListenerAdapter{
    public void onReady(@NotNull ReadyEvent e)
    {
       try
       {
           long guildID = 1046750078838255627L;
           long channelID = 1046750080243355684L;
           e.getJDA()
                   .getGuildById(guildID)
                   .getTextChannelById(channelID)
                   .sendMessageEmbeds
                   (
                           EmbedMessage.Rules(),
                           EmbedMessage.buildEmbed(),
                            EmbedMessage.VisitHere()
                   ).queue();
       } catch (Exception ex) {System.out.println("WRONG SERVER "+"Error: " + ex.getMessage());}
    }

}