package com.company.tasks;

import com.company.CONF;
import com.company.Tunnel;
import com.company.buses.Bus;
import com.company.buses.types.Size;
import com.company.buses.types.Type;

import java.util.Random;

public class BusGenerator implements Runnable {

    private Tunnel tunnel;
    private int busCount;

    public BusGenerator(Tunnel tunnel, int busCount) {
        this.tunnel = tunnel;
        this.busCount = busCount;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < busCount) {
            Thread.currentThread().setName(CONF.ANSI_CYAN + "Генератор автобусов");
            count++;
            tunnel.add(new Bus(getRandomSize(), getRandomType(), "bus #" + count)); // Генерируем случайный автобус и помещаем его в туннель
            try {
                Thread.sleep(getRandomTime()); // Интервал приездов новых автобусов (от 2,5 до 6 секунд)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getRandomTime() {
        Random rand = new Random();
        return rand.nextInt(CONF.MAX_TIME - CONF.MIN_TIME) + CONF.MIN_TIME;
    }

    private Type getRandomType() {
        Random rand = new Random();
        return Type.values()[rand.nextInt(Type.values().length)];
    }

    private Size getRandomSize() {
        Random rand = new Random();
        return Size.values()[rand.nextInt(Size.values().length)];
    }
}
