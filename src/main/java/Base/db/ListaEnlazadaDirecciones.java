package Base.db;

public class ListaEnlazadaDirecciones
{
    private NodoDireccionIP inicio;

    public ListaEnlazadaDirecciones() {}

    public ListaEnlazadaDirecciones(NodoDireccionIP sig) {
        this.inicio = sig;
    }

    public void insertar(Ips di) {
        NodoDireccionIP nuevo = new NodoDireccionIP(di, inicio);
        inicio = nuevo;
    }

    public void eliminar(Ips di) {
        NodoDireccionIP aux = inicio;
        NodoDireccionIP aux2 = aux;
        while (aux != null)
        {
            aux2 = aux;

            while (aux2.getNext() != null){
                if(!aux.getDireccionIP().equals(di)){
                    aux2 = aux;

                }else {
                    aux2.setSig(aux2.getNext().getNext());
                }
            }
            aux = aux.getNext();
        }
    }

    public void eliminarLista() {
        NodoDireccionIP aux = inicio;
        while (aux != null)
        {
            aux = aux.getNext();
            inicio.setSig(null);
            inicio = aux;
        }
    }

    public int encontrarDireccion(String direccionIP)
    {
        NodoDireccionIP aux = inicio;
        int contador = 0;
        int posicion = 0;

        while ( contador != size() )
        {
            if( !aux.getDireccionIP().getIp().equals(direccionIP) )
            {
                aux = aux.getNext();
                posicion++;
                contador++;
            }
            else {
                contador = size();
            }
        }
        return posicion;
    }

    public Ips getNodoDeLista(int position) {
        NodoDireccionIP aux = inicio;
        Ips direccionIP;
        int contador = 0;

        while (aux != null) {
            if (contador == position) {
                direccionIP = aux.getDireccionIP();
                return  direccionIP;
            }
            contador++;
            aux = aux.getNext();
        }
        return null;
    }

    public int size() {
        NodoDireccionIP aux = inicio;
        int contador = 0;

        while (aux != null) {
            aux = aux.getNext();
            contador++;
        }
        return contador;
    }

}
