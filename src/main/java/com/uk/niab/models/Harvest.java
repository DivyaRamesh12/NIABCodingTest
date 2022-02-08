package com.uk.niab.models;
import lombok.Data;
import java.util.List;
@Data
public class Harvest {
    String County;
    List<String> cropList;
    List<Integer> percentageList;
}
