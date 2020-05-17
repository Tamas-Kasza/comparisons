package hu.elte.szakdolgozat;

import static java.util.Arrays.fill;

public class CounterVo {
    private Double comparisons[];
    private Double moves[];
    private Double selections[];
    private Double predicted_selections[];
    private Double collectedResults[];
    private Double elapsedTime[];

    private int currentRound = 0;
    private int maxListLength;

    public CounterVo(Integer maxListLength) throws Exception{
        comparisons = new Double[maxListLength];
        moves = new Double[maxListLength];
        selections = new Double[maxListLength];
        predicted_selections = new Double[maxListLength];
        collectedResults = new Double[maxListLength];
        elapsedTime = new Double[maxListLength];

        maxListLength = maxListLength;

        resetArrays();
    }

    public void resetArrays(){
        fill(comparisons,0D);
        fill(moves,0D);
        fill(selections,0D);
        fill(predicted_selections,0D);
        fill(collectedResults,0D);
        fill(elapsedTime,0D);
    }

    public void  addToCounter(SortHeleper.CounterType type) {
        addToCounter(type,1);
    }

    public void  addToCounter(SortHeleper.CounterType type, int amount) {
        if(!SortHeleper.CounterType.TIME.equals(type)) {
            collectedResults[currentRound] += amount;
        }

        switch(type) {
            case COMPARISONS:
                comparisons[currentRound] += amount;
                break;
            case MOVES:
                moves[currentRound] += amount;
                break;
            case SELECTIONS:
                selections[currentRound] += amount;
                break;
            case TIME:
                elapsedTime[currentRound] += amount;
                break;
            default:
        }
    }

    public void  removeFromCounter(SortHeleper.CounterType type, Double amount) {
        if(!SortHeleper.CounterType.TIME.equals(type)) {
            collectedResults[currentRound] -= amount;
        }

        switch(type) {
            case COMPARISONS:
                comparisons[currentRound] -= amount;
                break;
            case MOVES:
                moves[currentRound] -= amount;
                break;
            case SELECTIONS:
                selections[currentRound] -= amount;
                break;
            case TIME:
                elapsedTime[currentRound] -= amount;
                break;
            default:
        }
    }

    public void  setCounter(SortHeleper.CounterType type, Double amount) {
        if(!SortHeleper.CounterType.TIME.equals(type)) {
            collectedResults[currentRound] += amount;
        }

        switch(type) {
            case COMPARISONS:
                comparisons[currentRound] = amount;
                break;
            case MOVES:
                moves[currentRound] = amount;
                break;
            case SELECTIONS:
                selections[currentRound] = amount;
                break;
            case TIME:
                elapsedTime[currentRound] = amount;
                break;
            default:
        }
    }

    public void divideBy(int amount) {
        comparisons[currentRound] /= amount;
        moves[currentRound] /= amount;
        selections[currentRound] /= amount;
        collectedResults[currentRound] /= amount;
        elapsedTime[currentRound] /= amount;
    }




        //getter setter
    public  void setPredictedSelections(Double val) {
        predicted_selections[currentRound] = val;
    }

    public void setElapsedTime(Double val) {
        elapsedTime[currentRound] = val;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public Double[] getComparisons() {
        return comparisons;
    }

    public Double[] getMoves() {
        return moves;
    }

    public Double[] getSelections() {
        return selections;
    }

    public Double[] getCollectedResults() {
        return collectedResults;
    }

    public Double[] getElapsedTime() {
        return elapsedTime;
    }
}
