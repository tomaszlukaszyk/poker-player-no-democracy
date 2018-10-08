package org.leanpoker.player;

import java.util.HashMap;
import java.util.Map;

public class Card implements Comparable<Card> {

    private String suit;
    private String rank;

    private Map<String, Integer> ranks = new HashMap<>();

    public Card() {
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

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Integer getRank() {
        return ranks.get(rank);
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Card o) {
        return this.getRank().compareTo(o.getRank());
    }
}
