package com.chrisl.footyscore.footyscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    //declare public variables
    int teamAgoals = 0;
    int teamAbehinds = 0;
    int teamBgoals = 0;
    int teamBbehinds = 0;
    int teamAscore = 0;
    int teamBscore = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetScores();


    }


    //method to reset all values to 0 and update screen
    public void resetScores (){
        teamAgoals = 0;
        teamAbehinds = 0;
        teamBgoals = 0;
        teamBbehinds = 0;
        teamAscore = 0;
        teamBscore = 0;
        displayForTeamBscoreL1();
        displayForTeamBscoreL2();
        displayForTeamAscoreL1();
        displayForTeamAscoreL2();
    }



    //Displays the Line 1  (goals and behinds) Score for Team A

    public void displayForTeamAscoreL1 (){
        TextView teamAScoreLine1 = findViewById(R.id.teamAScoreLine1);
        teamAScoreLine1.setText(String.valueOf(teamAbehinds)+ " . " +String.valueOf(teamAgoals));
    }

    //Displays the Line 2 (total) Score for Team A
    public void displayForTeamAscoreL2 (){
        TextView teamAScoreLine2 = findViewById(R.id.teamAScoreLine2);
        teamAScoreLine2.setText(String.valueOf(teamAgoals * 6 + teamAbehinds));
    }

    //Displays the Line 1  (goals and behinds) Score for Team B

    public void displayForTeamBscoreL1 (){
        TextView teamBScoreLine1 = findViewById(R.id.teamBScoreLine1);
        teamBScoreLine1.setText(String.valueOf(teamBbehinds)+ " . " +String.valueOf(teamBgoals));
    }


    //Displays the Line 2 (total) Score for Team B
    public void displayForTeamBscoreL2 (){
        TextView teamBScoreLine2 = findViewById(R.id.teamBScoreLine2);
        teamBScoreLine2.setText(String.valueOf(teamBgoals * 6 + teamBbehinds));
    }

    //runs on click of button teamAGoal
    public void teamAGoalButton(View view){
        teamAgoals = teamAgoals + 1;
        displayForTeamAscoreL1();
        displayForTeamAscoreL2();
    }

    //runs on click of button teamAbehind
    public void teamAbehindButton(View view){
        teamAbehinds = teamAbehinds + 1;
        displayForTeamAscoreL1();
        displayForTeamAscoreL2();
    }

    //runs on click of button teamBGoal
    public void teamBgoalButton(View view){
        teamBgoals = teamBgoals + 1;
        displayForTeamBscoreL1();
        displayForTeamBscoreL2();
    }

    //runs on click of button teamBbehind
    public void teamBbehindButton(View view){
        teamBbehinds = teamBbehinds + 1;
        displayForTeamBscoreL1();
        displayForTeamBscoreL2();
    }

    //runs on click of resetScores
    public void resetScoresButton(View view){
        resetScores();
    }

}
