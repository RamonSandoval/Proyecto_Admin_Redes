package Base.db;

public class Ips {
    String ip;
    String estatus;

    public Ips(){
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Ips(String ip, String estatus) {
        this.ip = ip;
        this.estatus = estatus;
    }


}
