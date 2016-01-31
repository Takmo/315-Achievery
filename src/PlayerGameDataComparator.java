package com.bitwisehero.course315.achievery;

import java.util.Comparator;

class PlayerGameDataComparator implements Comparator<PlayerGameData> {
    public int compare(PlayerGameData left, PlayerGameData right) {
        return right.getGamerscore() - left.getGamerscore();
    }
}
