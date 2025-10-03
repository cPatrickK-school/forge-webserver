package at.farre.webForge.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrController implements ErrorController {
    @RequestMapping(value = "/error", produces = MediaType.TEXT_PLAIN_VALUE)
    public String handleError() {
        return "An error occurred. Please try again later.";
    }
}
