package com.muchware.manager;

import java.util.concurrent.ConcurrentHashMap;
import com.muchware.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class CommandHandler {

    public ConcurrentHashMap<String, ServerCommand> commands;
    public CommandHandler() {
        this.commands = new ConcurrentHashMap<>();

    }

    public boolean perform(String command, Member m, TextChannel channel, Message message) {
        ServerCommand cmd;
        if((cmd = this.commands.get(command.toLowerCase())) != null) {
            cmd.performCommand(m, channel, message);
            return true;
        }
        return false;
    }
}
