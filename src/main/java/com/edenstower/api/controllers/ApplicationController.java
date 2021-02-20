package com.edenstower.api.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

@RestController
public class ApplicationController {
    @GetMapping("/")
    @ApiOperation(value = "Method to redirect home to Swagger documentation")
    public ModelAndView getHome() {
        //return "redirect:/swagger-ui.html#/";
        return new ModelAndView("redirect:/swagger-ui.html#/");
    }
}
