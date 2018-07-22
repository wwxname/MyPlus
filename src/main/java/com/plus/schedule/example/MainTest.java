package com.plus.schedule.example;


import com.plus.schedule.SchduledService;

public class MainTest {

    public static void  main(String args[]){
        SchduledService schduledService = new SchduledService();
        schduledService.startAsync();
    }
}
