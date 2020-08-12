package com.company;

import com.company.buses.types.Type;
import com.company.tasks.BusGenerator;
import com.company.tasks.BusStation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        System.out.println(ANSI_RESET + "Доступное количество ядер " + Runtime.getRuntime().availableProcessors() + "\n\n");

        Tunnel tunnel = new Tunnel();

        BusGenerator busGenerator = new BusGenerator(tunnel, 10); //Создаем 10 автобусов

        BusStation busStation1 = new BusStation(tunnel, Type.CITIZEN, ANSI_YELLOW);
        BusStation busStation2 = new BusStation(tunnel, Type.PUPILS, ANSI_BLUE);
        BusStation busStation3 = new BusStation(tunnel, Type.TRAVELERS, ANSI_PURPLE);

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        service.execute(busGenerator);
        service.execute(busStation1);
        service.execute(busStation2);
        service.execute(busStation3);

        service.shutdown();
    }
}
