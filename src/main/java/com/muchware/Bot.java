package com.muchware;

import com.muchware.events.SetInformation;
import com.muchware.events.WelcomeRole;
import com.muchware.manager.CommandHandler;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Bot {

    public static Bot INSTANCE;
    public ShardManager shardManager;
    private final CommandHandler cmdHandler;
    private Thread ActivityThread;
    private final Dotenv config;

    public static void main(String[] args) {
       try {
          new Bot();
       } catch (LoginException | IllegalArgumentException e) {
           e.printStackTrace();
       }
    }

    public Bot() throws LoginException, IllegalArgumentException {
        INSTANCE = this;

        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);

        builder.setActivity(Activity.watching("muchware.com"));
        builder.setStatus(OnlineStatus.ONLINE);

        this.cmdHandler = new CommandHandler();
        shardManager = builder.build();
        shardManager.addEventListener(
                new CommandHandler(),
                new SetInformation(),
                new WelcomeRole()
        );


        shutdown();
        runActivity();

        System.out.println("Bot is ready!");
    }

    public void shutdown(){
        new Thread(() ->
        {
            String input = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while ((input = reader.readLine()) != null) {
                    if (input.equalsIgnoreCase("exit"))
                    {
                        System.out.println("Shutting down...");
                        shutdown = true;
                        if (shardManager != null)
                        {
                            shardManager.setStatus(OnlineStatus.OFFLINE);
                            shardManager.shutdown();
                            System.out.println("Bot is offline.");
                        }
                        if (ActivityThread != null)
                        {
                            ActivityThread.interrupt();
                            System.out.println("Loop Thread has been terminated.");
                        }
                        reader.close();
                        break;
                    }
                    else System.out.println("Please type 'exit' to shutdown the bot.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public boolean shutdown = false;
    public void runActivity() {
        this.ActivityThread = new Thread(() -> {

            long time = System.currentTimeMillis();
            while (!shutdown) {
                if (System.currentTimeMillis() >= time + 1000) {
                    time = System.currentTimeMillis();
                    //System.out.println("Bot is running...");
                    onSecond();
                }
            }
        });
        this.ActivityThread.setName("Activity Thread");
        this.ActivityThread.start();
    }
    String[] status = new String[]{"github.com/muchware","muchware.com","Muchcraft"};
    int interval = 30;
    public void onSecond() {
      if(interval<=0){
          Random r = new Random();
          int next = r.nextInt(status.length);
          shardManager
                  .getShards()
                  .forEach(jda -> jda.getPresence()
                          .setActivity(Activity.watching(status[next])));
          interval = 30;
      }
      else interval--;
    }
    public CommandHandler getCmdHandler() {return cmdHandler;}
}