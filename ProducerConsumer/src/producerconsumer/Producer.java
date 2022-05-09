
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;

public class Producer extends Thread {
    Buffer buffer;
    int numeroProductores, sleepProductores;
    HashMap <String,String> tareasPorHacer;
    HashMap <String,String> tareasRealizadas;
    
    Producer(Buffer buffer, int numeroProductores, int sleepProductores, HashMap <String,String> tareasPorHacer,HashMap <String,String> tareasRealizadas ) {
        this.buffer = buffer;
        this.numeroProductores = numeroProductores;
        this.sleepProductores = sleepProductores;
        this.tareasPorHacer = tareasPorHacer;
        this.tareasRealizadas = tareasRealizadas;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products = "AEIOU";
        Random r = new Random(System.currentTimeMillis());
        char product;
        
        for(int i=0 ; i<numeroProductores ; i++) {
            product = products.charAt(r.nextInt(numeroProductores));
            this.buffer.produce(product);
            String jobID = String.valueOf(r.nextInt(numeroProductores));
            tareasPorHacer.put(jobID, "String");
            System.out.println("Producer produced: " + product);
            
            try {
                Thread.sleep(sleepProductores);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
