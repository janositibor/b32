package examinformation;

public class ExamResult {
    private int theory;
    private int practice;

    public ExamResult(int theory, int practice) {
        this.theory = theory;
        this.practice = practice;
    }
    public boolean isFailed(int maxTheory,int maxPractice){
        return (maxTheory/theory>=2 || maxPractice/practice>=2);
    }
    public int totalScore(){
        return theory+practice;
    }

    public int getTheory() {
        return theory;
    }
    public int getPractice() {
        return practice;
    }

}
