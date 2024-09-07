/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1.compi;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java_cup.runtime.Symbol;
import javax.swing.JPanel;
import jflex.Main;
import proyecto1.compi.sym;
import proyecto1.compi.Sintactico;



/**
 *
 * @author CLARO
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
    }

 
  private List<TokenInfo> tokenList = new ArrayList<>();


    // Clase interna para representar la información del token
    public class TokenInfo {
    private String tipo;
    private String valor;
    private int linea;
    private int columna;

    public TokenInfo(String tipo, String valor, int linea, int columna) {
        this.tipo = tipo;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }
}

private void generarReporteTokens() {
    String input = TextAreaEntrada.getText();
    List<TokenInfo> tokens = new ArrayList<>();

    // Crear un lexer y analizar el texto
    Lexico lexico = new Lexico(new StringReader(input));
    Symbol token;

    try {
        while ((token = lexico.next_token()) != null) {
            if (token.sym == sym.EOF) {
                break;
            }
            // Convertir el símbolo a Tokens
            Tokens tipoToken = Tokens.values()[token.sym];
            // Convertir Tokens a String
            String tipoTokenStr = tipoToken.name();
            tokens.add(new TokenInfo(tipoTokenStr, token.value.toString(), token.left, token.right));
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error durante el análisis léxico: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Generar el archivo HTML con la tabla de tokens
    File htmlFile = new File("TablaTokens.html");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile))) {
        writer.write("<html>\n<head>\n<title>Reporte de Tokens</title>\n</head>\n<body>\n");
        writer.write("<h1>Reporte de Tokens</h1>\n");
        writer.write("<table border='1'>\n");
        writer.write("<tr><th>Tipo</th><th>Valor</th><th>Línea</th><th>Columna</th></tr>\n");

        for (TokenInfo tokenInfo : tokens) {
            writer.write("<tr>\n");
            writer.write("<td>" + tokenInfo.getTipo() + "</td>\n");
            writer.write("<td>" + tokenInfo.getValor() + "</td>\n");
            writer.write("<td>" + tokenInfo.getLinea() + "</td>\n");
            writer.write("<td>" + tokenInfo.getColumna() + "</td>\n");
            writer.write("</tr>\n");
        }

        writer.write("</table>\n");
        writer.write("</body>\n</html>");
        JOptionPane.showMessageDialog(this, "Reporte de tokens generado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al generar el reporte de tokens: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

public List<ErrorInfo> errores = new ArrayList<>();

public class ErrorInfo {
    private String mensaje;
    private int linea;
    private int columna;

    public ErrorInfo(String mensaje, int linea, int columna) {
        this.mensaje = mensaje;
        this.linea = linea;
        this.columna = columna;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }
}
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaEntrada = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        anterior = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextAreaConsola = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        graficar = new javax.swing.JButton();
        generarJson = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        nuevoArchivo = new javax.swing.JMenuItem();
        guardarComo = new javax.swing.JMenuItem();
        guardar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ejecutarLexico = new javax.swing.JMenuItem();
        ejecutarSintactico = new javax.swing.JMenuItem();
        ejecutar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        reporteTokens = new javax.swing.JMenuItem();
        reporteErrores = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TextAreaEntrada.setColumns(20);
        TextAreaEntrada.setRows(5);
        jScrollPane1.setViewportView(TextAreaEntrada);

        jLabel1.setText("Entrada");

        jLabel2.setText("Gráficas:");

        anterior.setText("Anterior");

        siguiente.setText("Siguiente");

        TextAreaConsola.setColumns(20);
        TextAreaConsola.setRows(5);
        jScrollPane3.setViewportView(TextAreaConsola);

        jLabel3.setText("Consola:");

        graficar.setText("Graficar");
        graficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graficarActionPerformed(evt);
            }
        });

        generarJson.setText("Generar Json");
        generarJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarJsonActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        nuevoArchivo.setText("Nuevo Archivo");
        nuevoArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoArchivoActionPerformed(evt);
            }
        });
        jMenu1.add(nuevoArchivo);

        guardarComo.setText("Guardar Como");
        guardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarComoActionPerformed(evt);
            }
        });
        jMenu1.add(guardarComo);

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jMenu1.add(guardar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ejecutar");

        ejecutarLexico.setText("Ejecutar Léxico");
        ejecutarLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarLexicoActionPerformed(evt);
            }
        });
        jMenu2.add(ejecutarLexico);

        ejecutarSintactico.setText("Ejecutar Sintáctico");
        ejecutarSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarSintacticoActionPerformed(evt);
            }
        });
        jMenu2.add(ejecutarSintactico);

        ejecutar.setText("Ejecutar");
        ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarActionPerformed(evt);
            }
        });
        jMenu2.add(ejecutar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Reportes");

        reporteTokens.setText("Reporte Tokens");
        reporteTokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteTokensActionPerformed(evt);
            }
        });
        jMenu3.add(reporteTokens);

        reporteErrores.setText("Reporte Errores");
        reporteErrores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteErroresActionPerformed(evt);
            }
        });
        jMenu3.add(reporteErrores);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane3)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGap(60, 60, 60)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(anterior)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(siguiente))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generarJson)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graficar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(graficar)
                    .addComponent(generarJson))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(anterior)
                            .addComponent(siguiente))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoArchivoActionPerformed
        // Crear un selector de archivos
    JFileChooser fileChooser = new JFileChooser();
    
    // Filtro para mostrar solo archivos .ca
    fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".ca");
        }

        @Override
        public String getDescription() {
            return "Archivos CA (*.ca)";
        }
    });

    // Abrir el diálogo de selección de archivo
    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            StringBuilder fileContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }

            // Mostrar el contenido en el JTextArea "ConsolaEntrada"
            TextAreaEntrada.setText(fileContent.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
        archivoActual = selectedFile;
    }
    }//GEN-LAST:event_nuevoArchivoActionPerformed

    private void ejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarActionPerformed
     TextAreaConsola.setText("");

// Obtener el texto de entrada del TextArea
String input = TextAreaEntrada.getText();

try {
    Lexico lexico = new Lexico(new StringReader(input));
    
    TextAreaConsola.append("Iniciando análisis léxico...\n");
    Symbol token;
    boolean errorEncontrado = false;
    while ((token = lexico.next_token()) != null) {
        if (token.sym == sym.EOF) {
            break;
        }
        if (token.sym == sym.error) {
            TextAreaConsola.append("Error léxico: " + token.value + " en línea: " + token.left + ", columna: " + token.right + "\n");
            errorEncontrado = true;
        } else {
            TextAreaConsola.append("Token: " + token.value + ", Tipo: " + sym.terminalNames[token.sym] + 
                                   ", Línea: " + token.left + ", Columna: " + token.right + "\n");
        }
    }

    // Si hubo errores léxicos, no proceder al análisis sintáctico
    if (errorEncontrado) {
        TextAreaConsola.append("Errores léxicos encontrados. El análisis sintáctico no se realizará.\n");
        return;
    }

    // Crear una instancia del analizador sintáctico con el lexer
    TextAreaConsola.append("Iniciando análisis sintáctico...\n");
    Sintactico sintactico = new Sintactico(lexico); // Asegúrate de usar el nombre correcto de tu parser generado por CUP
    
    try {
        // Ejecutar el análisis sintáctico
        sintactico.parse();
        // Si el análisis sintáctico es exitoso, muestra un mensaje de éxito
        TextAreaConsola.append("Análisis sintáctico completado exitosamente.\n");
    } catch (Exception e) {
        // Mostrar cualquier error que ocurra durante el análisis sintáctico
        TextAreaConsola.append("Error durante el análisis sintáctico: " + e.getMessage() + "\n");
        e.printStackTrace(); // Imprimir el stack trace para detalles adicionales
    }
} catch (Exception e) {
    // Mostrar cualquier error que ocurra durante la creación del lexer o el análisis léxico
    TextAreaConsola.append("Error durante la ejecución del análisis léxico: " + e.getMessage() + "\n");
    e.printStackTrace(); // Imprimir el stack trace para detalles adicionales
}

    }//GEN-LAST:event_ejecutarActionPerformed

    private void guardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarComoActionPerformed

    JFileChooser fileChooser = new JFileChooser();
    
    fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".ca");
        }

        @Override
        public String getDescription() {
            return "Archivos CA (*.ca)";
        }
    });

    int result = fileChooser.showSaveDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();

        if (!selectedFile.getName().toLowerCase().endsWith(".ca")) {
            selectedFile = new File(selectedFile.getAbsolutePath() + ".ca");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
            
            writer.write(TextAreaEntrada.getText());
            writer.flush();
            JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        archivoActual = selectedFile;
    }
    }//GEN-LAST:event_guardarComoActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
       if (archivoActual != null) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoActual))) {
            // Guardar el contenido del JTextArea en el archivo actual
            writer.write(TextAreaEntrada.getText());
            writer.flush();
            JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Si no hay un archivo actual, actuar como "Guardar como"
        guardarComoActionPerformed(evt);
    }
    }//GEN-LAST:event_guardarActionPerformed

    private void ejecutarLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarLexicoActionPerformed
   
   TextAreaConsola.setText("");
        tokenList.clear();  // Limpiar la lista antes de empezar

        String input = TextAreaEntrada.getText();

        try {
            Lexico lexico = new Lexico(new StringReader(input));
            Symbol token;
            boolean errorEncontrado = false;

            TextAreaConsola.append("Iniciando análisis léxico...\n");
            while ((token = lexico.next_token()) != null) {
                if (token.sym == sym.EOF) {
                    break;
                }
                if (token.sym == sym.error) {
                    TextAreaConsola.append("Error léxico: " + token.value + " en línea: " + token.left + ", columna: " + token.right + "\n");
                    errorEncontrado = true;
                } else {
                    String tokenTipo = sym.terminalNames[token.sym];
                    TextAreaConsola.append("Token: " + token.value + ", Tipo: " + tokenTipo + 
                                           ", Línea: " + token.left + ", Columna: " + token.right + "\n");

                    // Agregar token a la lista
                   tokenList.add(new TokenInfo(tokenTipo, token.value.toString(), 
                                             token.left, 
                                             token.right));
                }
            }

            if (errorEncontrado) {
                TextAreaConsola.append("Errores léxicos encontrados. El análisis sintáctico no se realizará.\n");
                return;
            }

            // Continuar con el análisis sintáctico
            TextAreaConsola.append("Iniciando análisis sintáctico...\n");
            Sintactico sintactico = new Sintactico(lexico);
            sintactico.parse();
            TextAreaConsola.append("Análisis sintáctico completado exitosamente.\n");
        } catch (Exception e) {
            TextAreaConsola.append("Error durante la ejecución del análisis léxico: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }//GEN-LAST:event_ejecutarLexicoActionPerformed

    private void ejecutarSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarSintacticoActionPerformed
      
        TextAreaConsola.setText("");
    
    // Obtener el texto de entrada del TextArea
    String input = TextAreaEntrada.getText();
   
    try {
        // Crear una instancia del analizador léxico con el texto de entrada
        Lexico lexico = new Lexico(new StringReader(input));
        
        // Crear una instancia del analizador sintáctico con el lexer
        Sintactico sintactico = new Sintactico(lexico);
        
        
        sintactico.parse();
        
        // Si el análisis es exitoso, muestra un mensaje de éxito
        TextAreaConsola.append("Análisis sintáctico completado exitosamente.\n");
        
    } catch (Exception e) {
        // Mostrar cualquier error que ocurra durante el análisis sintáctico
        TextAreaConsola.append("Error al ejecutar análisis sintáctico: " + e.getMessage() + "\n");
    }
    
        
    }//GEN-LAST:event_ejecutarSintacticoActionPerformed

    private void reporteTokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteTokensActionPerformed
        
        generarReporteTokens();

      
    }//GEN-LAST:event_reporteTokensActionPerformed

    private void reporteErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteErroresActionPerformed
        
    if (errores.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No hay errores para reportar.", "Reporte de Errores", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    File archivo = new File("TablaErrores.html");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
        writer.write("<html>\n");
        writer.write("<head><title>Reporte de Errores</title></head>\n");
        writer.write("<body>\n");
        writer.write("<h1>Reporte de Errores</h1>\n");
        writer.write("<table border='1'>\n");
        writer.write("<tr><th>Mensaje</th><th>Línea</th><th>Columna</th></tr>\n");

        for (ErrorInfo error : errores) {
            writer.write("<tr>");
            writer.write("<td>" + error.getMensaje() + "</td>");
            writer.write("<td>" + error.getLinea() + "</td>");
            writer.write("<td>" + error.getColumna() + "</td>");
            writer.write("</tr>\n");
        }

        writer.write("</table>\n");
        writer.write("</body>\n");
        writer.write("</html>\n");

        JOptionPane.showMessageDialog(this, "Reporte de errores generado exitosamente.", "Reporte de Errores", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

        
    }//GEN-LAST:event_reporteErroresActionPerformed

    private void graficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graficarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_graficarActionPerformed

    private void generarJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarJsonActionPerformed
  
        
    }//GEN-LAST:event_generarJsonActionPerformed

 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    //variable archivoActual
private File archivoActual = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TextAreaConsola;
    private javax.swing.JTextArea TextAreaEntrada;
    private javax.swing.JButton anterior;
    private javax.swing.JMenuItem ejecutar;
    private javax.swing.JMenuItem ejecutarLexico;
    private javax.swing.JMenuItem ejecutarSintactico;
    private javax.swing.JButton generarJson;
    private javax.swing.JButton graficar;
    private javax.swing.JMenuItem guardar;
    private javax.swing.JMenuItem guardarComo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuItem nuevoArchivo;
    private javax.swing.JMenuItem reporteErrores;
    private javax.swing.JMenuItem reporteTokens;
    private javax.swing.JButton siguiente;
    // End of variables declaration//GEN-END:variables

    
    
}

