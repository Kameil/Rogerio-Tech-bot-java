import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnMessage extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String botMention = "<@" + event.getJDA().getSelfUser().getId() + ">";
        if (!event.getMessage().getAuthor().isBot()){
            if(event.getMessage().getContentRaw().contains(botMention)){
                System.out.println("Mencionaram o bot ai");
                event.getChannel().sendMessage("Calma calabreso, Nao e assim tao facil").queue();
            }
        }
    }
}
