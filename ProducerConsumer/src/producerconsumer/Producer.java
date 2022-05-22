
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;


public class Producer extends Thread {
    Buffer buffer;
    private final int sleepProductores2, rangomenor, rangomayor;
    int id;
    private boolean stop;
    private static final String Operations = "+-*/";
    
    Producer(Buffer buffer, int sleepProductores2, int rangomenor, int rangomayor, int id) {
        this.buffer = buffer;
        this.sleepProductores2 = sleepProductores2;
        this.rangomayor = rangomayor;
        this.rangomenor = rangomenor;
        this.id = id;
        this.stop = true;
        
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String product;
        
        while (stop) {
            int symbolmath = ThreadLocalRandom.
                    current().nextInt(Operations.length());
            int num1 = ThreadLocalRandom.
                    current().nextInt(rangomenor, rangomayor + 1);
            int num2 = ThreadLocalRandom.
                    current().nextInt(rangomenor, rangomayor + 1);
            
            product = String.format("(%c %d %d)", 
                    Operations.charAt(symbolmath), num1, num2);
            this.buffer.produce(product, id);
            
            try {
                Thread.sleep(sleepProductores2);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Terminar(){
        this.stop = false;
    }
    
}
