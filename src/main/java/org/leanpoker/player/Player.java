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
//        bet = zeroRoundPairBet();
//        if (bet == 0) bet = zeroRoundSameColorBet();
//        if (bet == 0) bet = zeroRoundHighCardBet();
//        return bet;
//    }
//
//    private int zeroRoundPairBet(){
//        int bet = 0;
//        if (firstCardrank.equals(secondCardrank)){
//            if (stack < 10*minimal_raise){
//                bet = stack;
//            }
//            else{
//                bet = 10 * minimal_raise;
//            }
//        }
//        return bet;
//    }
//
//    private int zeroRoundSameColorBet(){
//        int bet = 0;
//        if (firstCardSuit.equals(secondCardSuite)){
//            bet = raiseByPercent(10);
//        }
//        return bet;
//    }
//
//    private int zeroRoundHighCardBet(){
//        int bet = 0;
//        if(firstCardRank > 10 || secondCardRank > 10){
//            bet = raiseByPercent(10);
//        }
//        if(firstCardRank > 12 || secondCardRank > 10){
//            bet = raiseByPercent(15);
//        }
//        return bet;
//    }
//
//    private int raiseByPercent(int percent){
//        int bet = 0;
//        if (minimal_raise<stack/percent) bet = stack / percent;
//        return bet;
//    }

    public static void showdown(JsonElement game) {
    }
}
