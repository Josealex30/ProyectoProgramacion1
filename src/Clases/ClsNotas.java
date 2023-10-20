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
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author vinva
 */
public class ClsNotas {
    public String titulo;
    public String descripcion;
    public boolean check;
    public String email;


public boolean GuardarNotas() {
    boolean Guardado = false;
    Gson gson = new Gson();
    String jsonString = gson.toJson(ClsBD.jsonNotas);

    try {
        FileWriter fr = new FileWriter("Notas.txt");
        fr.write(jsonString);
        fr.close();
        Guardado = true;
        System.out.println("Guardado");
    } catch (IOException e) {
        System.out.println("No guardado");
        Guardado = false;
    }

    return Guardado;
}

public ClsNotas ObtenerDatosNotas() {
    ClsNotas datosCargados = null;
    try {
        FileReader reader = new FileReader("Notas.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        String Result = "";

        while ((line = bufferedReader.readLine()) != null) {
            Result += line;
        }

        reader.close();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ClsNotas>>(){}.getType();
        ArrayList<ClsNotas> ListaGuardada = gson.fromJson(Result, listType);

        if (!ListaGuardada.isEmpty()) {
            datosCargados = ListaGuardada.get(0); // Supongo que solo quieres el primer elemento
            ClsBD.jsonNotas = ListaGuardada;
        }
    } catch (Exception e) {
        System.out.println("Problemas al leer la data.");
    }

    return datosCargados;
}
}




