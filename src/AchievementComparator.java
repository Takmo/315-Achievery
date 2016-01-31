package com.bitwisehero.course315.achievery;

import java.util.Comparator;

class AchievementComparator implements Comparator<Achievement> {
    public int compare(Achievement left, Achievement right) {
        return right.getPoints() - left.getPoints();
    }
}
