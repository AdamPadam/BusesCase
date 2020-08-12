package com.company;

import com.company.buses.Bus;
import com.company.buses.types.Type;

import java.util.ArrayList;
import java.util.List;

public class Tunnel {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private List<Bus> store; // Автобусы в туннеле
    private static final int MAX_BUS_IN_TUNNEL = 5; // Вместительность туннеля
    private static final int MIN_BUS_IN_TUNNEL = 0;
    private int busCounter = 0;

    public Tunnel() {
        store = new ArrayList<>();
    }

    public synchronized boolean add(Bus bus) { // Добавляет автобусы в туннель
        try {
            if (busCounter < MAX_BUS_IN_TUNNEL) { // Если в туннеле ещё есть место, то запускает другой поток и начинает добавлять автобусы в туннель
                notifyAll(); // Запускает другой поток
                store.add(bus);
                System.out.println(ANSI_GREEN + "------------------- Новый автобус(" + bus.getName() + ") заехал в туннель -------------------"
                        + "\n" + "Тип: " + bus.getType() + "\n" + "Размер: " + bus.getSize().getValue()
                        + "\n" + "Сообщение от потока: " + Thread.currentThread().getName()
                        + "\n" + "Теперь автобусов в туннеле: " + store.size() + "\n");
                busCounter++;
            } else { // Если нет, то ждет когда заберут автобусы
                System.out.println(ANSI_RED + "Нету места для новых автобусов, сообщение от потока: " + Thread.currentThread().getName() + "\n");
                wait();
                return false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public synchronized Bus get(Type busType) { // Метод достает автобусы из туннеля
        try {
            if (busCounter > MIN_BUS_IN_TUNNEL) { // Если в туннеле есть автобусы, то
                notifyAll(); // Запускает другой поток
                for (Bus bus : store) { // Данный поток начинает забирать автобусы нужного типа из туннеля
                    if (bus.getType() == busType) {
                        busCounter--;
                        System.out.println(ANSI_GREEN + "------------------- Автобус (" + bus.getName() + ") выехал из туннеля -------------------"
                                + "\n" + "Сообщение от потока: " + Thread.currentThread().getName()
                                + "\n" + "Теперь автобусов в туннеле: " + (store.size() - 1) + "\n");
                        store.remove(bus);
                        return bus;
                    }
                }
            }
            System.out.println(ANSI_RED + "В туннеле нету автобусов нужного типа, сообщение от потока: " + Thread.currentThread().getName() + "\n");
            wait(); // Если автобусов нет, то ждет когда их добавят
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
