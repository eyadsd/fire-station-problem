package com.company;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.Random;

public class Employee {
    private int id;
    private String name;
    private EmployeeLevel level;
    private boolean available;

    Employee(int id, String name, EmployeeLevel level)
    {
        this.id = id;
        this.name = name;
        this.level = level;
        this.available = true;
    }

    public void takeCall(CallDispatcher callDispatcher, TelephoneCall call)
    {
        available = false;
        Thread t = new Thread() {
            public void run() {
                System.out.println("employee: " + name + " ,taking call" + "\n ,duration = " + call.getDurationInSeconds());
                Random random = new Random();
                if(random.nextBoolean() && call.getPriority() == CallPriority.low)
                {
                    System.out.println("call priority changed from low to high, call will be reallocated");
                    handlePriorityChange(callDispatcher, call);
                    Thread.currentThread().interrupt();
                }
                try
                {
                    Thread.sleep(1000 * call.getDurationInSeconds());
                    System.out.println("employee: " + name + " , call finished");

                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                available = true;
                call.success = true;
            }
        };
        t.start();
    }
    public void handlePriorityChange(CallDispatcher dispatcher, TelephoneCall call){
        call.setPriority(CallPriority.high);
        dispatcher.addCall(call);
    }
    public EmployeeLevel getLevel() {
        return level;
    }

    public boolean isAvailable(){
        return available;
    }

    public String getName()
    {
        return this.name;
    }
}
