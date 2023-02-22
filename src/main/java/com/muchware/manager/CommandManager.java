package com.muchware.manager;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import java.util.HashSet;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
public class CommandManager {
    public static class Command {

        private final String name;
        private final String description;
        private final HashSet<OptionData> options = new HashSet<>();
        private final HashSet<Command> subCommands = new HashSet<>();
        private CommandExecutor executor;

        public Command(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public void execute(CommandExecutor executor) {
            if (!subCommands.isEmpty() || !options.isEmpty()) {
                throw new IllegalStateException("You can't add an executor to a command with options or subcommands!");
            }
            this.executor = executor;
        }

        public void subCommand(String name, String description, CommandExecutor executor) {
            if (executor != null || !options.isEmpty()) {
                throw new IllegalStateException("You can't add a subcommand to a command with options or an executor!");
            }
            subCommands.add(new Command(name, description).setExecutor(executor));
        }

        public void option(String name, OptionType type, String description) {
            if (!subCommands.isEmpty()) {
                throw new IllegalStateException("You can't add an option to a command with subcommands!");
            }
            options.add(new OptionData(type, name, description));
        }

        public String getName() { return name;}
        public String getDescription() {return description;}
        public HashSet<OptionData> getOptions() {return options;}
        public HashSet<Command> getSubCommands() {return subCommands;}
        public CommandExecutor getExecutor() {return executor;}
        public Command setExecutor(CommandExecutor executor) {this.executor = executor; return this;}
        public interface CommandExecutor {void execute(SlashCommandInteractionEvent event);}
        public static Command command(String name, String description, CommandExecutor executor) {
            Command command = new Command(name, description);
            command.setExecutor(executor);
            return command;
        }
    }
}
