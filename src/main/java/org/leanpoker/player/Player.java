package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        JsonObject game = request.getAsJsonObject();
        int minimumRaise = game.get("minimum_raise").getAsInt();
        System.out.println(minimumRaise);

        JsonArray players = game.getAsJsonArray("players");

        players.forEach(player -> {
            JsonObject currentPlayer = player.getAsJsonObject();
            String playerName = currentPlayer.get("name").getAsString();

            if (playerName.equals("NO democracy")) {
                System.out.println(playerName);
            }
        });

        return 999;
    }

//    private int zeroRoundBehaviour(){
//        int bet = 0;
//        if (firstCardrank.equals(secondCardrank)){
//            if (stack < 10*minimal_raise){
//                bet = stack;
//            }
//            else{
//                bet = 10 * minimal_raise;
//            }
//        }
//        if (firstCardSuit.equals(secondCardSuite)){
//            if (minimal_raise<stack/10) bet = stack / 10;
//            else bet = 0;
//        }
//        return bet;
//    }

    public static void showdown(JsonElement game) {
    }
}
