package com.uk.niab.controllers;

import ch.qos.logback.core.util.COWArrayList;
import com.uk.niab.models.Harvest;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Arrays;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import  java.io.FileReader;
import  java.io.File;

import java.io.FileNotFoundException;
import  java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/api/harvest")
public class Harvestcontroller {


    @GetMapping
    public List<Harvest> findAll() {
        String filepath =new File("").getAbsolutePath();
        File file = new File(filepath+"\\src\\main\\resources\\csv files\\harvest.csv");
        System.out.println(file.exists());
        List<Harvest> list = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String input = line.replaceAll(" ", "");
                String[] values = input.split(",");
               System.out.println("val"+values);
               List<String> cropList = new ArrayList();
                List<Integer> percentageList = new ArrayList();
               for(int i=1;i<values.length;i++){
                   Harvest harvest = new Harvest();
                   harvest.setCounty(values[0]);
                   if(i%2!=0){
                       cropList.add(values[i]);

                   }else{
                       int per = Integer.parseInt(values[i]);
                       percentageList.add(per);
                   }
                   harvest.setCropList(cropList);
                   harvest.setPercentageList(percentageList);
                   System.out.println("val"+ values[i]);
               }
                //records.add(Arrays.asList(values));
            }


    }catch(IOException  e) {

            e.printStackTrace();
        }


        return list;
    }
    public static void main(String args[]){
        Harvestcontroller con = new Harvestcontroller();
        con.findAll();
    }
    public HashMap<String,String> cropCodeList(){
        HashMap<String,String> codeList =new HashMap<String,String>();
        codeList.put("w","Wheat");
        codeList.put("B","Barley");
        codeList.put("M","Maize");
        codeList.put("BE","Beetroot");
        codeList.put("C","Carrot");
        codeList.put("PO","Potatoes");
        codeList.put("PA","Parsnips");
        codeList.put("O","Oats");

        return codeList;
    }

}
