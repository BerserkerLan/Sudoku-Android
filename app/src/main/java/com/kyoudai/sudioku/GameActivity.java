package com.kyoudai.sudioku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kyoudai.utils.Sudoku;

public class GameActivity extends AppCompatActivity {

    GridLayout sudokuGrid;
    Drawable[][] drawableCellArray;
    Sudoku sudoku;
    private TextView lastCell;
    private Drawable lastCellDrawable;

    public static int mistakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        sudokuGrid = findViewById(R.id.sudokuGrid);
        mistakes = 0;

        setupDrawableForGrid();
        setGrid();
        findViewById(R.id.backToMainButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setGrid() {
        mistakes = 0;
        int dimension = 9;
        int numberOfDigitsToRemove = 50;

        sudoku = new Sudoku(dimension, numberOfDigitsToRemove);
        sudoku.fillValues();
        sudoku.printSudoku();

        int[][] matrix = sudoku.returnMatrix();

        TextView numberCell;
        sudokuGrid.removeAllViews();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(110, 110);

        for (int i = 0; i < matrix.length; i++) {
            int[] ints = matrix[i];
            for (int i1 = 0; i1 < ints.length; i1++) {
                int anInt = ints[i1];
                numberCell = new TextView(getApplicationContext());
                if (anInt != 0) {
                    numberCell.setText("" + anInt);
                }
                numberCell.setLayoutParams(params);
                if (drawableCellArray[i][i1] != null) {
                    numberCell.setBackground(drawableCellArray[i][i1]);
                }
                else {
                    numberCell.setBackground(getResources().getDrawable(R.drawable.rectback));
                }
                numberCell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lastCell != null) {
                            lastCell.setBackground(lastCellDrawable);
                        }
                        lastCell = (TextView) v;
                        lastCellDrawable = v.getBackground();
                        v.setBackground(getResources().getDrawable(R.color.lightBlue));
                    }
                });
                numberCell.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                numberCell.setTextColor(getResources().getColor(R.color.black));
                numberCell.setTextSize(33);
                sudokuGrid.addView(numberCell);
            }
        }
    }

    public void setupDrawableForGrid() {
        drawableCellArray = new Drawable[9][9];
        //Set rights which are column divisions
        drawableCellArray[0][2] = getResources().getDrawable(R.drawable.cellright);
        drawableCellArray[0][5] = getResources().getDrawable(R.drawable.cellright);
        //drawableCellArray[0][8] = getResources().getDrawable(R.drawable.cellright);

        drawableCellArray[1][2] = getResources().getDrawable(R.drawable.cellright);
        drawableCellArray[1][5] = getResources().getDrawable(R.drawable.cellright);
       // drawableCellArray[1][8] = getResources().getDrawable(R.drawable.cellright);

        drawableCellArray[2][2] = getResources().getDrawable(R.drawable.cellbottomright);
        drawableCellArray[2][5] = getResources().getDrawable(R.drawable.cellbottomright);
        drawableCellArray[2][8] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[3][2] = getResources().getDrawable(R.drawable.cellright);
        drawableCellArray[3][5] = getResources().getDrawable(R.drawable.cellright);
       // drawableCellArray[3][8] = getResources().getDrawable(R.drawable.cellright);

        drawableCellArray[4][2] = getResources().getDrawable(R.drawable.cellright);
        drawableCellArray[4][5] = getResources().getDrawable(R.drawable.cellright);
       // drawableCellArray[4][8] = getResources().getDrawable(R.drawable.cellright);

        drawableCellArray[5][2] = getResources().getDrawable(R.drawable.cellbottomright);
        drawableCellArray[5][5] = getResources().getDrawable(R.drawable.cellbottomright);
        drawableCellArray[5][8] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[6][2] = getResources().getDrawable(R.drawable.cellright);
        drawableCellArray[6][5] = getResources().getDrawable(R.drawable.cellright);
        //drawableCellArray[6][8] = getResources().getDrawable(R.drawable.cellright);

        drawableCellArray[7][2] = getResources().getDrawable(R.drawable.cellright);
        drawableCellArray[7][5] = getResources().getDrawable(R.drawable.cellright);
        //drawableCellArray[7][8] = getResources().getDrawable(R.drawable.cellright);

        drawableCellArray[8][2] = getResources().getDrawable(R.drawable.cellbottomright);
        drawableCellArray[8][5] = getResources().getDrawable(R.drawable.cellbottomright);
        drawableCellArray[8][8] = getResources().getDrawable(R.drawable.cellbottom);

        //Set Bottoms which are row divisions
        drawableCellArray[2][0] = getResources().getDrawable(R.drawable.cellbottom);
        drawableCellArray[2][1] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[2][3] = getResources().getDrawable(R.drawable.cellbottom);
        drawableCellArray[2][4] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[2][6] = getResources().getDrawable(R.drawable.cellbottom);
        drawableCellArray[2][7] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[5][0] = getResources().getDrawable(R.drawable.cellbottom);
        drawableCellArray[5][1] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[5][3] = getResources().getDrawable(R.drawable.cellbottom);
        drawableCellArray[5][4] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[5][6] = getResources().getDrawable(R.drawable.cellbottom);
        drawableCellArray[5][7] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[8][0] = getResources().getDrawable(R.drawable.cellbottom);
        drawableCellArray[8][1] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[8][3] = getResources().getDrawable(R.drawable.cellbottom);
        drawableCellArray[8][4] = getResources().getDrawable(R.drawable.cellbottom);

        drawableCellArray[8][6] = getResources().getDrawable(R.drawable.cellbottom);
        drawableCellArray[8][7] = getResources().getDrawable(R.drawable.cellbottom);

    }

    public void numberClick(View view) {
        if (lastCell != null) {
            if (lastCell.getText() == "") {
                //Top diff = 110
                //Left diff = 110
                //Start= Top:659, left: 45 (Top left)
                //End= Top:1539, left: 925 (bottom right)
                Button button = (Button) view;
                int left = getRelativeLeft(lastCell);
                int top = getRelativeTop(lastCell);
                left = left - 45;
                left = left/110;
                top = top - 659;
                top = top/ 110;
                //Toast.makeText(getApplicationContext(), left + " : " + top, Toast.LENGTH_LONG).show();
                int selectedNumber = Integer.parseInt(button.getText().toString());
                int actualAnswer = sudoku.returnSolvedMatrix()[top][left];
                if (selectedNumber == actualAnswer) {
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    lastCell.setText(selectedNumber + "");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong, try again", Toast.LENGTH_SHORT).show();
                    mistakes++;
                    if (mistakes == 3) {
                        showLosingDialogue();
                    }
                }
            }
        }
    }
    private int getRelativeLeft(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getLeft();
        else
            return myView.getLeft() + getRelativeLeft((View) myView.getParent());
    }

    private int getRelativeTop(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }

    public void showLosingDialogue() {
        final Intent showLosingDialogue = new Intent(getApplicationContext(), LosingPopup.class);
        startActivity(showLosingDialogue);
    }
}
