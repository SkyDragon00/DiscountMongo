package com.example.discount;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    // ---------- Tests applyDiscount ----------
    @Test
    void testApplyDiscountHappyPath() {
        assertEquals(90.0, Discount.applyDiscount(100.0, 10.0));
        assertEquals(59.9925, Discount.applyDiscount(79.99, 25.0), 1e-6);
    }

    @Test
    void testApplyDiscountEdgeValues() {
        assertEquals(42.0, Discount.applyDiscount(42.0, 0.0));
        assertEquals(0.0, Discount.applyDiscount(42.0, 100.0));
    }

    @Test
    void testApplyDiscountInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> Discount.applyDiscount(-10, 5));
        assertThrows(IllegalArgumentException.class, () -> Discount.applyDiscount(10, -5));
        assertThrows(IllegalArgumentException.class, () -> Discount.applyDiscount(10, 120));
    }

    // ---------- Tests applyFixedDiscount ----------
    @Test
    void testApplyFixedDiscountHappyPath() {
        assertEquals(95.0, Discount.applyFixedDiscount(100.0, 5.0));
        assertEquals(40.0, Discount.applyFixedDiscount(49.99, 9.99), 1e-6);
    }

    @Test
    void testApplyFixedDiscountEdgeValues() {
        assertEquals(42.0, Discount.applyFixedDiscount(42.0, 0.0));
        assertEquals(0.0, Discount.applyFixedDiscount(42.0, 42.0));
    }

    @Test
    void testApplyFixedDiscountInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> Discount.applyFixedDiscount(-10, 5));
        assertThrows(IllegalArgumentException.class, () -> Discount.applyFixedDiscount(10, -5));
        assertThrows(IllegalArgumentException.class, () -> Discount.applyFixedDiscount(10, 20));
    }

    // ---------- Tests applyAccumulatedDiscount ----------
    @Test
    void testApplyAccumulatedDiscountHappyPath() {
        assertEquals(85.0, Discount.applyAccumulatedDiscount(100.0, 10.0, 5.0));
        assertEquals(70.0, Discount.applyAccumulatedDiscount(200.0, 50.0, 30.0));
    }

    @Test
    void testApplyAccumulatedDiscountEdgeValues() {
        assertEquals(42.0, Discount.applyAccumulatedDiscount(42.0, 0.0, 0.0));
        assertThrows(IllegalArgumentException.class,
            () -> Discount.applyAccumulatedDiscount(42.0, 100.0, 5.0));
    }

    @Test
    void testApplyAccumulatedDiscountInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> Discount.applyAccumulatedDiscount(-10, 5, 3));
        assertThrows(IllegalArgumentException.class, () -> Discount.applyAccumulatedDiscount(10, -5, 3));
        assertThrows(IllegalArgumentException.class, () -> Discount.applyAccumulatedDiscount(10, 5, -3));
        assertThrows(IllegalArgumentException.class, () -> Discount.applyAccumulatedDiscount(10, 5, 20));
    }

    // ---------- Test showFinalPrice ----------
    @Test
    void testShowFinalPriceOutput() {
        // Captura la salida por consola
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        Discount.showFinalPrice("porcentaje", 85.0);
        String printed = out.toString();
        assertTrue(
            printed.contains("después de aplicar el descuento (porcentaje) es: $85.00")
        );
    }

    @Test
    void testApplyAccumulatedDiscountPercentTooLarge() {
        assertThrows(IllegalArgumentException.class,
            () -> Discount.applyAccumulatedDiscount(50, 150, 0));
    }

}
