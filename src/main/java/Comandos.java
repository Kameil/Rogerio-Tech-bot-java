import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.List;

public class Comandos extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("analisar")) {
            if (event.isFromGuild()){
                event.deferReply().queue();
                Guild guild = event.getGuild();
                User cabaASerAnalisado = event.getOption("user").getAsUser();
                List<String> mensagensDoCabaAi = new ArrayList<>();
                assert guild != null;
                for(GuildChannel channel : guild.getTextChannels()){
                    TextChannel textChannel = (TextChannel) channel;
                    System.out.println("sera se tem permissao em");
                    if (guild.getSelfMember().hasPermission(textChannel, Permission.MESSAGE_HISTORY)) {
                        System.out.println("e nao e que tem em");
                        textChannel.getHistory().retrievePast(100).queue(messages -> {
                            System.out.println("imagina se");
                            for(Message message : messages) {
                                if (message.getAuthor().getId().equals(cabaASerAnalisado.getId())) {
                                    System.out.println("imagina ai");

                                    mensagensDoCabaAi.add(String.format("Mensagem de %s em #%s canal: %s",
                                            message.getAuthor().getName(),
                                            message.getChannel().getName(),
                                            message.getContentRaw()));
                                }
                            }
                            event.getHook().sendMessage(String.format("li todas as mensagens do caba ai mas nao fiz nd pq nao tem api ne\n mensagens lidas: %d", mensagensDoCabaAi.toArray().length)).queue();
                        });
                    }

                }
            }else{
                event.reply("mancho nao da pra fazer isso em Dm").queue();
            }
        }
    }
}
