package tuentichallenge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class TuentiChallenge1 {

    public static void main(String[] args) {
        try {
            String students = "students.gz";
            BufferedReader studentsFile = new BufferedReader(new InputStreamReader (new GZIPInputStream (new FileInputStream (students))));
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            int nCases = Integer.parseInt(input.readLine());
            ArrayList<String> cases = new ArrayList<>();
            for(int i = 0; i < nCases; i++){
                cases.add(input.readLine());
            }
            
            ArrayList<String> matches = new ArrayList<>();
            for(int i = 0; i < nCases; i++) matches.add("");
            
            while(studentsFile.ready()){
                String line = studentsFile.readLine();
                int separator = line.indexOf(",");
                String filter = line.substring(separator+1);
                int index = cases.lastIndexOf(filter);
                if(index > -1) matches.set(index, matches.get(index).compareTo(line.substring(0, separator+1)) > 0 ? line.substring(0, separator+1).concat(matches.get(index)) : matches.get(index).concat(line.substring(0, separator+1)));
            }
            
            for(int i = 0; i < matches.size(); i++){
                System.out.println("Case #"+(i+1)+": "+(matches.get(i).equals("") ? "NONE" : matches.get(i).substring(0,matches.get(i).length()-1)));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TuentiChallenge1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TuentiChallenge1.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
