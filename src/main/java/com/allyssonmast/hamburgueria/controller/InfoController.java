package com.allyssonmast.hamburgueria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping
    public ResponseEntity<?> info() {

        return ResponseEntity.ok(
                Map.of(
                        "sistema",
                        "Hamburgueria API",

                        "versao",
                        "1.0"
                )
        );
    }
}