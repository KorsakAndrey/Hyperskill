package advisor;

import advisor.commandsClass.Categories;
import advisor.commandsClass.Featured;
import advisor.commandsClass.New;
import advisor.commandsClass.Playlists;

import java.util.HashMap;

public class Commands {
    private static final HashMap<String,String> playlistCategories = new HashMap<>();
    private static boolean showCat = true;

    public static void answer(String command, String secondCommand) {
        switch (command) {
            case "new": {
                View.show(new New());
                break;
            }
            case "featured": {
                View.show(new Featured());
                break;
            }
            case "categories": {
                View.show(new Categories());
                break;
            }
            case "playlists": {
               View.show(new Playlists());
                break;
            }
            case "next": {
                View.showNext();
                break;
            }
            case "prev": {
                View.showPrev();
                break;
            }
            case "exit": {
                System.out.println("---GOODBYE!---");
                break;
            }
            case "auth": {
                System.out.println("Success!");
                break;
            }
        }
    }
}
