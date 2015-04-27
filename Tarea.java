/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author expsle
 */
public class Tarea {

    /**
     * @param args the command line arguments
     *
     */
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    FileWriter fichero = null;
    PrintWriter pw = null;
    static String fileIn = "";
    static String fileOut ="";
    public void escribir(String palabra, boolean cerrar) {

        try {
            fichero = new FileWriter(fileOut);
            pw = new PrintWriter(fichero);
            pw.println(palabra);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // cerrar fichero
                if (cerrar) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void leer() throws FileNotFoundException {
        try {
            // Apertura del fichero y creacion de BufferedReader 

            archivo = new File(fileIn);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea="";
            String caracter = "";
            String valor = "";
            String errores = "";

            Tarea t = new Tarea();
            String archivo = "";
            int cont = 0;
            
            while ((linea = br.readLine()) != null) {
               
                cont++;
                
                String finDeLinea = "";

                // recorre cada linea
                caracter = "";
                for (int i = 0; i < linea.length(); i++) {
                    

                    // mientras no sea espacio  ,  o "" concatena para obtener el token
                    if (linea.charAt(i) != ' ' && linea.charAt(i) != ',' && linea.charAt(i) != '"') {

                        caracter = caracter + linea.charAt(i);

                    } else if (linea.charAt(i) == ',') {
                        // si el caracter es , se guarda para concatenar al final
                        finDeLinea = "COMA ";

                    }

                    if (linea.charAt(i) == '"' && i != linea.length() - 1) {

                        int contador = i + 1;
                        caracter = caracter + linea.charAt(i);
                        while (contador < linea.length() && linea.charAt(contador) != '"') {
                            
                            caracter = caracter + linea.charAt(contador);
                            contador++;
                        }
                        if (caracter != "") {
                            caracter = caracter + "\"";
                        }
                        i = contador;
                        

                    } else if (linea.charAt(i) == '"' && i == linea.length() - 1) {
                        valor = "Cadena erronea linea : -" + cont;
                        archivo = archivo + valor;

                    }

                    if ((linea.charAt(i) == ' ' || linea.charAt(i) == ',') && !caracter.equals("")) {

                        if ("[".equals(caracter)) {
                            valor = "L_CORCHETE";
                        } else if ("]".equals(caracter)) {
                            valor = "R_CORCHETE";
                        } else if ("{".equals(caracter)) {
                            valor = "L_LLAVE";
                        } else if ("}".equals(caracter)) {
                            valor = "R_LLAVE";
                        } else if (",".equals(caracter)) {
                            valor = ",";
                        } else if (":".equals(caracter)) {
                            valor = "DOS_PUNTOS";
                        } else if ("true".equals(caracter) || "TRUE".equals(caracter)) {
                            valor = "PR_TRUE";
                        } else if ("false".equals(caracter) || "FALSE".equals(caracter)) {
                            valor = "PR_FALSE";
                        } else if ("null".equals(caracter) || "NULL".equals(caracter)) {
                            valor = "PR_NULL";
                        } else if (caracter.charAt(0) == '"' && caracter.charAt(caracter.length() - 1) == '"') {

                            valor = "LITERAL_CADENA";
                        }

                        archivo = archivo + valor + " ";
                        // si se encontro una , se agrega al final
                        if (!finDeLinea.equals("")) {
                            archivo = archivo + " " + finDeLinea;

                        }
                        caracter = "";
                    }

                    if (i == linea.length() - 1 && caracter != "") {
                        if ("[".equals(caracter)) {
                            valor = "L_CORCHETE";
                        } else if ("]".equals(caracter)) {
                            valor = "R_CORCHETE";
                        } else if ("{".equals(caracter)) {
                            valor = "L_LLAVE";
                        } else if ("}".equals(caracter)) {
                            valor = "R_LLAVE";
                        } else if (",".equals(caracter)) {
                            valor = ",";
                        } else if (":".equals(caracter)) {
                            valor = "DOS_PUNTOS";
                        } else if ("true".equals(caracter) || "TRUE".equals(caracter)) {
                            valor = "PR_TRUE";
                        } else if ("false".equals(caracter) || "FALSE".equals(caracter)) {
                            valor = "PR_FALSE";
                        } else if ("null".equals(caracter) || "NULL".equals(caracter)) {
                            valor = "PR_NULL";
                        } else if (caracter.charAt(0) == '"' && caracter.charAt(caracter.length() - 1) == '"') {

                            valor = "LITERAL_CADENA";
                        } else {
                            valor = "ERROR linea ";
                        }

                        archivo = archivo + valor + " ";
                        // si se encontro una , se agrega al final
                        if (!finDeLinea.equals("")) {
                            archivo = archivo + " " + finDeLinea;

                        }
                        caracter = "";
                    }
                }
                
                         
                
// se escribe en el archivo 
                archivo = archivo + "\n";
                t.escribir(archivo, true);

            }
                                archivo = archivo + "EOF";
                t.escribir(archivo, true);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan;
        scan = new Scanner(System.in);
        System.out.println("Ingrese el fichero de salida ");
        fileOut= scan.next();
        System.out.println("Ingrese el fichero de entrada ");
        fileIn= scan.next();
        Tarea t = new Tarea();
        
        t.leer();
        // TODO code application logic here
    }

}
