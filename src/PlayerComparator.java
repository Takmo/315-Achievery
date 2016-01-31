package com.bitwisehero.course315.achievery;

import java.util.Comparator;

class PlayerComparator implements Comparator<Player> {
    public int compare(Player left, Player right) {
        return right.getTotalPoints() - left.getTotalPoints();
    }
}
