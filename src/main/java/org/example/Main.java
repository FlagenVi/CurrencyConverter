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

//Библиотека для ввода данных в консоль
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Программа для конвертации курса рубля. :)\n");



        GetExample example = new GetExample();
        String response = example.run("http://www.cbr.ru/scripts/XML_daily.asp?date_req=23/02/2024");
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
                String value = valute.getElementsByTagName("VunitRate").item(0).getTextContent();
                System.out.println((i+1) + ". " + name + ", Курс за 1 единицу: " + value);
            }
            System.out.println("Выберите из перечисленных валюту, в которую вы хотите конвертировать рубль:");
            int num = in.nextInt();

            Element chosenValute = (Element) valuteList.item(num - 1);
            String chosenCharCode = chosenValute.getElementsByTagName("CharCode").item(0).getTextContent();
            String chosenName = chosenValute.getElementsByTagName("Name").item(0).getTextContent();
            String chosenValue = chosenValute.getElementsByTagName("VunitRate").item(0).getTextContent();

            System.out.println("Вы выбрали валюту: " + chosenCharCode + " - " + chosenName);
            System.out.println("Текущий курс: " + chosenValue);

            System.out.print("Введите количество рублей для конвертации: ");
            float rubAmount = in.nextFloat();

            System.out.println("Результат: " + rubAmount * Float.parseFloat(chosenValue.replace(',','.')));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}