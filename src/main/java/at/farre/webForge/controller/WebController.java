package at.farre.webForge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import at.farre.webForge.model.*;


@RestController
public class WebController {
    @Autowired
    private LocalFiles lf;

    @RequestMapping(value = "/files", produces = MediaType.APPLICATION_JSON_VALUE)
    public LocalFiles localFiles() {
        lf.loadFiles();
        return lf;
    }
}