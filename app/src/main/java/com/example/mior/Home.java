package com.example.mior;

import java.util.ArrayList;

public class Home {
    private ArrayList<Game> games; // ArrayList of the notification objects for a given game


    // Constructor
    public Home(String newName, int newRoundLen, int newGameLen){
        games = new ArrayList<Game>();
    }


    // Adds a game object with the given parameters, returns true if succeds,
    // returns false if the given name already exists, can also give a game name as a preset to set the length parameters
    public boolean AddGame(String newName, int newRoundLen, int newGameLen, String preset, int newImage){

        for (int i = 0; i < games.size(); i++){
            if (games.get(i).GetName() == newName){
                return false;
            }
        }

        if (preset == "LoL"){
            newRoundLen = 9999999;  // Has no rounds, so make so big so it doesn't occur
            newGameLen = 2100;  //35 min
        }
        else if (preset == "RainbowSixCasual"){
            newRoundLen = 300; // 4 min + 1 min op select
            newGameLen = 1200;  // 16 min avg from possible 3-5 games
        }
        else if (preset == "RainbowSixRanked"){
            newRoundLen = 240; // 3 min + 45 sec op select + 15 sec load
            newGameLen = 1680;  // 28 min avg from possible 5-9 games
        }
        else if (preset == "Smash"){
            newRoundLen = 9999999;
            newGameLen = 240;  // 4 min avg
        }
        else if (preset == "Valorant"){
            newRoundLen = 150; // 100 sec max + 30 sec op select + load
            newGameLen = 2250;  // 37.5 min avg from possible 10-20~ games
        }
        else if (preset == "ValorantRush"){
            newRoundLen = 120; // 80 sec max + 20 sec op select + load
            newGameLen = 600;  // 10 min avg from possible 4-7 games
        }
        else if (preset == "Fortnite"){
            newRoundLen = 9999999;
            newGameLen = 900;  // 15 min avg
        }
        else if (preset == "Pub"){
            newRoundLen = 9999999;
            newGameLen = 900;  // 15 min avg
        }
        else if (preset == "RocketLeague"){
            newRoundLen = 9999999;
            newGameLen = 420;  // 7 min avg
        }


        Game newGame = new Game(newName, newRoundLen, newGameLen, newImage);

        games.add(newGame);
        // games.add(name);
        return true;
    }


    // Given a name, removes that game from the list
    public void RemoveGame(String name){
        int temp = 0;
        for (int i = 0; i < games.size(); i++){
            // if (games.get(i).name == name)
            if (games.get(i).GetName() == name){
                temp = i;
            }
        }

        games.remove(temp);
    }


    // Getter function
     public ArrayList<Game> Getgames(){
        return games;
    }

    // Returns a string list of all the notification names
    public ArrayList<String> DisplayGames(){
        ArrayList<String> temp = new ArrayList<String>();

        for (int i = 0; i < games.size(); i++){
            temp.add(games.get(i).GetName());
        }

        return temp;
    }


    // Given a game name, returns a reference to that game
    public Game GetGameReference(String name){
        for (int i = 0; i < games.size(); i++){
            if (games.get(i).GetName() == name){
                return games.get(i);
            }
        }

        // Should never get here
        return new Game("", 0, 0, 0);
    }


    // Given a game name, returns the index of that game
    public int GetGameIndex(String name){
        for (int i = 0; i < games.size(); i++){
            if (games.get(i).GetName() == name){
                return i;
            }
        }

        // Should never get here
        return -1;
    }
}
