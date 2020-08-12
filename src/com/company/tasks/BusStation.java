package com.company.tasks;

import com.company.CONF;
import com.company.Tunnel;
import com.company.buses.Bus;
import com.company.buses.types.Type;

public class BusStation implements Runnable {

    private Tunnel tunnel;
    private Type busType;
    private String color;

    public BusStation(Tunnel tunnel, Type bysType, String color) {
        this.tunnel = tunnel;
        this.busType = bysType;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.currentThread().setName(color + "Посадка " + busType);
                Thread.sleep(CONF.WAY_TIME); // Путь от туннеля до остановки
                Bus bus = tunnel.get(busType);
                if (bus != null) {
                    while (bus.countCheck()) {
                        Thread.sleep(100); // Время загрузки автобуса (BOARDING_SPEED в секунду)
                        bus.add(CONF.BOARDING_SPEED); // Загружаем автобус пассажирами
                        System.out.println(color + "Сообщение от потока: " + Thread.currentThread().getName() +
                                "\n" + color + "Автобус(" + bus.getName() + ") загружен на "
                                + "\n" + bus.getCount() + "/" + bus.getSize().getValue() + "\n");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
