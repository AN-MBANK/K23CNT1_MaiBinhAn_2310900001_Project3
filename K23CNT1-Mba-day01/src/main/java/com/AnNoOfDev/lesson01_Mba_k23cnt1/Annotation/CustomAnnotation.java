package com.AnNoOfDev.lesson01_Mba_k23cnt1.Annotation;

// Tạo annotation mới
@interface DevInfo {
    String author();
    String date();
    String version() default "1.0";
}

@DevInfo(author = "Hư Không", date = "20-10-2025", version = "1.1")
public class CustomAnnotation {
    public static void main(String[] args) {
        System.out.println("Demo Custom Annotation");
    }
}
//@interface dùng để định nghĩa annotation mới.
//
//Annotation có thể có thuộc tính (element) như author, date, version.
//
//Có thể đọc lại thông tin này bằng Reflection nếu cần.