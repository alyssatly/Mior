package com.example.mior;

import java.util.ArrayList;

public class Game {
    private boolean active;
    private String name;
    private int roundLen;  // average length of a round in seconds
    private int gameLen; // average length of a game in seconds
    private ArrayList<Alert> notifications; // ArrayList of the notification objects for a given game
    private int image;


    // Constructor
    public Game(String newName, int newRoundLen, int newGameLen, int newImage){
        active = false;
        name = newName;
        roundLen = newRoundLen;
        gameLen = newGameLen;
        notifications = new ArrayList<Alert>();
        image = newImage;
    }


    // Replaces values of current attributes with the given ones
    public void Edit(String newName, int newRoundLen, int newGameLen, int newImage){
        name = newName;
        roundLen = newRoundLen;
        gameLen = newGameLen;
        image = newImage;

        for (int i = 0; i < notifications.size(); i++) {
            notifications.get(i).SetGameInfo(roundLen, gameLen);;
        }
    }


    public void ToggleActive(){
        active = !(active);

        // Set all notifications' active state to be the same as the game's, so that turning on the game turns them all on, and turning off the game turns them all off
        for (int i = 0; i < notifications.size(); i++){
            notifications.get(i).SetActive(active);
        }
    }


    // Adds a notification object with the given parameters, returns true if succeds, returns false if the given name already exists
    public boolean AddNotification(String name, String message, String intervalType, int interval, boolean continuousRemind, int extraAlerts, int extraInterval, int image){

//        for (int i = 0; i < notifications.size(); i++){
//            if (notifications.get(i).GetName() == name){
//                return false;
//            }
//        }

        Alert newAlert = new Alert(name, message, intervalType, interval, continuousRemind, extraAlerts, extraInterval, image);
        newAlert.SetGameInfo(roundLen, gameLen);

        notifications.add(newAlert);
        // notifications.add(name);
        return true;
    }


    // Given a name, removes that notification from the list
    public void RemoveNotification(String name){
        int temp = 0;
        for (int i = 0; i < notifications.size(); i++){
            // if (notifications.get(i).name == name)
            if (notifications.get(i).GetName() == name){
                temp = i;
            }
        }

        notifications.remove(temp);
    }


    // Getter function
    public boolean GetActive(){
        return active;
    }

    public String GetName(){
        return name;
    }

    public int GetRoundLen()
    {
        return roundLen;
    }

    public int GetGameLen()
    {
        return gameLen;
    }

    public ArrayList<Alert> GetNotifications(){
        return notifications;
    }

    // Returns a string list of all the notification names
    public ArrayList<String> DisplayNotifications(){
        ArrayList<String> temp = new ArrayList<String>();

        for (int i = 0; i < notifications.size(); i++){
            temp.add(notifications.get(i).GetName());
        }

        return temp;
    }


    // Given an alert name, returns a reference to that alert
    public Alert GetGameReference(String name){
        for (int i = 0; i < notifications.size(); i++){
            if (notifications.get(i).GetName() == name){
                return notifications.get(i);
            }
        }

        // Should never get here
        return new Alert("", "", "", 0, false, 0, 0, 0);
    }


    // Given an alert name, returns the index of that alert
    public int GetGameIndex(String name){
        for (int i = 0; i < notifications.size(); i++){
            if (notifications.get(i).GetName() == name){
                return i;
            }
        }

        // Should never get here
        return -1;
    }
}
