/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadeficheros;

import java.util.Scanner;

/**
 *
 * @author Ruben
 */
public class entradaTeclado {

    static Scanner usuario = new Scanner(System.in);
    
    public static String leerString(String s){
        System.out.println(s);
        String c = usuario.nextLine();
        return c;
    }
    
}
