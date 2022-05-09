package Base.db;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crud {
    ConexionBD conexion = new ConexionBD();
    Ips di= new Ips();
    PreparedStatement ps;

    ResultSet rs;

    public boolean create (Ips di)
    {
        String estatus = di.getEstatus();
        String ip = di.getIp();


        String sql = "INSERT INTO `direcciones` (`IP`, `STATUS`) VALUES ('"+ip+"', '"+estatus+"');";

        try
        {
            ps  = (PreparedStatement) conexion.getCon().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        }
        catch(SQLException ex)
        {
            return false;
        }

    }

    public Ips busca (String ip)
    {
        String sql = "SELECT * FROM `direcciones` WHERE `ip` = '"+ip+"';";

        try
        {
            ps  = (PreparedStatement) conexion.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            di.setIp(rs.getString(1));
            di.setEstatus(rs.getString(2));


            return di;
        }
        catch(SQLException e)
        {
            di.setIp("0");
            di.setEstatus("0");

            return di;
        }
    }

    public void actualiza (String estatus, String ip){
        String sqlStatus = "UPDATE `direcciones` SET `STATUS`='"+estatus+"' WHERE `IP`='"+ip+"';";

        try{
            ps = (PreparedStatement) conexion.getCon().prepareStatement(sqlStatus);
            ps.executeUpdate();

        }catch (SQLException e){
            System.out.println("UPDATE_ERROR");
            System.out.println(e.getMessage());
        }
    }

    public boolean elimina (String ip) {
        String sql = "DELETE FROM `direcciones` WHERE `IP` = '"+ip+"';";
        try{
            ps  = (PreparedStatement) conexion.getCon().prepareStatement(sql);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public ListaEnlazadaDirecciones listDirecciones(){
        ListaEnlazadaDirecciones list = new ListaEnlazadaDirecciones();

        String sql = "SELECT * FROM `direcciones`;";
        try{
            rs = conexion.getCon().createStatement().executeQuery(sql);
            rs.first();
            di.setIp(rs.getString(1));
            di.setEstatus(rs.getString(2));

            list.insertar(di);
            Ips di = new Ips();
            while (rs.next()){
                di.setIp(rs.getString(1));
                di.setEstatus(rs.getString(2));

                list.insertar(di);
              di = new Ips();
            }
        }catch (SQLException e){
            System.out.println("listaCRUD");
            System.out.println(e.getMessage());
        }

        return list;
    }

    public String administradores (String user)
    {
        String sql = "SELECT * FROM `usuarios` WHERE `Usuarios` = '"+user+"';";
        String password;

        try
        {
            ps  = (PreparedStatement) conexion.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            password = rs.getString(2);

            return password;
        }
        catch(SQLException e)
        {
            password = "?";
            return password;
        }
    }

}

