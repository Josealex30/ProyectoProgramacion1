/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import BD.ClsBD;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author kamez
 */
public class ClsAcceso {
    public ClsAcceso(){
        Recordar = false;
        Activo = true;
    }
    
    public String Correo;
    public String Password;
    public boolean Recordar;
    public boolean Activo;
    
    
    public int  RecordarUsuario() {
        int posicion = -1;
        
        for(int i = 0; i < ClsBD.jsonAccess.size(); i ++) {
            if(ClsBD.jsonAccess.get(i).Recordar) {
            posicion = i;
            break;
            }
        }
        
        return posicion;
    }
    
    public boolean ValidarAcceso(String correo, String password) {
    for (ClsAcceso usuario : ClsBD.jsonAccess) {
        if (usuario.Correo.equals(correo) && usuario.Password.equals(password)) {
            return true;
        }
    }
    
    return false;
    }
    
    public void MarcarRecordar(int posicionRecordar) {        
        if(posicionRecordar > -1) {
                ClsBD.jsonAccess.get(posicionRecordar).Recordar = false;
        }
        
         for(int i = 0; i < ClsBD.jsonAccess.size(); i ++) {
            if(ClsBD.jsonAccess.get(i).Correo.equals(Correo)) {
                ClsBD.jsonAccess.get(i).Recordar = true;
                break;
            }
        }
    }

/****************************************
     *                                      *
     *   CODIGO PARA GUARDAR DATOS EN TXT   *
     *                                      *
     ****************************************/

    public boolean GuardarDatosMemoria(){
        boolean Guardado = false;
        Gson gson = new Gson();
        String jsonString = gson.toJson(ClsBD.jsonAccess);
        String[] arrSplitFile = this.toString().split("@"); 

        try{
            FileWriter fr = new FileWriter(arrSplitFile[0] + ".txt");
            fr.write(jsonString);
            fr.close();
            Guardado = true;
            ObtenerDatosMemoria();
        }

        catch(Exception e)
        {
            Guardado = false;
        }

        return Guardado;
    }

    /****************************************
     *                                      *
     *   CODIGO PARA CARGAR DATOS DEL TXT   *
     *                                      *
     ****************************************/

    public void ObtenerDatosMemoria(){        
        String[] arrSplitFile = this.toString().split("@"); 
        
        try {
            FileReader reader = new FileReader(arrSplitFile[0] + ".txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            String Result = "";

            while ((line = bufferedReader.readLine()) != null) {
                Result += line;
            }

            reader.close();  
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<ClsAcceso>>(){}.getType();
            ArrayList<ClsAcceso> ListaGuardada = gson.fromJson(Result, listType);
            ClsBD.jsonAccess = ListaGuardada;

        } catch (Exception e) {}
    }
    
}
