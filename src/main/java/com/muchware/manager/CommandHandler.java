package com.muchware.manager;

import com.muchware.objects.Embed_a_Message;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        switch (command) {
            case "much" -> {
                event.reply("much?").queue();
                event.getHook().editOriginal("SO MUCH DIT").queue();
            }
            case "embed" -> {
                //try to remove command origin
                event.replyEmbeds
                        (
                        Embed_a_Message.Rules(),
                        Embed_a_Message.buildEmbed()
                        )
                        .queue();
               // event.replyEmbeds(Embed_a_Message.buildEmbed()).queue();
            }
        }
    }
 //Only FOR DEV
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commands = new ArrayList<>();
        //commands.add(Commands.slash("example", "example Description"));
        event.getGuild().updateCommands().addCommands(commands).queue();
    }
// PUBLIC USE
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commands = new ArrayList<>();
        commands.add(Commands.slash("much", "Sends you a cool message"));
        commands.add(Commands.slash("embed", "Builds the information embed"));

        event.getJDA().updateCommands().addCommands(commands).queue();
    }
}