/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1.compi;

import java.util.List;

/**
 *
 * @author CLARO
 */
public class Simplificacion {
    private String operacion;
    private List<String> leyes;
    private String conjuntoSimplificado;

    // Constructor
    public Simplificacion(String operacion, List<String> leyes, String conjuntoSimplificado) {
        this.operacion = operacion;
        this.leyes = leyes;
        this.conjuntoSimplificado = conjuntoSimplificado;
    }
     // Getters y setters
    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public List<String> getLeyes() {
        return leyes;
    }

    public void setLeyes(List<String> leyes) {
        this.leyes = leyes;
    }

    public String getConjuntoSimplificado() {
        return conjuntoSimplificado;
    }

    public void setConjuntoSimplificado(String conjuntoSimplificado) {
        this.conjuntoSimplificado = conjuntoSimplificado;
    }
}
