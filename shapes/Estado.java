import javax.swing.JOptionPane;

public class Estado {
    private int misionerosOrilla1;
    private int canibalesOrilla1;
    private int misionerosOrilla2;
    private int canibalesOrilla2;
    private boolean barcaEnOrilla1; // true si la barca está en la orilla 1, false si está en la orilla 2

    public Estado(int misionerosOrilla1, int canibalesOrilla1, int misionerosOrilla2, int canibalesOrilla2, boolean barcaEnOrilla1) {
        this.misionerosOrilla1 = misionerosOrilla1;
        this.canibalesOrilla1 = canibalesOrilla1;
        this.misionerosOrilla2 = misionerosOrilla2;
        this.canibalesOrilla2 = canibalesOrilla2;
        this.barcaEnOrilla1 = barcaEnOrilla1;
    }

    // Métodos para subir personas a la barca
    public void subirPersonas(int misioneros, int canibales) {
        if (barcaEnOrilla1 && esAccionValida(misioneros, canibales, misionerosOrilla1, canibalesOrilla1)) {
            misionerosOrilla1 -= misioneros;
            canibalesOrilla1 -= canibales;
            mostrarEstado();
        } else if (!barcaEnOrilla1 && esAccionValida(misioneros, canibales, misionerosOrilla2, canibalesOrilla2)) {
            misionerosOrilla2 -= misioneros;
            canibalesOrilla2 -= canibales;
            mostrarEstado();
        } else {
            mostrarError("No es posible subir esa cantidad de personas.");
        }
    }

    // Método para mover la barca de una orilla a otra
    public void moverBarca() {
        if (barcaEnOrilla1 && esAccionValida(0, 0, misionerosOrilla2, canibalesOrilla2)) {
            barcaEnOrilla1 = false;
            mostrarEstado();
        } else if (!barcaEnOrilla1 && esAccionValida(0, 0, misionerosOrilla1, canibalesOrilla1)) {
            barcaEnOrilla1 = true;
            mostrarEstado();
        } else {
            mostrarError("No es posible mover la barca a esa orilla.");
        }
    }

    // Método para bajar personas de la barca
    public void bajarPersonas(int misioneros, int canibales) {
        if (barcaEnOrilla1 && esAccionValida(misioneros, canibales, 3 - misionerosOrilla1, 3 - canibalesOrilla1)) {
            misionerosOrilla1 += misioneros;
            canibalesOrilla1 += canibales;
            mostrarEstado();
        } else if (!barcaEnOrilla1 && esAccionValida(misioneros, canibales, 3 - misionerosOrilla2, 3 - canibalesOrilla2)) {
            misionerosOrilla2 += misioneros;
            canibalesOrilla2 += canibales;
            mostrarEstado();
        } else {
            mostrarError("No es posible bajar esa cantidad de personas.");
        }
    }

    // Método para verificar si una acción es válida
    private boolean esAccionValida(int misioneros, int canibales, int misionerosEnOrilla, int canibalesEnOrilla) {
        int misionerosRestantes = misionerosEnOrilla - misioneros;
        int canibalesRestantes = canibalesEnOrilla - canibales;
        return (misionerosRestantes >= 0 && canibalesRestantes >= 0 && (misionerosRestantes == 0 || misionerosRestantes >= canibalesRestantes));
    }

    // Método para mostrar el estado actual
    private void mostrarEstado() {
        String mensaje = "Orilla 1: Misioneros = " + misionerosOrilla1 + ", Caníbales = " + canibalesOrilla1 + "\n";
        mensaje += "Orilla 2: Misioneros = " + misionerosOrilla2 + ", Caníbales = " + canibalesOrilla2 + "\n";
        mensaje += "Barca en Orilla 1: " + barcaEnOrilla1;
        JOptionPane.showMessageDialog(null, mensaje, "Estado actual", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para mostrar mensajes de error
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

