package com.kyoudai.sudioku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.kyoudai.utils.Sudoku;

public class MainActivity extends AppCompatActivity {

    GridLayout sudokuGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sudokuGrid = findViewById(R.id.sudokuGrid);
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

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                numberCell = new TextView(getApplicationContext());
                if (matrix[i][j] != 0) {
                    numberCell.setText("" + matrix[i][j]);
                }
                numberCell.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
                numberCell.setTextSize(30);
                numberCell.setGravity(Gravity.CENTER);
                numberCell.setBackgroundResource(R.drawable.rectback);
                sudokuGrid.addView(numberCell);
            }
        }
    }
}
