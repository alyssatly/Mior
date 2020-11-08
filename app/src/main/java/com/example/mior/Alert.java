package com.example.mior;

import java.util.Timer;
import java.util.TimerTask;

public class Alert {
    private boolean active;
    private String name;
    private String message;  // Message to display when sending notification
    private String intervalType;  //How often it notifies you, can be "round", "game", or "custom"
    private int interval;  // Time in seconds to wait to notify when "custom" intervalType is chosen
    private boolean continuousRemind;  // Should the user be notified multiple times after the initial alert
    private int extraAlerts;  // How many extra alerts to do
    private int extraInterval;  // Interval delay for extra alerts
    private int extraAlertsDone; // How many extra alerts have been sent
    private int roundLength;
    private int gameLength;
    private Timer timer;  //Internal timer
    private Timer subTimer; // Internal timer for extra alerts
    private int image;  //Image id


    // Constructor
    public Alert(String name2, String message2, String intervalType2, int interval2,
                        boolean continuousRemind2, int extraAlerts2, int extraInterval2, int image2){

        active = false;
        name = name2;
        message = message2;
        intervalType = intervalType2;
        interval = interval2;
        continuousRemind = continuousRemind2;
        extraAlerts = extraAlerts2;
        extraInterval = extraInterval2;
        extraAlertsDone = 0;
        timer = new Timer();
        subTimer = new Timer();
        image = image2;
    }


    // Changes the given notification object's attributes to those given
    public void Edit(String name2, String message2, String intervalType2, int interval2, boolean continuousRemind2, int extraAlerts2, int extraInterval2, int image2){

        name = name2;
        message = message2;
        intervalType = intervalType2;
        interval = interval2;
        continuousRemind = continuousRemind2;
        extraAlerts = extraAlerts2;
        extraInterval = extraInterval2;
        image = image2;
    }


    // Updates the game info, should be called whenever a game object is edited
    public void SetGameInfo(int roundLength2, int gameLength2){
        roundLength = roundLength2;
        gameLength = gameLength2;
    }


    public void ToggleActive(){
        active = !(active);

        // If turned on, start timer
        if (active){
            StartTimer();
        }
    }


    public void SetActive(boolean state){
        active = state;

        // If turned on, start timer
        if (active){
            StartTimer();
        }
    }



    // Executes when time finishes
    class SendRemind extends TimerTask {

        @Override
        public void run() {
            System.out.println(message);

            // When a main alert executes, reset extra reminds
            extraAlertsDone = 0;
            subTimer.cancel();
            subTimer.purge();

            // Set another reminder for the interval
            int temp = 0;

            if (intervalType == "custom"){
                temp = interval;
            }
            else if (intervalType == "round"){
                temp = roundLength;
            }
            else if (intervalType == "game") {
                temp = gameLength;
            }
            else{
                temp = 9999999;
            }

            timer.schedule(new SendRemind(), temp*1000);

            // Keep alerting if continuousRemind is true
            if (continuousRemind){
                //only set an extra alert if haven't met the cap
                if (extraAlertsDone < extraAlerts){
                    subTimer = new Timer();
                    subTimer.schedule(new SendExtraRemind(), extraInterval*1000);
                }
            }
        }};


    // Runs when an extra remind finishes
    class SendExtraRemind extends TimerTask {

        @Override
        public void run() {

            System.out.println("Extra " + message);

            if (continuousRemind){
                //only set an extra alert if haven't met the cap
                if (extraAlertsDone < extraAlerts){
                    subTimer.schedule(new SendExtraRemind(), extraInterval*1000);
                }
            }
        }};


    // Sets a timer for the interval delay
    public void StartTimer(){
        if (active)
        {
            timer = new Timer();
            int temp = 0;

            if (intervalType == "custom"){
                temp = interval;
            }
            else if (intervalType == "round"){
                temp = roundLength;
            }
            else if (intervalType == "game"){
                temp = gameLength;
            }
            else{
                temp = 9999999;
            }
            timer.schedule(new SendRemind(), temp*1000);
        }
    }

    // Stops Timer, clears the main and subTimers
    public void StopTimer(){
        timer.cancel();
        timer.purge();
        subTimer.cancel();
        subTimer.purge();

        extraAlertsDone = 0;
    }


    // Getter function
    public boolean GetActive(){
        return active;
    }

    public String GetName(){
        return name;
    }

    public String GetMessage(){
        return message;
    }

    public String GetIntervalType(){
        return intervalType;
    }

    public int GetInterval(){
        return interval;
    }

    public boolean GetContinuousRemind(){
        return continuousRemind;
    }

    public int GetExtraAlerts(){
        return extraAlerts;
    }

    public int GetExtraInterval(){
        return extraInterval;
    }
}
