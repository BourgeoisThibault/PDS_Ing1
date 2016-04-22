package app;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Server {

  public static ServerSocket socket = null;
  public static Thread connectionManagerThread;
  public static ConnectionPool[] connectionPool;
  public static int connectionPoolSize;

  public void launch() {

    try {
      socket = new ServerSocket(1234);
      ServerInterface.changeTextLog("Le serveur est en ligne.");
      ServerInterface.changeTextLog("Le serveur est � l'�coute du port "+socket.getLocalPort());
      connectionManagerThread = new Thread(new ConnectionManager(socket));
      connectionManagerThread.start();

      ResourceBundle bundle = ResourceBundle.getBundle("domaine.properties.config");

      connectionPoolSize = Integer.parseInt(bundle.getString("connectionPoolSize"));

      ServerInterface.changeTextLog("Cr�ation du pool:");
      poolGenerator(connectionPoolSize);

      updateConnectionAvailability();

      ServerInterface.changeTextLog(connectionPool.length + " connexions cr��es");


    } catch (IOException e) {
      ServerInterface.changeTextLog("WARNING : Probl�me ouverture du port (" + e.getMessage() + ")");
    }
  }

  private void poolGenerator(int size){
    connectionPool = new ConnectionPool[size];
    for(int i=0;i<size;i++)  {
      connectionPool[i] = new ConnectionPool(i);
      ServerInterface.changeTextLog("Connexion " + i + " cr��e");
    }
  }

  public static Connection getConnection() throws SQLException
  {
    ResourceBundle bundle = ResourceBundle.getBundle("domaine.properties.config");

    String drivers = "org.postgresql.Driver";
    System.setProperty("jdbc.drivers",drivers);
    String url = "jdbc:postgresql://" + bundle.getString("server") + "/" + bundle.getString("dbName");
    String username = bundle.getString("login");
    String password = bundle.getString("password");

    return DriverManager.getConnection(url, username, password);
  }

  public static void updateConnectionAvailability() {
    int connected=0;
    int available=0;

    for(int i=0;i<connectionPool.length;i++)  {
      if (connectionPool[i].isUsed() == true)
        connected++;
      else
        available++;

      ServerInterface.updateInfoLabel(connected, available);
    }
  }
}
