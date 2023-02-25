package com.muchware.manager;

import com.muchware.objects.EmbedMessage;
import com.muchware.objects.MessagePurgeList;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandHandler extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        switch (command) {
            case "much" -> {
                event.reply("much?")
                        .queue();
                event.getHook()
                        .editOriginal("SO MUCH DIT")
                        .queue();
            }
            case "embed" -> {
                //try to remove command origin
                if(event.getMember().hasPermission(Permission.ADMINISTRATOR))
                {
                    event.replyEmbeds
                                    (
                                            EmbedMessage.Rules(),
                                            EmbedMessage.buildEmbed(),
                                            EmbedMessage.VisitHere()
                                    )
                            .queue();
                }else event.reply("You don't have permission to do that!")
                        .setEphemeral(true)
                        .queue();
            }
            case "clear" -> {
                if(!Objects.requireNonNull(event.getMember())
                        .hasPermission(Permission.MANAGE_CHANNEL))
                {
                    event.reply("You don't have permission to do that!")
                            .setEphemeral(true)
                            .queue();
                }
                else{
                        event.getChannel()
                                .purgeMessages(
                                        MessagePurgeList
                                                .get(event.getChannel(),
                                                        event.getOption("amount")
                                                                .getAsInt()));
                        event.reply("Channel cleared!")
                                .setEphemeral(true)
                                .complete();
                }
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
        commands.add(Commands.slash("clear", "Clears x Messages in this channel")
                .addOption(OptionType.INTEGER, "amount", "The amount of messages to clear", true));

        event.getJDA().updateCommands().addCommands(commands).queue();
    }
}