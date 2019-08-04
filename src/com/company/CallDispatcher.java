package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CallDispatcher  {
//    private List<TelephoneCall> allCalls = new LinkedList<>();
    private List<Employee> employees = new LinkedList<>();
    private Queue<TelephoneCall> callsQueue = new LinkedList<>();
    private boolean stop = false;
    CallDispatcher(List<Employee> employees, Queue<TelephoneCall> calls)
    {
        this.employees = employees;
        this.callsQueue = calls;
    }

    public void run() {
        while(!stop)
        {
            if(!callsQueue.isEmpty())
            {
                TelephoneCall currentCall = callsQueue.poll();
                dispatch(currentCall);
            }


        }
    }

    public void dispatch(TelephoneCall call)
    {
        Employee selectedEmployee = null;
        if (call.getPriority() == CallPriority.low)
        {
            selectedEmployee = getAvailableEmployee(EmployeeLevel.junior);
            if (selectedEmployee == null)
            {
                selectedEmployee = getAvailableEmployee(EmployeeLevel.senior);
                if (selectedEmployee == null)
                {
                    selectedEmployee = getAvailableEmployee(EmployeeLevel.manager);
                }
            }


        }
        if(call.getPriority() == CallPriority.high)
        {
            selectedEmployee = getAvailableEmployee(EmployeeLevel.manager);
            if (selectedEmployee == null)
            {
                selectedEmployee = getAvailableEmployee(EmployeeLevel.director);

            }
        }

        if(selectedEmployee == null)
        {
            putCallOnHold(call);
        }

        else
        {
            selectedEmployee.takeCall(this, call);
            System.out.println("call assigned to: " + selectedEmployee.getName() +"  "  + ",level:  " + selectedEmployee.getLevel());
        }
    }

    private Employee getAvailableEmployee(EmployeeLevel level){
        for(Employee employee: employees)
        {
            if(employee.getLevel() == level && employee.isAvailable())
            {
                return employee;
            }
        }
        return null;
    }

    public void putCallOnHold(TelephoneCall call){
        callsQueue.add(call);
    }

    public void addCall(TelephoneCall call){
        callsQueue.add(call);

    }


}
