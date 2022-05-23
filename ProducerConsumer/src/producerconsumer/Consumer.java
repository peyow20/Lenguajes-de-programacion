
package producerconsumer;


import java.util.logging.Level;
import java.util.logging.Logger;


public class Consumer extends Thread {
    //Variables de Consumer
    Buffer buffer;
    private final int sleepConsumidores,idConsu;
    private boolean stop;
    //Constructor de Consumer
    Consumer(Buffer buffer, int idConsu, int sleepConsumidores) {
        this.buffer = buffer;
        this.sleepConsumidores = sleepConsumidores;
        this.idConsu = idConsu; 
        this.stop = true;
    }
 
    @Override
    public void run() {
        System.out.println("Running Consumer...");

        while (stop) {
            //Llama el metodo de consume en la clase Buffer
            this.buffer.consume(idConsu);
            try {
                Thread.sleep(sleepConsumidores);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //Stop de Consumer
    public void Apagar(){
        this.stop = false;
    }
}
