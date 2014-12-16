package tuentichallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TuentiChallenge2 {

    static int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
    static int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
    static HashMap<Dot, Character> circuit = new HashMap<>();
    
    public static void main(String[] args) {
        try {
            
            final int north = 0, east = 1, south = 2, west = 3;
            int direction = east;
            String plainCircuit = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            
            int x = 0, y = 0;
            if(minX > x) minX = x;
            if(maxX < x) maxX = x;
            if(minY > y) minY = y;
            if(maxY < y) maxY = y;                
            writePiece(x, y, '#');
            
            int i = plainCircuit.lastIndexOf("#");
            int index = i+1; 
            while(index != i){
                index = ++index%plainCircuit.length();
                char circuitPart = plainCircuit.charAt(index);
                switch(direction){
                    case east: x++; break; 
                    case north: y++; break;                          
                    case west: x--; break;
                    case south: y--; break;
                }              
                
                writePiece(x, y, (direction%2 == 0 && circuitPart == '-' ? '|' : circuitPart));
                
                if(circuitPart == '/') direction = (direction%2 == 0 ? direction+1 : direction-1);
                if(circuitPart == '\'') direction = 3-direction;
                  
                if(minX > x) minX = x;
                if(maxX < x) maxX = x;
                if(minY > y) minY = y;
                if(maxY < y) maxY = y;
                
                
            }
            System.out.println(minX + " " + maxX + " " + minY + " " + maxY);
            showResults();
        } catch (IOException ex) {
            Logger.getLogger(TuentiChallenge2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private static void writePiece(int x, int y, char circuitPart){
        System.out.println(x + " "+y+" "+circuitPart);
        circuit.put(new Dot(x, y), circuitPart);
    }
    
    private static void showResults(){
        for(int i = minY; i < maxY; i++){
            String line = "";
            for(int j = minX; j < maxX; j++){
                Character c = circuit.get(new Dot(j, i));
                line = line.concat(c == null ? " " : c.toString());
            }
            System.out.println(line);
        }
    }
    
}

class Dot {
    int x, y;
    
    public Dot(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        if( o instanceof Dot ){
            if(o == null) return false;
            Dot d = (Dot) o;
            return (this.x == d.x && this.y == d.y);
        }else return false;
    }
    
    @Override 
    public int hashCode(){
        return (int)this.y*300+this.x;
    }
}

