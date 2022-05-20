
package producerconsumer;
import java.util.HashMap;



public class ProducerConsumer extends Producer {
    
    protected int numeroConsumidores, numeroProductores, sleepProductores, sleepConsumidores, sizeBuffer, rangoValores;
    protected String operadores;
    protected Buffer buffer;
    protected Consumer consumer;
    protected Producer producer;
    protected HashMap <String,String> tareasPorHacer;
    protected HashMap <String,String> tareasRealizadas;
    protected int id;
    protected boolean stop = true;
    
    public void setStop(boolean stop)
    {
        this.stop = stop;
    }
    public void setoperadores(String operadores){
        this.operadores = operadores;
    }
    public void setsizeBuffer(int sizeBuffer)
    {
        this.sizeBuffer = sizeBuffer;
    }
    public void setrangoValores(int rangoValores)
    {
        this.rangoValores = rangoValores;
    }
    public void setnumeroConsumidores(int numeroConsumidores){
        this.numeroConsumidores = numeroConsumidores;
    }
    
    public void setnumeroProductores(int numeroProductores){
        this.numeroProductores = numeroProductores;
    }
    public void setsleepProductores(int sleepProductores){
        this.sleepProductores = sleepProductores;
    }
    public void setsleepConsumidores(int sleepConsumidores){
        this.sleepConsumidores = sleepConsumidores;
    }
    public int getnumeroConsumidores()
    {
        return this.numeroConsumidores;
    }
    
    public int getnumeroProductores()
    {
        return this.numeroProductores;
    }
    public int getsleepConsumidores()
    {
        return this.sleepConsumidores;
    }
    public int getsleepProductores()
    {
        return this.sleepProductores;
    }
        public int getSizeBuffer()
    {
         return this.sizeBuffer;
    }
    public int getrangoValores()
    {
        return this.rangoValores;
    }  
    public String getoperadores()
    {
        return this.operadores;
    }
    public boolean getStop()
    {
        return this.stop;
    }
    
    ProducerConsumer(){
            
    }

    public void start() {
    
     tareasPorHacer = new HashMap <String,String>();
     tareasRealizadas = new HashMap <String,String>();
     
     buffer = new Buffer();
        
     producer = new Producer(buffer, this.numeroProductores, this.sleepProductores, tareasPorHacer, tareasRealizadas, this.stop);
        producer.start();
        
     consumer = new Consumer(buffer, this.numeroConsumidores, this.sleepConsumidores, tareasPorHacer, tareasRealizadas, id, this.stop);
        consumer.start();
    }

      
}
