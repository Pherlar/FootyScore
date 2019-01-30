package com.chrisl.footyscore.footyscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Stack;

public class MainActivity extends AppCompatActivity
{
    TextView TeamA_gbScore;
    TextView TeamA_sumScore;
    TextView TeamB_gbScore;
    TextView TeamB_sumScore;

    Stack<Integer> scoreStack;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TeamA_gbScore = findViewById(R.id.teamAScoreLine1);
        TeamA_sumScore = findViewById(R.id.teamAScoreLine2);
        TeamB_gbScore = findViewById(R.id.teamBScoreLine1);
        TeamB_sumScore = findViewById(R.id.teamBScoreLine2);

        scoreStack = new Stack<>();
        resetScores();


    }


    //method to reset all values to 0 and update screen
    public void resetScores (){
        scoreStack.clear();
        updateScore();
    }





    //Displays the Line 2 (total) Score for Team A
    public void updateScore (){
        int teamA_behind = 0;
        int teamA_goal = 0;
        int teamB_behind = 0;
        int teamB_goal=0;

        Iterator<Integer> iter = scoreStack.iterator();

        while (iter.hasNext())
        {
            Integer scoreEntry = iter.next();
            if (scoreEntry == 1)
            {
                teamA_behind ++;
            }
            else if (scoreEntry == 6)
            {
                teamA_goal ++;
            }
            else if (scoreEntry == -1)
            {
                teamB_behind ++;
            }
            else if (scoreEntry == -6)
            {
                teamB_goal ++;
            }
        }
        TeamA_gbScore.setText(Integer.toString(teamA_behind) + " . " + Integer.toString(teamA_goal));
        TeamA_sumScore.setText(Integer.toString(teamA_behind + teamA_goal*6));
        TeamB_gbScore.setText(Integer.toString(teamB_behind) + " . " + Integer.toString(teamB_goal));
        TeamB_sumScore.setText(Integer.toString(teamB_behind + teamB_goal*6));


    }



    //runs on click of button teamAbehind
    public void teamA_BehindClick(View view){
        scoreStack.push(1);
        updateScore();
    }


    //runs on click of button teamAGoal
    public void teamA_GoalClick(View view){
        scoreStack.push(6);
        updateScore();
     }

    //runs on click of button teamBbehind
    public void teamB_BehindClick(View view){
        scoreStack.push(-1);
        updateScore();
    }

    //runs on click of button teamBGoal
    public void teamB_GoalClick(View view){
        scoreStack.push(-6);
        updateScore();
    }



    //runs on click of resetScores
    public void resetScoresButton(View view){
        resetScores();
    }

}
