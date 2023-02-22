package com.muchware.objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Embed_a_Message{
       public static MessageEmbed buildEmbed(){
           final String iconUrl ="https://avatars.githubusercontent.com/u/122747145?s=96&v=4";
           final String muchwareUrl = "https://muchware.com";
           final String muchwareSpotify = "";
           final String[] Mod =
                   {
                           "https://curseforge.com/minecraft/modpacks/muchcraft",
                           "https://www.curseforge.com/minecraft/modpacks/muchcraft-through-the-ages",
                           "https://www.curseforge.com/minecraft/mods/muchcraft"
                   };
           final String description =
                           "Here, we provide support and assistance for our Minecraft Mods & Modpacks, Discord Bots and our Web development services.\n"
                                   +"\n"+
                           "Our team of experienced developers and moderators are dedicated to providing prompt and efficient assistance to any inquiries or issues you may have. Whether you're having trouble installing a mod or need help with web development, we're here to help you every step of the way.\n"
                                   +"\n"+
                           "We also offer a range of resources, including tutorials, guides, and FAQs to help you get started with our services.";



           EmbedBuilder builder = new EmbedBuilder();
           builder.setTitle("Welcome to the Muchware Discord Server!");
           builder.setAuthor("Muchware", null, iconUrl);
           builder.setThumbnail(iconUrl);
           builder.setColor(Color.RED);
           builder.setDescription(description);
           builder.addBlankField(false);
           builder.addField(":globe_with_meridians: Muchware Website", "\t[Muchware](%s)".formatted(muchwareUrl), false);
           builder.addField(":headphones: Muchware Music","\t[Muchware Spotify](%s)".formatted(muchwareSpotify), false);
           builder.addField(":link: Muchcraft Adventures","%-1s[Modpack - Muchcraft Adventures](%s)".formatted(Mod[0]), false);
           builder.addField(":link: Muchcraft through the Ages ","\tStill in Progress"/*.formatted(Mod[1])*/, false);
           builder.addField(":link: Muchcraft","\tStill in Progress"/*.formatted(Mod[2])*/, false);

           return builder.build();

       }
       public static MessageEmbed Rules()
       {
           EmbedBuilder rules = new EmbedBuilder();
           rules.setTitle("Muchware Discord Rules!");
           rules.setColor(Color.RED);
           rules.setDescription("We have a small but strict set of rules on our server. Please read over them and take them on board. \n"); /*If you don't understand a rule or need to report an incident, please send a direct message to @ModMail!*/
           rules.addField("Rule 1","Follow the [Discord Community Guidelines](https://discordapp.com/guidelines) and [Terms of Service](https://discordapp.com/terms).", false);
           rules.addField("Rule 2","Respect staff members and listen to their instructions.", false);
           rules.addField("Rule 3","Use English to the best of your ability. Be polite if someone speaks English imperfectly.", false);
           rules.addField("Rule 4","Do not provide or request help on projects that may break laws, breach terms of services, or are malicious or inappropriate.", false);
           rules.addField("Rule 5","Do not spam, advertise, or promote your own or other servers, products, or services.", false);
           rules.addField("Rule 6","Keep discussions relevant to the channel topic. Each channel's description tells you the topic.", false);
           rules.addField("Rule 7","Do not post or share content that is NSFW, offensive, or inappropriate.", false);
           rules.addField("Rule 8","Do not offer or ask for paid work of any kind.", false);
           rules.addBlankField(false);
           rules.addField("Name & Profile Policy","In order to keep things pleasant and workable for both users and staff members, we enforce the following requirements regarding your name, avatar, and profile. Staff reserve the right to change any nickname we judge to be violating these requirements.", false);
           rules.addField("","We also reserve the right to enforce compliance of hateful or otherwise inappropriate usernames and profiles regardless of the server-specific nickname or profile.", false);
           rules.addField("1.","Your name must not be offensive, hateful, or otherwise inappropriate.", false);
           rules.addField("2.","Your name must not be impersonating another user.", false);
           rules.addField("3.","No noisy unicode characters (for example m̶̦̘̀u̶̮͂ĉ̶̖ͮh̷͎̾) or rapidly flashing avatars..", false);

           return rules.build();
       }
}
