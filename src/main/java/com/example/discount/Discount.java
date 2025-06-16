package com.example.discount;

/**
 * Utilidades para aplicar distintos tipos de descuento.
 */
public class Discount {

    /**
     * Retorna el precio tras aplicar un descuento porcentual.
     *
     * @param cost    Precio original (>= 0).
     * @param percent Descuento en porcentaje (0–100).
     * @return Precio con descuento aplicado.
     * @throws IllegalArgumentException si cost < 0 o percent fuera de [0,100].
     */
public static double applyDiscount(double cost, double percent) {
    if (cost < 0) {
        throw new IllegalArgumentException("Cost must be non-negative.");
    }
    if (percent < 0 || percent > 100) {
        throw new IllegalArgumentException("Percent must be between 0 and 100.");
    }
    return cost * (1 - percent / 100);
}

    /**
     * Retorna el precio tras aplicar un descuento fijo.
     *
     * @param cost     Precio original (>= 0).
     * @param discount Monto fijo de descuento (>= 0 y <= cost).
     * @return Precio con descuento aplicado.
     * @throws IllegalArgumentException si cost < 0, discount < 0 o discount > cost.
     */
    public static double applyFixedDiscount(double cost, double discount) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost must be non-negative.");
        }
        if (discount < 0) {
            throw new IllegalArgumentException("Discount must be non-negative.");
        }
        if (discount > cost) {
            throw new IllegalArgumentException("Discount cannot exceed the cost.");
        }
        return cost - discount;
    }

    /**
     * Aplica un descuento porcentual primero y luego un descuento fijo.
     *
     * @param cost    Precio original (>= 0).
     * @param percent Descuento porcentual (0–100).
     * @param fixed   Descuento fijo (>= 0 y <= precio intermedio).
     * @return Precio final tras ambos descuentos.
     * @throws IllegalArgumentException si algún parámetro está fuera de rango.
     */
    public static double applyAccumulatedDiscount(double cost, double percent, double fixed) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost must be non-negative.");
        }
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Percent must be between 0 and 100.");
        }
        double intermediate = cost * (1 - percent / 100);
        if (fixed < 0) {
            throw new IllegalArgumentException("Fixed discount must be non-negative.");
        }
        if (fixed > intermediate) {
            throw new IllegalArgumentException("Fixed discount cannot exceed the intermediate price.");
        }
        return intermediate - fixed;
    }

    /**
     * Imprime el precio final por consola.
     *
     * @param tipoDescuento Tipo de descuento aplicado.
     * @param precio        Precio final.
     */
    public static void showFinalPrice(String tipoDescuento, double precio) {
        System.out.printf(
            "El precio final después de aplicar el descuento (%s) es: $%.2f%n",
            tipoDescuento, precio
        );
    }
}
