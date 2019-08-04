package com.company;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        String pathToJsonFile;
        List<Employee> employees = new LinkedList<>();
        Queue<TelephoneCall> calls = new LinkedList<>();
        // if path to json file is given Run only on given data
        if(args.length>0) {
            pathToJsonFile = args[0];

        }
        else{
            pathToJsonFile = "JSONExample.json";
        }

        Object obj = new JSONParser().parse(new FileReader(pathToJsonFile));
        JSONObject jo = (JSONObject) obj;

        for (Object item : (JSONArray) jo.get("employees")) {
            Map employeeJSON = (Map) item;
            int id = Integer.parseInt((String) employeeJSON.get("id"));
            String name = (String) employeeJSON.get("name");
            EmployeeLevel level = EmployeeLevel.valueOf((String) employeeJSON.get("level"));
            employees.add(new Employee(id, name, level));
        }
        for (Object item : (JSONArray) jo.get("calls")) {
            Map callJSON = (Map) item;
            int duration = Integer.parseInt((String) callJSON.get("duration"));
            CallPriority priority = CallPriority.valueOf((String) callJSON.get("priority"));
            calls.add(new TelephoneCall(priority, duration));
        }
        CallDispatcher callDispatcher = new CallDispatcher(employees, calls);
        callDispatcher.run();


    }

}