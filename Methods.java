
/**
 * Write a description of class isBalan here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Methods
{
    // instance variables - replace the example below with your own
    private static int minPath(char[][] minChess, int i, int j, char prev){
        final char prev = 'B';
        if (outOfIndex)
            return 600;
        if (minChess[i][j] == prev)
            return 600;
        temp = minChess[i][j];
        minChess[i][j] = 'B';
        if (horseValidMove(minChess, i, j) != true){
            int move1 = chess(2.1)
            mvoe2
            move3
            move4
            move5
            move6
            move7
            move8
        }
        
        else{
            return 0;
        }
        
        -
        minChess[i][j] = temp;
        minPath = Math.min(Math.min(Math.min(Math.min(move1,move2),Math.min(move3,move4)),(Math.min(Math.min(move5,move6),Math.min(move7,move8))));
        if ( minPath < 600) 
            return minPath
        else{
            return -1;
        }
        
        
    }
    
     
    private boolean horseValidMove(char[][] a, int i, int j){
        if (a[i+1][j+2] == 'K' || a[i+1][j-2] != 'B' || a[i-1][j+2] != 'B' || a[i-1][j-2] != 'B' || a[i-2][j-1] != 'b'
    }true 
            
      
    
    
    public static int[] findUFO (Space tmp){
        return findUFO(tmp, 0, 0);  
    }
    
    private static int[] findUFO(Space tmp, int x, int y){
        
        
        while (tmp.ask(x,y) != UFO){
            if (tmp.ask(x,y)[0] == 1)
                x++;
            if (tmp.ask(x,y)[1] == 1)
                y++;
        }
        UFO[0] = x;
        UFO[1] = y;
        return UFO;
    }
    }

