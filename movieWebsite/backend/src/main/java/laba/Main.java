package laba;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> listOfGenres = new ArrayList<>();
        listOfGenres.add("Adventure");
        listOfGenres.add("Action");
        listOfGenres.add("Comedy");
        listOfGenres.add("Drama");
        listOfGenres.add("Crime");

        List<String> listFromRequest = new ArrayList<>();
        listFromRequest.add("Adventure");
        listFromRequest.add("Drama");

        int count = 0;
            for (int j = 0; j < listFromRequest.size(); j++) {
                if(listOfGenres.contains(listFromRequest.get(j))){
                    count++;
                }
            }

        System.out.println(count);

    }
}
