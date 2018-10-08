package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "First round handling player";

    int minimumRaise;
    int stack;
    HoleCards holeCards;

    public static int betRequest(JsonElement request) {
        JsonObject game = request.getAsJsonObject();
        Player player = new Player();
        player.setGameStatus(game);



        return player.zeroRoundBehaviour(game);
    }

    private int getMinmumRaise(JsonObject game) {
        return game.get("minimum_raise").getAsInt();
    }

    private HoleCards getHoleCards(JsonObject game) {
        HoleCards holeCards = new HoleCards();
        JsonArray players = game.getAsJsonArray("players");

        for (JsonElement player: players) {
            JsonObject currentPlayer = player.getAsJsonObject();
            String playerName = currentPlayer.get("name").getAsString();

            if (playerName.equals("NO democracy")) {
                System.out.println(playerName);

                JsonArray holeCardsJson = currentPlayer.get("hole_cards").getAsJsonArray();
                holeCards.setFirstCardRank(holeCardsJson.get(0).getAsJsonObject().get("rank").getAsString());
                holeCards.setSecondCardRank(holeCardsJson.get(1).getAsJsonObject().get("rank").getAsString());
                holeCards.setFirstCardSuit(holeCardsJson.get(0).getAsJsonObject().get("suit").getAsString());
                holeCards.setSecondCardSuit(holeCardsJson.get(1).getAsJsonObject().get("suit").getAsString());

            }
        }
        return holeCards;
    }

    private void setGameStatus(JsonObject game) {
        minimumRaise = getMinmumRaise(game);
        stack = getStack(game);
        holeCards = getHoleCards(game);
    }

    private int getStack(JsonObject game) {
        int stack = 0;
        JsonArray players = game.getAsJsonArray("players");

        for (JsonElement player: players) {
            JsonObject currentPlayer = player.getAsJsonObject();
            String playerName = currentPlayer.get("name").getAsString();

            if (playerName.equals("NO democracy")) {
                System.out.println(playerName);

                stack = currentPlayer.getAsJsonObject().get("stack").getAsInt();

            }
        }
        return stack;
    }

    private int zeroRoundBehaviour(JsonObject game){
        int bet = 0;
        bet = zeroRoundPairBet();
        if (bet == 0) bet = zeroRoundSameColorBet();
        if (bet == 0) bet = zeroRoundHighCardBet();
        return bet;
    }

    private int zeroRoundPairBet(){
        int bet = 0;
        if (holeCards.getFirstCardRank().equals(holeCards.getSecondCardRank())){
            if (stack < 10*minimumRaise){
                bet = stack;
            }
            else{
                bet = 10 * minimumRaise;
            }
        }
        return bet;
    }

    private int zeroRoundSameColorBet(){
        int bet = 0;
        if (holeCards.getFirstCardSuit().equals(holeCards.getSecondCardSuit())){
            bet = raiseByPercent(10);
        }
        return bet;
    }

    private int zeroRoundHighCardBet(){
        int bet = 0;
        if(holeCards.getFirstCardRank() > 10 || holeCards.getSecondCardRank() > 10){
            bet = raiseByPercent(10);
        }
        if(holeCards.getFirstCardRank() > 12 || holeCards.getSecondCardRank() > 10){
            bet = raiseByPercent(15);
        }
        return bet;
    }

    private int raiseByPercent(int percent){
        int bet = 0;
        if (minimumRaise<stack/percent) bet = stack / percent;
        return bet;
    }

    public static void showdown(JsonElement game) {
    }
}
