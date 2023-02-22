package com.muchware.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.awt.*;

public class SetInformation extends ListenerAdapter{
    public static final String iconUrl ="";
    public void onReady(@NotNull ReadyEvent e)
    {
       try{
           long guildID = 1046750078838255627L;
           long channelID = 1046750080243355684L;
           e.getJDA().getGuildById(guildID).getTextChannelById(channelID).sendMessage("test").queue();
       } catch (Exception ex) {
           System.out.println("WRONG SERVER"+"Error: " + ex.getMessage());
       }
    }
    public MessageEmbed buildEmbed(){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Welcome to the Muchware Discord Server!");
        builder.setAuthor("Muchware", null, iconUrl);
        builder.setThumbnail(iconUrl);
        builder.setColor(Color.RED);
        builder.setDescription("Here, we provide support and assistance for our Minecraft Mods & Modpacks, Discord Bots and our Web development services.\n"
                +
                "Our team of experienced developers and moderators are dedicated to providing prompt and efficient assistance to any inquiries or issues you may have. Whether you're having trouble installing a mod or need help with web development, we're here to help you every step of the way.\n"
                +
                "We also offer a range of resources, including tutorials, guides, and FAQs to help you get started with our services.");
        builder.addField("Our Website", "https://muchware.com", false);


        return builder.build();
    }
}
