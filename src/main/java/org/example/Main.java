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
        Scanner in = new Scanner(System.in);

        System.out.println("Программа для конвертации курса рубля. :)\n");



        GetExample example = new GetExample();
        String response = example.run("http://www.cbr.ru/scripts/XML_daily.asp?date_req=22/02/2024");
        try {
            //Для чтения XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //Парсим XML
            Document document = db.parse(new InputSource(new StringReader(response)));

            NodeList valuteList = document.getElementsByTagName("Valute");
            for(int i = 0; i < valuteList.getLength();i++)
            {
                Element valute = (Element) valuteList.item(i);
                String name = valute.getElementsByTagName("Name").item(0).getTextContent();
                String value = valute.getElementsByTagName("Value").item(0).getTextContent();
                System.out.println((i+1) + ". " + name + ", Курс: " + value);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Выберите из перечисленных валюту, в которую вы хотите конвертировать рубль:");
        int num = in.nextInt();
        System.out.println("Введите количество рублей, что у вас есть:");
        float fnum = in.nextFloat();


    }
}