import java.util.*;
import java.math.*;
import java.lang.*;

public class Board{

    //Size of the population
    private int size;
    //Initialize the array list of boards which represents our population    
    private List<int[]> board = new ArrayList<int[]>();


    //Contructor that takes the size of the population as its argument
    public Board(int size){
        size = this.size;
        
    }

    //Getter for class
    public List<int[]> getBoard(){
        return board;
    }

    //Method to add a new board to an array list
    public List<int[]> addBoard(int[] arr){
        board.add(arr);

        return board;

    }

    //Mix up the numbers in the array to create a random population
    public int[] randomArray(int array[]){
        Random rand = new Random();
        
            for(int i = 0; i < 8; i++){
                int randomPosition = rand.nextInt(8);
                int temp = array[i];
                array[i] = array[randomPosition];
                array[randomPosition] = temp;

            }
        
        return array;
    }

    //Method to print the board
    public void printBoard(){
        for(int i = 0; i < board.size(); i++){
            System.out.println(Arrays.toString(board.get(i)));
        }
    }




    //Determine the fitness of the boards and sort them from most fit to least fit
    public void getFitness(){
        //Initalize Arrays for sorting and checking whether under attack
        int[] temparr = new int[9];

        //Loop through the entire array list
        for(int l = 0; l < board.size(); l++){
            int score = 0;
            temparr = board.get(l);

            //Loop through the first array in the array list
            for(int i = 0; i < 8; i++){  

                for(int j = 0; j < 8; j++){
                    int xDistance = Math.abs(i - j);
                    int yDistance = Math.abs(temparr[i] - temparr[j]); 
                    
                    if(i != j){
                        if (xDistance == yDistance){
                            score++;
                        }
                    }
                    if(i != j){
                        if(temparr[i] == temparr[j]){
                            score++;
                        }
                    }

                }
                
            }
            //System.out.println("The score is " + (score));
            if(score == 0){
                System.out.println("Solution Found! ");
                System.out.println(Arrays.toString(board.get(l)));
                System.exit(0);
            }
            board.get(l)[8] = (score);
        }
        
        //Use a comparator to sort the arrays(board) by the 8th index which is the fitness value
        Collections.sort(board,new Comparator<int[]>() {
            public int compare(int[] board1, int[] board2) {
                return Integer.compare(board1[8], board2[8]);
            }
        });

    }




    public void Crossover(){
        //Initialize array of indexes we're going to use for crossover parents
        int parentindexes[] = new int[51];
        int[] temparr1 = new int[9];
        int[] temparr2 = new int[9];
    
        //Initialize a temp board to hold the crossover population
        List<int[]> tempboard = new ArrayList<int[]>();
        

        //fill the array with the random indexes favoring more fit boards (smaller index values)
        for(int i = 0; i < parentindexes.length; i++){
            double number = Math.random();
            int randomindex = (int) (100 * Math.pow(number, 5));
            parentindexes[i] = randomindex;
        }

        //Loop through the population selecting random parents and crossing them over
        for(int i = 0; i < parentindexes.length - 1; i++){

            int j = i+1;
            temparr1 = board.get(parentindexes[i]);
            temparr2 = board.get(parentindexes[j]);

            int[] child1 = {temparr1[0],temparr1[1],temparr1[2],temparr2[3],temparr2[4],temparr2[5],temparr2[6],temparr2[7], 0};
            int[] child2 = {temparr1[3],temparr1[4],temparr1[5],temparr2[6],temparr2[7],temparr2[0],temparr2[1],temparr2[2], 0};
            
            //Save the children into a temporary array list
            tempboard.add(child1);
            tempboard.add(child2);


        }

        board.clear();
        board = tempboard;
      

    }


    public void Mutate(){
        for(int i = 0; i < board.size(); i++){
            Random rand = new Random();
            int random1 = rand.nextInt((7-0) + 1) + 0;
            int random2 = rand.nextInt((7-0) + 1) + 0;


            board.get(i)[random1] = random2;

        }
    }
}




