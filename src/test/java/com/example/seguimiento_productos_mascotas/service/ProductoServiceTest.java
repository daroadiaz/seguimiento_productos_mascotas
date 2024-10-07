package com.example.seguimiento_productos_mascotas.service;

import com.example.seguimiento_productos_mascotas.model.Producto;
import com.example.seguimiento_productos_mascotas.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductos() {
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        List<Producto> productos = productoService.getAllProductos();

        assertEquals(2, productos.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void getProductoById() {
        Producto producto = new Producto();
        producto.setId(1L);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> result = productoService.getProductoById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void saveProducto() {
        Producto producto = new Producto();
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto savedProducto = productoService.saveProducto(producto);

        assertNotNull(savedProducto);
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void deleteProducto() {
        productoService.deleteProducto(1L);
        verify(productoRepository, times(1)).deleteById(1L);
    }
}