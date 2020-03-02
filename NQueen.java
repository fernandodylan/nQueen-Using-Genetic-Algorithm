import java.util.*;

public class NQueen{

    //Initialize a array of boards that represents the population
    Board population = new Board(100);

    public void initializePopulation(){
        

        for(int i = 0; i < 100; i++){
            //Initialize a board of size 7
            int[] array = new int[9];

            //Create a random board
            for(int j = 0; j < 8; j++){
                array[j] = (int)(Math.random() * 8);
            }
            
            //randomize each board and add them to an array list
            population.addBoard(population.randomArray(array));
             
        }
        population.printBoard();

    }

    public void print(){
        population.printBoard();

    }

    public void getFitness(){
        population.getFitness();
        
    }

    public void Crossover(){
        population.Crossover();

    }

    public void Mutate(){
        population.Mutate();
    }


    public static void main(String[] args){
        NQueen ga = new NQueen();

        ga.initializePopulation();
        ga.getFitness();
        ga.Crossover();
    
        System.out.println("\n\n New Fitness");
        ga.getFitness();
        ga.print();

        for(int i = 0; i < 1000; i++){
            ga.Crossover();
            ga.getFitness();
            ga.Mutate();
        }
        
        System.out.println("\n\n New Fitness");
        ga.print();
        

        
    }
}