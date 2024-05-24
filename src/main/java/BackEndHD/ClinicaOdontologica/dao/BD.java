package BackEndHD.ClinicaOdontologica.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {
    private static final Logger logger = Logger.getLogger(BD.class);
    private static final String SQL_DROP_CREATE_ODONT = "DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY, MATRICULA INT NOT NULL, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL)";

    private static final String SQL_DROP_CREATE_PAC="DROP TABLE IF EXISTS PACIENTES; CREATE TABLE PACIENTES (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL, " +
            "CEDULA VARCHAR(100) NOT NULL, FECHA_INGRESO DATE NOT NULL, DOMICILIO_ID INT NOT NULL, EMAIL VARCHAR(100) NOT NULL )"; //<<-- FK
    private static final String SQL_DROP_CREATE_DOM="DROP TABLE IF EXISTS DOMICILIOS; " +
            "CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY, CALLE VARCHAR(100) NOT NULL, NUMERO INT NOT NULL, LOCALIDAD VARCHAR(100) NOT NULL, PROVINCIA VARCHAR(100) NOT NULL)";

    private static final String SQL_PRUEBA="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES ('Jorgito','Pereyra','111111','2024-05-16', 1, 'jorgito@jorgito.com'), ('German','Fraire','22222','2024-05-10',2, 'german@german.com'); " +
            "INSERT INTO DOMICILIOS  (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES ('Siempre Viva',742,'Springfield','USA'),('Av. Uruguay',345,'Punta del Este','Uruguay')";
    private static final String SQL_PRUEBA_ODONT="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES" +
            "(111,'Andres','Cardenas'), (222, 'Mariana', 'Ampudia'), (333, 'Monica', 'Gonzalez')";

    public static void crearTablas(){
        Connection connection = null;

        try{
            connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(SQL_DROP_CREATE_ODONT);
            stmt.execute(SQL_DROP_CREATE_DOM);
            stmt.execute(SQL_DROP_CREATE_PAC);
            stmt.execute(SQL_PRUEBA);
            stmt.execute(SQL_PRUEBA_ODONT);
            logger.info("Tablas creada con exito");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:~/clinicaOdonto", "sa", "");
    }
}
