
package producerconsumer;
import java.util.HashMap;


public class ProducerConsumer {
    
    private int numeroConsumidores, numeroProductores, sleepProductores, sleepConsumidores, tamañoBuffer, rangoValores;
    private String operadores;
    private Buffer buffer;
    private Consumer consumer;
    private Producer producer;
    private HashMap <String,String> tareasPorHacer;
    private HashMap <String,String> tareasRealizadas;
    
    
    public void setoperadores(String operadores){
        this.operadores = operadores;
    }
    public void settamañoBuffer(int tamañoBuffer)
    {
        this.tamañoBuffer = tamañoBuffer;
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
        public int gettamañoBuffer()
    {
         return this.tamañoBuffer;
    }
    public int getrangoValores()
    {
        return this.rangoValores;
    }  
    public String getoperadores()
    {
        return this.operadores;
    }
    
    ProducerConsumer(){
            
    }
    public void start() {
     tareasPorHacer = new HashMap <String,String>();
     tareasRealizadas = new HashMap <String,String>();
     
     buffer = new Buffer();
        
     producer = new Producer(buffer, this.numeroProductores, this.sleepProductores, tareasPorHacer, tareasRealizadas);
        producer.start();
        
     consumer = new Consumer(buffer, this.numeroConsumidores, this.sleepConsumidores, tareasPorHacer, tareasRealizadas);
        consumer.start();
    }
    public void stop(){
        
        producer.stop();
        consumer.stop();
    }
}
