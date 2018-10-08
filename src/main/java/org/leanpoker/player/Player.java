package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Player {

    static final String VERSION = "First round handling player";

    int round;
    int currentBuyIn;
    int currentBet;
    int minimumRaise;
    int stack;
    List<Card> holeCards = new ArrayList<>();
    List<Card> communityCards = new ArrayList<>();

    public static int betRequest(JsonElement request) {
        JsonObject game = request.getAsJsonObject();
        Player player = new Player();
        player.setGameStatus(game);



        return player.zeroRoundBehaviour(game);
    }

    private void setGameStatus(JsonObject game) {
        round = game.get("round").getAsInt();
        currentBuyIn = game.get("current_buy_in").getAsInt();
        minimumRaise = game.get("minimum_raise").getAsInt();
        currentBet = getCurrentBet(game);
        stack = getStack(game);
        holeCards = getHoleCards(game);
        communityCards = getCommunityCards(game);
    }

    private int getCurrentBet(JsonObject game) {
        JsonArray players = game.getAsJsonArray("players");
        JsonObject ourPlayer = getOurPlayer(players);

        return ourPlayer.get("bet").getAsInt();
    }

    private List<Card> getCommunityCards(JsonObject game) {
        List<Card> communityCards = new ArrayList<>();
        JsonArray jsonCommunityCards = game.get("community_cards").getAsJsonArray();

        for (JsonElement jsonCard: jsonCommunityCards) {
            Card card = new Card();
            card.setRank(jsonCard.getAsJsonObject().get("rank").getAsString());
            card.setSuit(jsonCard.getAsJsonObject().get("suit").getAsString());
            communityCards.add(card);
        }
        return communityCards;
    }

    private List<Card> getHoleCards(JsonObject game) {
        List<Card> holeCards = new ArrayList<>();
        JsonArray players = game.getAsJsonArray("players");
        JsonObject ourPlayer = getOurPlayer(players);
        JsonArray jsonHoleCards = ourPlayer.get("hole_cards").getAsJsonArray();

        for (JsonElement jsonCard: jsonHoleCards) {
            Card card = new Card();
            card.setRank(jsonCard.getAsJsonObject().get("rank").getAsString());
            card.setSuit(jsonCard.getAsJsonObject().get("suit").getAsString());
            holeCards.add(card);
        }

        return holeCards;
    }

    private JsonObject getOurPlayer(JsonArray players) {
        for (JsonElement player: players) {
            JsonObject currentPlayer = player.getAsJsonObject();
            String playerName = currentPlayer.get("name").getAsString();

            if (playerName.equals("NO democracy")) {
                return currentPlayer;
            }
        }
        return new JsonObject();
    }

    private int getStack(JsonObject game) {
        JsonArray players = game.getAsJsonArray("players");
        JsonObject ourPlayer = getOurPlayer(players);

        return ourPlayer.getAsJsonObject().get("stack").getAsInt();
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
        if (holeCards.get(0).getRank().equals(holeCards.get(1).getRank())){
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
        if (holeCards.get(0).getSuit().equals(holeCards.get(1).getSuit())){
            bet = raiseByPercent(10);
        }
        return bet;
    }

    private int zeroRoundHighCardBet(){
        int bet = 0;
        if(holeCards.get(0).getRank() > 10 || holeCards.get(1).getRank() > 10){
            bet = raiseByPercent(10);
        }
        if(holeCards.get(0).getRank() > 12 || holeCards.get(1).getRank() > 10){
            bet = raiseByPercent(15);
        }
        return bet;
    }

    private int raiseByPercent(int percent){
        int bet = 0;
        if (minimumRaise<stack/percent*100) bet = stack / percent * 100;
        return bet;
    }

    public static void showdown(JsonElement game) {
    }
}
