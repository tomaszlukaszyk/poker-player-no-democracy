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

    public static void showdown(JsonElement game) {
    }
}
