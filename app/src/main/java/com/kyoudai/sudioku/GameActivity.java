package com.kyoudai.sudioku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kyoudai.utils.Sudoku;

import java.net.URLEncoder;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    GridLayout sudokuGrid;
    Drawable[][] drawableCellArray;
    Sudoku sudoku;
    private TextView lastCell;
    private Drawable lastCellDrawable;
    SwitchCompat switchToDio;
    int[][] matrix;
    LinearLayout numbersLayout;
    int mode = 0; //0 = int, 1 = DIO (Amazing programming ik >.> )

    public static int mistakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Easy : Remove 25
        //Medium : Remove 35
        //Hard : Remove 50
        int difficulty = getIntent().getIntExtra("difficulty", 0);
        boolean cont = getIntent().getBooleanExtra("continue", false);

        if (difficulty == 0) {
            difficulty = 25;
        }
        else if (difficulty == 1) {
            difficulty = 35;
        }
        else {
            difficulty = 50;
        }
        sudokuGrid = findViewById(R.id.sudokuGrid);
        mistakes = 0;
        switchToDio = findViewById(R.id.switchToDio);
        numbersLayout = findViewById(R.id.numbersLayout);

        //DIO Image size: 574 X 574
        switchToDio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { //It's DIO
                    mode=1;
                    TextView textView;
                    for (int i = 0; i < sudokuGrid.getChildCount(); i++) {
                        textView = (TextView) sudokuGrid.getChildAt(i);
                        textView.setTextColor(getResources().getColor(R.color.transparent));
                        switch (textView.getText().toString()) {
                            case ("1"):
                                textView.setBackground(getResources().getDrawable(R.drawable.dio_1));
                                break;
                            case ("2"):
                                textView.setBackground(getResources().getDrawable(R.drawable.dio_2));
                                break;
                            case ("3"):
                                textView.setBackground(getResources().getDrawable(R.drawable.dio_3));
                                break;
                            case ("4"):
                                textView.setBackground(getResources().getDrawable(R.drawable.dio_4));
                                break;
                            case ("5"):
                                textView.setBackground(getResources().getDrawable(R.drawable.dio_5));
                                break;
                            case ("6"):
                                textView.setBackground(getResources().getDrawable(R.drawable.dio_6));
                                break;
                            case ("7"):
                                textView.setBackground(getResources().getDrawable(R.drawable.dio_7));
                                break;
                            case ("8"):
                                textView.setBackground(getResources().getDrawable(R.drawable.dio_8));
                                break;
                            case ("9"):
                                textView.setBackground(getResources().getDrawable(R.drawable.dio_9));
                                break;
                        }
                    }
                    Button button;
                    for (int i = 0; i < numbersLayout.getChildCount(); i++) {
                        button =(Button) numbersLayout.getChildAt(i);
                        button.setTextColor(getResources().getColor(R.color.transparent));
                        switch (button.getText().toString()) {
                            case ("1"):
                                button.setBackground(getResources().getDrawable(R.drawable.dio_1));
                                break;
                            case ("2"):
                                button.setBackground(getResources().getDrawable(R.drawable.dio_2));
                                break;
                            case ("3"):
                                button.setBackground(getResources().getDrawable(R.drawable.dio_3));
                                break;
                            case ("4"):
                                button.setBackground(getResources().getDrawable(R.drawable.dio_4));
                                break;
                            case ("5"):
                                button.setBackground(getResources().getDrawable(R.drawable.dio_5));
                                break;
                            case ("6"):
                                button.setBackground(getResources().getDrawable(R.drawable.dio_6));
                                break;
                            case ("7"):
                                button.setBackground(getResources().getDrawable(R.drawable.dio_7));
                                break;
                            case ("8"):
                                button.setBackground(getResources().getDrawable(R.drawable.dio_8));
                                break;
                            case ("9"):
                                button.setBackground(getResources().getDrawable(R.drawable.dio_9));
                                break;
                        }
                    }

                }
                else {
                    mode=0;
                    TextView textView;
                    for (int i = 0; i < sudokuGrid.getChildCount(); i++) {
                        textView = (TextView) sudokuGrid.getChildAt(i);
                        textView.setBackground(getResources().getDrawable(R.drawable.rectback));
                        textView.setTextColor(getResources().getColor(R.color.black));
                        //Top diff = 110
                        //Left diff = 110
                        //Start= Top:659, left: 45 (Top left)
                        //End= Top:1539, left: 925 (bottom right)
                        int left = getRelativeLeft(textView);
                        int top = getRelativeTop(textView);
                        left = left - 45;
                        left = left/110;
                        top = top - 659;
                        top = top/ 110;
                        if (drawableCellArray[top+1][left] != null) {
                            textView.setBackground(drawableCellArray[top+1][left]);
                        }
                    }
                    Button button;
                    for (int i = 0; i < numbersLayout.getChildCount(); i++) {
                        button =(Button) numbersLayout.getChildAt(i);
                        button.setTextColor(getResources().getColor(R.color.colorPrimary));
                        button.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                }
            }
        });

        setupDrawableForGrid();
        if (cont) {
            setGrid(loadMatrix());
        }
        else {
            setGrid(difficulty);
        }
        findViewById(R.id.backToMainButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setGrid(int numberOfDigitsToRemove) {
        mistakes = 0;
        int dimension = 9;

        sudoku = new Sudoku(dimension, numberOfDigitsToRemove);
        sudoku.fillValues();
        sudoku.printSudoku();

        matrix = sudoku.returnMatrix();

        saveMatrix(matrix);

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

    public String matrixToString(int[][] matrix) {
        return Arrays.deepToString(matrix);
    }

    public void saveMatrix(int[][] matrix) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String savingThis = matrixToString(matrix);
        editor.putString("matrix", savingThis);
        editor.apply();
    }

    public int[][] loadMatrix() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String matrixString = sharedPref.getString("matrix", "0");
        return stringToDeep(matrixString);
    }

    private static int[][] stringToDeep(String str) {
        int row = 0;
        int col = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                row++;
            }
        }
        row--;
        for (int i = 0;; i++) {
            if (str.charAt(i) == ',') {
                col++;
            }
            if (str.charAt(i) == ']') {
                break;
            }
        }
        col++;

        int[][] out = new int[9][9];

        str = str.replaceAll("\\[", "").replaceAll("\\]", "");

        String[] s1 = str.split(", ");

        int j = -1;
        for (int i = 0; i < s1.length; i++) {
            if (i % col == 0) {
                j++;
            }
            out[j][i % col] = Integer.parseInt(s1[i]);
            //System.out.println(s1[i] + "\t" + j + "\t" + i % col);
        }
        return out;
    }

    public void setGrid(int[][] matrix) {
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
                    matrix[top][left] = actualAnswer;
                    lastCell.setText(selectedNumber + "");
                    if (mode == 1) {
                        switch (lastCell.getText().toString()) {
                            case ("1"):
                                lastCell.setBackground(getResources().getDrawable(R.drawable.dio_1));
                                break;
                            case ("2"):
                                lastCell.setBackground(getResources().getDrawable(R.drawable.dio_2));
                                break;
                            case ("3"):
                                lastCell.setBackground(getResources().getDrawable(R.drawable.dio_3));
                                break;
                            case ("4"):
                                lastCell.setBackground(getResources().getDrawable(R.drawable.dio_4));
                                break;
                            case ("5"):
                                lastCell.setBackground(getResources().getDrawable(R.drawable.dio_5));
                                break;
                            case ("6"):
                                lastCell.setBackground(getResources().getDrawable(R.drawable.dio_6));
                                break;
                            case ("7"):
                                lastCell.setBackground(getResources().getDrawable(R.drawable.dio_7));
                                break;
                            case ("8"):
                                lastCell.setBackground(getResources().getDrawable(R.drawable.dio_8));
                                break;
                            case ("9"):
                                lastCell.setBackground(getResources().getDrawable(R.drawable.dio_9));
                                break;
                        }
                    }

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

    @Override
    public void onBackPressed() {
        saveMatrix(matrix);
        super.onBackPressed();
    }
}
