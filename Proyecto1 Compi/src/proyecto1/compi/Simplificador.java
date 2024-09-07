/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1.compi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CLARO
 */
public class Simplificador {
    public Simplificacion simplificarOperacion(String nombreOperacion, String expresionOperacion) {
        // Ejemplo básico de simplificación
        List<String> leyes = new ArrayList<>();
        String conjuntoSimplificado = "";

        if (expresionOperacion.contains("^^^")) {
            leyes.add("Ley de doble complemento");
            conjuntoSimplificado = "^{conjA}";
        } else {
            conjuntoSimplificado = "No se puede simplificar la operacion";
        }

        return new Simplificacion(nombreOperacion, leyes, conjuntoSimplificado);
    }
}
