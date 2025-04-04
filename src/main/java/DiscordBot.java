import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.EnumSet;




public class DiscordBot {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");
        JDA jdaBuilder = JDABuilder.createLight(token,  EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT))
                .addEventListeners( new Comandos(), new OnMessage())
                .setActivity(Activity.playing("Java Edition"))
                .build();
        CommandListUpdateAction commands = jdaBuilder.updateCommands();
        commands.addCommands(
                Commands.slash("analisar", "analisar usuario e descobrir se ele e desenrolado")
                        .addOption(OptionType.USER, "user", "usuario a ser analisado")
        ).queue();
    }

}

