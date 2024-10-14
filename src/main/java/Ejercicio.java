import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio {
    public static void main(String[] args) {
        MiConector miConector = new MiConector();
        Connection con = miConector.getConnection();

        try {
            DatabaseMetaData dbmd= con.getMetaData();
            ResultSet resultSet = dbmd.getTables("starwars",null,null,null);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("TABLE_NAME"));
                ResultSet rs2= dbmd.getPrimaryKeys(null,null,resultSet.getString("TABLE_NAME"));
                ResultSet rs3=dbmd.getImportedKeys(null,null,resultSet.getString("TABLE_NAME"));
                ResultSet rs4=dbmd.getColumns(null,null,resultSet.getString("TABLE_NAME"),null);
                while (rs2.next()) {
                    System.out.println("Clave primaria: "+rs2.getString("COLUMN_NAME"));
                }
                while (rs3.next()) {
                    System.out.println("Clave Externa: "+rs3.getString("FKCOLUMN_NAME")+" de la tabla "+rs3.getString(3)+" con clave primaria "+ rs3.getString("PKCOLUMN_NAME"));
                }
                System.out.println("Columnas");
                while (rs4.next()) {
                    System.out.println("COLUMNA: "+rs4.getString("COLUMN_NAME")+" ("+rs4.getInt("COLUMN_SIZE")+") "+rs4.getString("TYPE_NAME")+" - "+esnullable(rs4.getInt("NULLABLE")));
                }
                System.out.println("---------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String esnullable(int n) {

        if(n==0){
            return "no es nullable";
        }else {
            return "si es nullable";
        }
    }
}
