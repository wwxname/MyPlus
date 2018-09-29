package com.plus.schedule.example;

import com.plus.schedule.DelayMinutesService;

public class MainTest {

    public static void main(String args[]) {
        // SchduledService schduledService = new SchduledService();
        //schduledService.startAsync();
      DelayMinutesService.newInstance(0, () -> {
            System.err.println("laji");
        }).startAsync();
    }
}
