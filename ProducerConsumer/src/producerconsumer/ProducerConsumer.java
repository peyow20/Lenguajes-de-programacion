
package producerconsumer;
import java.util.HashMap;


public class ProducerConsumer {
    
    private int numeroConsumidores, numeroProductores, sleepProductores, sleepConsumidores, tamanoBuffer, valorN, valorM;
    private String operadores;
    private Buffer buffer;
    private Consumer consumer;
    private Producer producer;
    private HashMap <String,String> tareasPorHacer;
    private HashMap <String,String> tareasRealizadas;
    
    
    public void setoperadores(String operadores){
        this.operadores = operadores;
    }
    public void settamanoBuffer(int tamanoBuffer)
    {
        this.tamanoBuffer = tamanoBuffer;
    }
    public void setvalorN(int valorN)
    {
        this.valorN = valorN;
    }
    public void setvalorM(int valorM)
    {
        this.valorM = valorM;
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
        public int gettamanoBuffer()
    {
         return this.tamanoBuffer;
    }
    public int getvalorN()
    {
        return this.valorN;
    }
    public int getvalorM()
    {
        return this.valorM;
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
