package Base.db;

import java.net.InetAddress;
public class Calis{
    public static void main(String[] args){
        int con_d=0;
        int con_n=0;
        int i;
        String seg="10.0.0.";
        InetAddress ip=null;
        try{
            for(i=0;i<10; i++){
                ip=InetAddress.getByName( seg+i);
                if( ip. isReachable(250)){
                    System.out.println(seg+""+i+" si esta disponible");
                    con_d++;
                }else{
                    System.out.println(seg+""+i+" no esta disponible");
                    con_n++;
                }
            }
        }catch(Exception e){
        }
        System.out.println( "Estan disponibles "+ con_d);
        System.out.println( "No estan disponibles "+ con_n);

    }
}