package com.company;

import com.company.buses.types.Type;
import com.company.tasks.BusGenerator;
import com.company.tasks.BusStation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        System.out.println(CONF.ANSI_RESET + "Доступное количество ядер " + Runtime.getRuntime().availableProcessors() + "\n\n");

        Tunnel tunnel = new Tunnel();

        BusGenerator busGenerator = new BusGenerator(tunnel, CONF.BUSES_SIZE); //Создаем 10 автобусов

        BusStation busStation1 = new BusStation(tunnel, Type.CITIZEN, CONF.ANSI_YELLOW);
        BusStation busStation2 = new BusStation(tunnel, Type.PUPILS, CONF.ANSI_BLUE);
        BusStation busStation3 = new BusStation(tunnel, Type.TRAVELERS, CONF.ANSI_PURPLE);

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        service.execute(busGenerator);
        service.execute(busStation1);
        service.execute(busStation2);
        service.execute(busStation3);

        service.shutdown();
    }
}
