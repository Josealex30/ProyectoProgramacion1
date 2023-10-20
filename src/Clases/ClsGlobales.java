/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Forms.FrmPrincipal;

/**
 *
 * @author vinva
 */
public class ClsGlobales {
    public static FrmPrincipal fPrincipal = new FrmPrincipal();
    public static String email;
    
        public static void setEmail(String email) {
        ClsGlobales.email = email;
    }

    public static String getEmail() {
        return email;
    }
}
