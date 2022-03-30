package com.example.back.web.controller;



import com.example.back.dao.ProductDao;
import com.example.back.model.Product;
import com.example.back.web.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 60)
public class ProductController {

    @Autowired
    ProductDao productDao;


    @GetMapping(value = "/products")
    public List<Product> findAll() {

        List<Product> products = productDao.findAll();

        if (products.isEmpty()) throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");

        return products;

    }


    @GetMapping(value = "/products/{id}")
    public Optional<Product> findById(@PathVariable int id) {

        Optional<Product> product = productDao.findById(id);

        if (!product.isPresent())
            throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");

        return product;
    }


    @PostMapping(value = "/products")
    public ResponseEntity<Product> add(@RequestBody Product product){

        Product p = productDao.save(product);

        //if(p == null) throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Product>(p, HttpStatus.CREATED);
    }
}

