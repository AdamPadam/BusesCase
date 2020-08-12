package com.company.buses;

import com.company.buses.types.Size;
import com.company.buses.types.Type;

public class Bus {
    private int count;
    private Size size;
    private Type type;
    private String name;

    public Bus(Size size, Type type, String name) {
        this.size = size;
        this.type = type;
        this.name = name;
    }

    // Посадка пассажиров в автобус
    public void add(int count) {
        this.count += count;
    }

    // Проверка загрузки автобуса пассажирами
    public boolean countCheck() {
        return count < size.getValue();
    }

    public int getCount() {
        return count;
    }

    public Size getSize() {
        return size;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
