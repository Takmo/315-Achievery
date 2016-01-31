package com.bitwisehero.course315.achievery;

import java.util.Comparator;

// PlayerGameDataComparator - allows a Collection of PlayerGameDatas
// to be sorted in decreasing order based on their contribution to
// total gamerscore.

class PlayerGameDataComparator implements Comparator<PlayerGameData> {
    public int compare(PlayerGameData left, PlayerGameData right) {
        return right.getGamerscore() - left.getGamerscore();
    }
}
