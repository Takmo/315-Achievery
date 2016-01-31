package com.bitwisehero.course315.achievery;

import java.util.Comparator;

// PlayerComparator - allows a Collection of Players
// to be sorted in decreasing order based on total gamerscore.

class PlayerComparator implements Comparator<Player> {
    public int compare(Player left, Player right) {
        return right.getTotalGamerscore() - left.getTotalGamerscore();
    }
}
