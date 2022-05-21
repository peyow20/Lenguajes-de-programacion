
package producerconsumer;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;


public class Consumer extends Thread {
    Buffer buffer;
    int numeroConsumidores, sleepConsumidores, id;
    HashMap <String,String> tareasPorHacer;
    HashMap <String,String> tareasRealizadas;
    boolean stop;
    
    
    Consumer(Buffer buffer, int numeroConsumidores, int sleepConsumidores, HashMap <String,String> tareasPorHacer,HashMap <String,String> tareasRealizadas, int id, boolean stop) {
        this.buffer = buffer;
        this.numeroConsumidores = numeroConsumidores;
        this.sleepConsumidores = sleepConsumidores;
        this.tareasPorHacer = tareasPorHacer;
        this.tareasRealizadas = tareasRealizadas;
        this.id = id;
        this.stop = true;
   }
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        System.out.println(numeroConsumidores);
        while(stop) {
            
            product = this.buffer.consume();
            System.out.println("Consumer consumed: " + product);
            
            try {
                Thread.sleep(sleepConsumidores);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void ApagarProcesos(){
        this.stop = false;
    }
}
