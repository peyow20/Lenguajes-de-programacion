
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;


public class Producer extends Thread {
    Buffer buffer;
    int numeroProductores, sleepProductores;
    HashMap <String,String> tareasPorHacer;
    HashMap <String,String> tareasRealizadas;
    private static final String symbol = "+-*/";
    boolean stop;

    
    public Producer(Buffer buffer, int numeroProductores, int sleepProductores, HashMap <String,String> tareasPorHacer,HashMap <String,String> tareasRealizadas, boolean stop) {
        this.buffer = buffer;
        this.numeroProductores = numeroProductores;
        this.sleepProductores = sleepProductores;
        this.tareasPorHacer = tareasPorHacer;
        this.tareasRealizadas = tareasRealizadas;
        this.stop = true;
        
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products;
        Random r = new Random(System.currentTimeMillis());
        
        
        while(stop) {
           System.out.println(stop);
           System.out.println(numeroProductores);
           System.out.println(sleepProductores);
           
            int num1 = ThreadLocalRandom.current().nextInt(sleepProductores, numeroProductores + 1);
            int num2 = ThreadLocalRandom.current().nextInt(sleepProductores, numeroProductores + 1);
            int sym = ThreadLocalRandom.current().nextInt(symbol.length());
            
            products = String.format("(%c %d %d)", symbol.charAt(sym), num1, num2);
            String jobID = String.valueOf(r.nextInt(numeroProductores));
            this.buffer.produce(products, jobID);
            
            tareasPorHacer.put(jobID, products);
            System.out.println("Producer produced: " + products);
            
            try {
                Thread.sleep(sleepProductores);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public void EndProc(){
       this.stop = false
    }
    
}
