import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author El Tremendo Dafnis
 */
public class Conexion {
    Connection conn = null;
    final String url = "jdbc:mysql://localhost:3306";
    final String dbName = "elMaquilon";
    final String driver = "com.mysql.jdbc.Driver";
    final String userName = "root";
    final String password = "";
    public void connect(){
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            if(!conn.isClosed()){
                System.out.println("connect");
            }
        } catch (Exception ex) {
       System.err.println("Error " + ex.getMessage()); 
        } finally{
            try {
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
            }
       }
    }
    
    public void consulta() throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT * FORM EMPLEADO WHERE NOMBRE = ?");
        stmt.setString(1, "Carlos");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            System.out.println("Nombre: "+rs.getString("Nombre"));
            System.out.println("Apellido: "+rs.getString("apellido"));
            System.out.println("Fecha de naciemiento: "+rs.getString("fec_nac"));
            System.out.println("Telefono: "+rs.getString("telefono"));
            System.out.println("Email: "+rs.getString("email"));
            System.out.println("Puesto: "+rs.getString("puesto"));
        }
        rs.close();
        stmt.close();
    }
    public void registrar() throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO EMPLEADO (nombre, apellido, fec_nac, telefono, email, puesto) VALUES (?,?,?,?,?,?)");
        stmt.setString(1, "Marco");
        stmt.setString(2, "Orozco");
        stmt.setString(3, "2015-01-01");
        stmt.setString(4, "614-123-45-67");
        stmt.setString(5, "Carlos@cosa.com");
        stmt.setString(6, "Jefazo");
        int count = stmt.executeUpdate();
        System.out.println("Datos insertado # "+count);
        stmt.close();
    }
    public void baja() throws SQLException{
    PreparedStatement stmt = conn.prepareStatement("DELETE FROM EMPLEADO WHERE ID =?");
    stmt.setString(1, "13"); 
    int count = stmt.executeUpdate();
        System.out.println("Datos eliminados "+count);
        stmt.close();
    }
    public void cambios() throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("UPDATE EMPLEADO SET NOMBRE = ? WHERE ID = ?");
        stmt.setString(1, "Pedro");
        stmt.setString(2, "1");
        int count = stmt.executeUpdate();
        System.out.println("Datos cambiados "+count);
        stmt.close();
    }
    
}
