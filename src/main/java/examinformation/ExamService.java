package examinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExamService {
    private int theoryMax;
    private int practiceMax;
    private Map<String,ExamResult> results=new TreeMap<>();

    public void readFromFIle(Path path) {
        try(BufferedReader reader= Files.newBufferedReader(path)){
             String line=reader.readLine();
             findMaxScores(line);
             while((line=reader.readLine())!=null){
                process(line);
            }

        }catch(IOException ioe){
            throw new IllegalArgumentException("Cannot read file: "+path);
        }
    }

    private void process(String line) {
        String[] parts=line.split(";");
        String name=parts[0];
        int[] scores=findScores(parts[1]);
        ExamResult examResult=new ExamResult(scores[0],scores[1]);
        results.put(name,examResult);
    }

    private void findMaxScores(String input) {
        int[] parts=findScores(input);
        theoryMax=parts[0];
        practiceMax=parts[1];
    }

    private int[] findScores(String input) {
        String[] parts=input.split(" ");
        return new int[]{Integer.parseInt(parts[0]),Integer.parseInt(parts[1])};
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public Map<String, ExamResult> getResults() {
        return results;
    }

    public List<String> findPeopleFailed() {
        return results.entrySet().stream().filter(e->e.getValue().isFailed(theoryMax,practiceMax)).map(e->e.getKey()).toList();
    }

    public String findBestPerson() {
        return results.entrySet().stream()
                .filter(e->!e.getValue().isFailed(theoryMax,practiceMax))
                .max(Comparator.comparing((Map.Entry<String,ExamResult> e)->e.getValue().totalScore()).thenComparing(Comparator.comparing((Map.Entry<String,ExamResult> e)->e.getKey())))
                .map(e->e.getKey())
                .orElseThrow(() -> new IllegalStateException(""));
    }
}
