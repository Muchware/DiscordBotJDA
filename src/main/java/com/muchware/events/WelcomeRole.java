package com.muchware.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WelcomeRole extends ListenerAdapter {
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent e)
    {
        try
        {
            long guildID = 1046750078838255627L;
            long roleID = 1046750078838255630L;

            e.getJDA()
                    .getGuildById(guildID)
                    .addRoleToMember(e.getMember(), e.getGuild().getRoleById(roleID))
                    .queue();

            System.out.println("Role added to " + e.getMember().getEffectiveName());

        } catch (Exception ex) {System.out.println("WRONG SERVER"+"Error: " + ex.getMessage());}
    }
}