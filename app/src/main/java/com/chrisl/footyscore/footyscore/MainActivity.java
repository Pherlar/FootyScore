/**
 * Note to the reader:  This app keeps score for Australian Rules football.
 * In Australian Rules Football, kicking the ball through the center posts is a 'Goal' worth 6 points
 * Kicking the ball through the outer posts is a 'Behind' worth 1 point.
 * From Wikipedia - "As an example of a score report, consider a match between Essendon and Melbourne with the former as the home team.
 * Essendon's score of 11 goals and 14 behinds equates to 80 points. Melbourne's score of 10 goals and 7 behinds equates to a 67-point tally.
 * Essendon wins the match by a margin of 13 points. Such a result would be written as:
 * "Essendon 11.14 (80) defeated Melbourne 10.7 (67)."[68]
 * And spoken as:
 * "Essendon, eleven-fourteen, eighty, defeated Melbourne ten-seven, sixty-seven".
 */


package com.chrisl.footyscore.footyscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Stack;

public class MainActivity extends AppCompatActivity
{
    TextView TeamA_gbScore;
    TextView TeamA_sumScore;
    TextView TeamB_gbScore;
    TextView TeamB_sumScore;

    Stack<Integer> scoreStack;
    Stack<Integer> undoStack;




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
        undoStack = new Stack<>();
        resetScores();


    }


    //method to reset all values to 0 and update screen
    public void resetScores (){
        scoreStack.clear();
        undoStack.clear();
        updateScore();
    }


    //Displays the Line 2 (total) Score for Team A
    public void updateScore (){
        int teamA_behind = 0;
        int teamA_goal = 0;
        int teamB_behind = 0;
        int teamB_goal=0;

        //create an iterator to handle stepping through the stack and calculating the current score
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
        TeamA_gbScore.setText (Integer.toString(teamA_goal) + " . " + Integer.toString(teamA_behind));
        TeamA_sumScore.setText("("+Integer.toString(teamA_behind + teamA_goal*6)+")");
        TeamB_gbScore.setText( Integer.toString(teamB_goal)+ " . " +  Integer.toString(teamB_behind));
        TeamB_sumScore.setText("("+Integer.toString(teamB_behind + teamB_goal*6)+")");


    }

    //runs on click of Undo button
    //scores are moved from the score stack to a 'redo' stack which then enables
    //the user to redo any undos
    public void undoClick(View view){
        if (!scoreStack.isEmpty())
        {
            int lastEntry;
            lastEntry = scoreStack.pop();
            Log.i("MainActiviy", lastEntry + "Popped from scoreStack");
            undoStack.push(lastEntry);
            updateScore();
        }
        else{
            Toast.makeText(this, "Nothing to Undo!", Toast.LENGTH_SHORT).show();
        }
    }


    public void redoClick (View view){
        if (!undoStack.isEmpty())
        {
            int undoLastEntry;
            undoLastEntry = undoStack.pop();
            scoreStack.push(undoLastEntry);
            updateScore();
        }
        else{
            Toast.makeText(this, "Nothing to Redo!", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * This section handles the 'score stack' which enables the undo/redo function
     * Behinds for Team A are always +1 on the stack, Goals are always +6 on the stack
     * Similarly, behinds for Team B are always -1 and Goals are -6.  This enables one
     * Score stack to keep track of the running score of the game.
     */

    //runs on click of button teamAbehind
    public void teamA_BehindClick(View view){
        scoreStack.push(1);
        undoStack.clear(); //need this to clear the undo stack now that it has been replaced
        updateScore();
    }


    //runs on click of button teamAGoal
    public void teamA_GoalClick(View view){
        scoreStack.push(6);
        undoStack.clear(); //need this to clear the undo stack now that it has been replaced
        updateScore();
     }

    //runs on click of button teamBbehind
    public void teamB_BehindClick(View view){
        scoreStack.push(-1);
        undoStack.clear(); //need this to clear the undo stack now that it has been replaced
        updateScore();
    }

    //runs on click of button teamBGoal
    public void teamB_GoalClick(View view){
        scoreStack.push(-6);
        undoStack.clear(); //need this to clear the undo stack now that it has been replaced
        updateScore();
    }


    //runs on click of resetScores
    public void resetScoresButton(View view){
        resetScores();
    }

}
