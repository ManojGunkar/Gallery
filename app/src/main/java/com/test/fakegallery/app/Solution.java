package com.test.fakegallery.app;

import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    private HashMap<Integer, ArrayList<Integer>> map;
    private int capital = 0;
    private int noOfCities = 0;

    Solution() {
        map = new HashMap<>();
    }

    int[] solution(int[] T) {
        int dist[] = new int[T.length - 1];
        capital = findCapital(T);
        convertToMap(T);
        for (int i = 0; i < T.length - 1; i++) {
            noOfCities = 0;
            dist[i] = findNoOfCities(capital, i + 1, 1);
        }
        return dist;
    }

    private int findNoOfCities(int fromCity, int maxDist, int dist) {
        if (dist <= maxDist) {
            ArrayList<Integer> list = map.get(fromCity);
            if (list != null) {
                for (int i : list) {
                    findNoOfCities(i, maxDist, dist + 1);
                }
                if (maxDist == dist)
                noOfCities += list.size();
            }
        }
        return noOfCities;
    }

    private int findCapital(int[] T) {
        for (int i = 0; i < T.length; i++) {
            if (i == T[i]) return i;
        }
        return 0;
    }

    private void convertToMap(int[] T) {
        for (int i = 0; i < T.length; i++) {
            if (capital == i) continue;
            ArrayList<Integer> list = map.get(T[i]);
            if (list == null) list = new ArrayList<>();
            list.add(i);
            map.put(T[i], list);
        }
    }
}
