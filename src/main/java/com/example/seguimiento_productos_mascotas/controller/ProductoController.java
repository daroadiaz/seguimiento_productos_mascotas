package com.example.seguimiento_productos_mascotas.controller;

import com.example.seguimiento_productos_mascotas.model.Producto;
import com.example.seguimiento_productos_mascotas.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public CollectionModel<EntityModel<Producto>> getAllProductos() {
        List<EntityModel<Producto>> productos = productoService.getAllProductos().stream()
            .map(producto -> EntityModel.of(producto,
                linkTo(methodOn(ProductoController.class).getProductoById(producto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos")))
            .collect(Collectors.toList());

        return CollectionModel.of(productos, 
            linkTo(methodOn(ProductoController.class).getAllProductos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id)
            .map(producto -> EntityModel.of(producto,
                linkTo(methodOn(ProductoController.class).getProductoById(id)).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos")))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Producto>> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.saveProducto(producto);
        EntityModel<Producto> entityModel = EntityModel.of(nuevoProducto,
            linkTo(methodOn(ProductoController.class).getProductoById(nuevoProducto.getId())).withSelfRel(),
            linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos"));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        return productoService.getProductoById(id)
            .map(producto -> {
                producto.setNombre(productoDetails.getNombre());
                producto.setDescripcion(productoDetails.getDescripcion());
                producto.setPrecio(productoDetails.getPrecio());
                Producto updatedProducto = productoService.saveProducto(producto);
                EntityModel<Producto> entityModel = EntityModel.of(updatedProducto,
                    linkTo(methodOn(ProductoController.class).getProductoById(updatedProducto.getId())).withSelfRel(),
                    linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("productos"));
                return ResponseEntity.ok(entityModel);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        return productoService.getProductoById(id)
            .map(producto -> {
                productoService.deleteProducto(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}