package org.example;
import org.okhttp.GetExample;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Программа для конвертации курса валют. :)");

//        Scanner in = new Scanner(System.in);
//        System.out.print(": ");
//        int num = in.nextInt();
        GetExample example = new GetExample();
        String response = example.run("http://www.cbr.ru/scripts/XML_daily.asp?date_req=22/02/2024");

    }
}