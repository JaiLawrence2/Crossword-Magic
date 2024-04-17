package edu.jsu.mcis.cs408.crosswordmagic.model;

import android.content.Context;
import android.util.Log;

import java.util.Arrays;

import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.DAOFactory;
import edu.jsu.mcis.cs408.crosswordmagic.model.dao.PuzzleDAO;

public class CrosswordMagicModel extends AbstractModel {
    public static final String TAG = "Model";

    private final int DEFAULT_PUZZLE_ID = 1;

    private Puzzle puzzle;
    private String guess;
    private int box_selected;

    public CrosswordMagicModel(Context context) {

        DAOFactory daoFactory = new DAOFactory(context);
        PuzzleDAO puzzleDAO = daoFactory.getPuzzleDAO();

        this.puzzle = puzzleDAO.find(DEFAULT_PUZZLE_ID);

    }

    public void getTestProperty() {

        String wordCount = (this.puzzle != null ? String.valueOf(puzzle.getSize()) : "none");
        firePropertyChange(CrosswordMagicController.TEST_PROPERTY, null, wordCount);

    }
    public void getGridLetters(){

        firePropertyChange(CrosswordMagicController.GRID_LETTERS_PROPERTY, null, puzzle.getLetters());
    }

    public void getGridDimensions() {

        Integer[] dimension = new Integer[2];
        dimension[0] = puzzle.getHeight();
        dimension[1] = puzzle.getWidth();
        firePropertyChange(CrosswordMagicController.GRID_DIMENSION_PROPERTY, null, dimension);

    }

    public void getGridNumbers(){
        firePropertyChange(CrosswordMagicController.GRID_NUMBERS_PROPERTY, null, puzzle.getNumbers());
    }

    public void getCluesAcross() {
        firePropertyChange(CrosswordMagicController.CLUES_ACROSS_PROPERTY, null, puzzle.getCluesAcross());
    }

    public void getCluesDown() {
        Log.i(TAG, "Clues: "+ puzzle.getCluesDown());
        firePropertyChange(CrosswordMagicController.CLUES_DOWN_PROPERTY, null, puzzle.getCluesDown());
    }
    public void getGuesses(Integer num, String guess){
         firePropertyChange(CrosswordMagicController.GUESS_PROPERTY, null, puzzle.checkGuess(num, guess) );
    }
    public void getSolved(){
        firePropertyChange(CrosswordMagicController.SOLVED_PROPERTY, null, puzzle.isSolved());
    }
    public void getSelectedBox(int box_selected){
        int oldValue = this.box_selected;
        this.box_selected = box_selected;
        firePropertyChange(CrosswordMagicController.SELECTED_BOX_PROPERTY, oldValue, box_selected);

    }
    public void getUserInput(String guess){
        String oldValue = this.guess;
        this.guess = guess;
        firePropertyChange(CrosswordMagicController.USER_INPUT_PROPERTY, oldValue, guess);

    }

}