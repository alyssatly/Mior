package com.example.mior.models;

import java.util.ArrayList;

public class Game{
    private boolean active;
    private String name;
    private int roundLen;  // average length of a round in seconds
    private int gameLen; // average length of a game in seconds
    private ArrayList<Alert> notifications; // ArrayList of the notification objects for a given game


    // Constructor
    public Game(String newName, int newRoundLen, int newGameLen){
        active = false;
        name = newName;
        roundLen = newRoundLen;
        gameLen = newGameLen;
        notifications = new ArrayList<Alert>();
    }


    // Replaces values of current attributes with the given ones
    public void Edit(String newName, int newRoundLen, int newGameLen){
        name = newName;
        roundLen = newRoundLen;
        gameLen = newGameLen;

        for (int i = 0; i < notifications.size(); i++) {
            (notifications.get(i)).SetGameInfo(roundLen, gameLen);
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
    public boolean AddNotification(String name, String message, String intervalType, int interval, boolean continuousRemind, int extraAlerts, int extraInterval){

        for (int i = 0; i < notifications.size(); i++){
            if (notifications.get(i).GetName() == name){
                return false;
            }
        }

        Alert newNotif = new Alert(name, message, intervalType, interval, continuousRemind, extraAlerts, extraInterval);
        newNotif.SetGameInfo(roundLen, gameLen);

        notifications.add(newNotif);
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
}


