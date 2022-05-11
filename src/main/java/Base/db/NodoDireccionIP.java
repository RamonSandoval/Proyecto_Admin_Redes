package Base.db;

public class NodoDireccionIP
{
    private Ips di;
    private NodoDireccionIP next;

    public NodoDireccionIP(Ips direccionIP, NodoDireccionIP next) {
        this.di = direccionIP;
        this.next = next;
    }

    public Ips getDireccionIP() {
        return di;
    }

    public void setDi(Ips di) {
        this.di = di;
    }

    public NodoDireccionIP getNext() {
        return next;
    }

    public void setNext(NodoDireccionIP sig) {
        this.next= next;
    }
}
