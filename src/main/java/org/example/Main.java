package org.example;

import org.okhttp.GetExample;
import org.xml.sax.InputSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Программа для конвертации курса валют. :)");
        System.out.println("Выберите из перечисленных валют, которую вы хотите конвертировать:");
//        Scanner in = new Scanner(System.in);
//        System.out.print(": ");
//        int num = in.nextInt();


        GetExample example = new GetExample();
        String response = example.run("http://www.cbr.ru/scripts/XML_daily.asp?date_req=22/02/2024");
        try {
            //Для чтения XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //Парсим XML
            Document document = db.parse(new InputSource(new StringReader(response)));


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}