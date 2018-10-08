package org.leanpoker.player;

import java.util.HashMap;
import java.util.Map;

public class HoleCards {
    String firstCardRank;
    String secondCardRank;
    String firstCardSuit;
    String secondCardSuit;

    Map<String, Integer> ranks = new HashMap<>();

    public HoleCards() {
        ranks.put("1", 1);
        ranks.put("2", 2);
        ranks.put("3", 3);
        ranks.put("4", 4);
        ranks.put("5", 5);
        ranks.put("6", 6);
        ranks.put("7", 7);
        ranks.put("8", 8);
        ranks.put("9", 9);
        ranks.put("10", 10);
        ranks.put("J", 11);
        ranks.put("Q", 12);
        ranks.put("K", 13);
        ranks.put("A", 14);
    }

    public Integer getFirstCardRank() {
        return ranks.get(firstCardRank);
    }

    public void setFirstCardRank(String firstCardRank) {
        this.firstCardRank = firstCardRank;
    }

    public Integer getSecondCardRank() {
        return ranks.get(secondCardRank);
    }

    public void setSecondCardRank(String secondCardRank) {
        this.secondCardRank = secondCardRank;
    }

    public String getFirstCardSuit() {
        return firstCardSuit;
    }

    public void setFirstCardSuit(String firstCardSuit) {
        this.firstCardSuit = firstCardSuit;
    }

    public String getSecondCardSuit() {
        return secondCardSuit;
    }

    public void setSecondCardSuit(String secondCardSuit) {
        this.secondCardSuit = secondCardSuit;
    }
}
