package proyecto1.compi;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jflex.Main;
import java.io.IOException;
import proyecto1.compi.Interfaz;

/**
 *
 * @author CLARO
 */
public class Proyecto1Compi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new Interfaz().setVisible(true);

        String rutaLexico = "C:/Users/CLARO/Documents/segundo semestre 2024/compi/Proyecto1 Compi/src/proyecto1/compi/Lexico.flex";
        String[] rutaSintactico = {"-parser", "Sintactico", "C:/Users/CLARO/Documents/segundo semestre 2024/compi/Proyecto1 Compi/src/proyecto1/compi/Sintactico.cup"};
        generar(rutaLexico, rutaSintactico);
    }

    public static void generar(String rutaLexico, String[] rutaSintactico) throws IOException, Exception {
        String[] rutaArray = {rutaLexico};

        try {
            // Ejecutar JFlex para generar el analizador léxico
            Main.generate(rutaArray);

            // Ejecutar CUP para generar el parser
            java_cup.Main.main(rutaSintactico);

            // Mover archivos generados por CUP si existen
            Path rutaS = Paths.get("C:/Users/CLARO/Documents/segundo semestre 2024/compi/Proyecto1 Compi/src/proyecto1/compi/Sintactico.java");
            Path generatedSintactico = Paths.get("C:/Users/CLARO/Documents/segundo semestre 2024/compi/Proyecto1 Compi/Sintactico.java");

            if (Files.exists(generatedSintactico)) {
                if (Files.exists(rutaS)) {
                    Files.delete(rutaS);
                }
                Files.move(generatedSintactico, rutaS);
            } else {
                System.err.println("Error: Sintactico.java no fue generado");
            }

            Path rutaSym = Paths.get("C:/Users/CLARO/Documents/segundo semestre 2024/compi/Proyecto1 Compi/src/proyecto1/compi/Sym.java");
            Path generatedSym = Paths.get("C:/Users/CLARO/Documents/segundo semestre 2024/compi/Proyecto1 Compi/Sym.java");

            if (Files.exists(generatedSym)) {
                if (Files.exists(rutaSym)) {
                    Files.delete(rutaSym);
                }
                Files.move(generatedSym, rutaSym);
            } else {
                System.err.println("Error: Sym.java no fue generado");
            }

        } catch (Exception e) {
            e.printStackTrace();  // Mostrar el error en la consola para depuración
        }
    }
}
