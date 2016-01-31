package com.bitwisehero.course315.achievery;

import java.util.Comparator;

// AchievementComparator - allows a Collection of Achievements
// to be sorted in decreasing order based on points on unlock.

class AchievementComparator implements Comparator<Achievement> {
    public int compare(Achievement left, Achievement right) {
        return right.getPoints() - left.getPoints();
    }
}
