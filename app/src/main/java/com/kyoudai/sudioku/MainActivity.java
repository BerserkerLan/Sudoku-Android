package com.kyoudai.sudioku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyoudai.utils.Sudoku;

public class MainActivity extends AppCompatActivity {

    GridLayout sudokuGrid;
    Drawable[][] drawableCellArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sudokuGrid = findViewById(R.id.sudokuGrid);

        setupDrawableForGrid();
        setGrid();
        findViewById(R.id.refreshButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGrid();
            }
        });

    }

    public void setGrid() {
        int dimension = 9;
        int numberOfDigitsToRemove = 50;

        Sudoku sudoku = new Sudoku(dimension, numberOfDigitsToRemove);
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
                numberCell.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                numberCell.setTextSize(33);
//                numberCell.setBackgroundResource(R.drawable.cellbottom);
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

}
