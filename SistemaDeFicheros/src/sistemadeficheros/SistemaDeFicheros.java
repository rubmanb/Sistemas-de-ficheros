/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadeficheros;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import static sistemadeficheros.entradaTeclado.leerString;

/**
 *
 * @author Ruben
 */
public class SistemaDeFicheros {

    static final int MAX_FILES_BY_COLUMN = 4;

    static String directorioActual = System.getProperty("user.dir");

    //muestra ficheros en el directorio actual
    public static void modoLista(File f) {
        String[] verLista = f.list();
        if (verLista == null || verLista.length == 0) {
            System.out.println("El directorio está vacío");
        } else {
            for (String verLista1 : verLista) {
                System.out.println(verLista1);
            }
        }
    }

    //muestra un vector de ficheros en columnas
    public static void ListaColumnas(String[] filenames) {
        int columnas = (filenames.length / MAX_FILES_BY_COLUMN) + 1;
        String[][] salida = new String[MAX_FILES_BY_COLUMN][columnas];
        for (int i = 0; i < filenames.length; i++) {
            salida[i % MAX_FILES_BY_COLUMN][i / MAX_FILES_BY_COLUMN] = filenames[i];
        }
//bucle para mostrar salidas
        for (int i = 0; i < MAX_FILES_BY_COLUMN; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(salida[i][j] + " - ");
            }
            System.out.println(" /");
        }
    }

    //muestra el formato de la fecha
    public static String mostrarFecha(long data) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(data));
    }

    //muestra el tipo de fichero y propiedades
    public static boolean atributosFicheros(File f) {
        
        if (!f.exists()) {
            return false;
        } else {
            String c = "";
            //tipo de fichero o directorio
            c += f.isDirectory() ? 'D' : 'F';
            c += "\t";
//            c += f.isFile() ? 'F' : '-';
//            c += "";
            c += f.canRead() ? 'R' : '-';
            c += f.canWrite() ? 'W' : '-';
            c += f.isHidden() ? 'H' : '-';
            //nombre
            c += "\t";
            c += f.getName();
            //tamaño
            c += "\t";
            c += f.length();
            //fecha de modificacion
            c += "\t";
            c += mostrarFecha(f.lastModified());
            System.out.println(c);
            return true;
        }
    }

    //muestra el árbol de un directorio
    public static void listaFicheros(File f, String sep) {
        File vectorFicheros[] = f.listFiles();
        if (vectorFicheros != null) {
            for (File vectorFicher : vectorFicheros) {
                System.out.print(sep);
                atributosFicheros(vectorFicher);
                if (vectorFicher.isDirectory()) {
                    listaFicheros(vectorFicher, sep + "\t");//recursión
                }
            }
        }
    }

    private static void listaFicheros(File f) {
        listaFicheros(f, "\t");
    }

    static void listarDirectorio(File f) {
        if (f.exists()) {
            if (f.isFile()) {
                atributosFicheros(f);
            } else {
                listaFicheros(f);
            }
        } else {
            System.out.println("El fichero no existe");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String ruta = "C:/Users/Ruben/Desktop";
        File f = new File(directorioActual);
        File f1 = new File(ruta);
        String[] listaFicheros = f1.list();
        String separador = "\t";
        System.out.println("LISTADO:");
        modoLista(f);
        System.out.println("\nEN COLUMNAS:");
        ListaColumnas(listaFicheros);
        System.out.println("\nARCHIVOS Y DIRECTORIOS CON PROPIEDADES");
        listarDirectorio(f);
    }

}
