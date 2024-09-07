import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import proyecto1.compi.Simplificacion;
import proyecto1.compi.Simplificador;

public class Generador{

    // Método para generar el archivo JSON
    public void generarJson(Map<String, Simplificacion> simplificaciones) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("resultado.json")) {
            gson.toJson(simplificaciones, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Ejemplos de simplificaciones
        Simplificacion op1 = new Simplificacion(
                "op1",
                List.of(
                        "Expresión reordenada: ^(^impares & \"pares\")",
                        "Leyes aplicadas: Ley de De Morgan aplicada: (A & B) → ^A U ^B",
                        "Ley del doble complemento aplicada: ^^A → A",
                        "Expresión simplificada: (impares U pares)"
                ),
                "Expresión simplificada: (impares U pares)"
        );

        Simplificacion op2 = new Simplificacion(
                "op2",
                List.of(
                        "Expresión reordenada: ^((^naturales & naturales) U (^pares & ^pares))",
                        "Leyes aplicadas: Propiedad Idempotente aplicada: A & A → A",
                        "Ley de De Morgan aplicada: (A U B) → ^A & ^B",
                        "Ley del doble complemento aplicada: ^^A → A",
                        "Expresión simplificada: (naturales & pares)"
                ),
                "Expresión simplificada: (naturales & pares)"
        );

        Simplificacion op3 = new Simplificacion(
                "op3",
                List.of(
                        "Expresión reordenada: ^^((impares U impares) U (naturales & impares))",
                        "Leyes aplicadas: Ley del doble complemento aplicada: ^^A → A",
                        "Propiedad Idempotente aplicada: A U A → A",
                        "Propiedad de Absorción aplicada: A U (A & B) → A",
                        "Expresión simplificada: impares"
                ),
                "Expresión simplificada: impares"
        );

        // Crear mapa de simplificaciones
        Map<String, Simplificacion> simplificaciones = new HashMap<>();
        simplificaciones.put(op1.getOperacion(), op1);
        simplificaciones.put(op2.getOperacion(), op2);
        simplificaciones.put(op3.getOperacion(), op3);

        // Generar JSON
        Generador generador = new Generador();
        generador.generarJson(simplificaciones);
    }
}
