package com.AnNoOfDev.lesson01_Mba_k23cnt1.DateTimeAPI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class DateTimeExample {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("Ngày hiện tại: " + date);
        System.out.println("Giờ hiện tại: " + time);
        System.out.println("Ngày & giờ hiện tại: " + dateTime);
    }
}
//now() trả về thời gian hiện tại theo hệ thống.
//
//LocalDate và LocalTime độc lập, tránh sai lệch múi giờ.