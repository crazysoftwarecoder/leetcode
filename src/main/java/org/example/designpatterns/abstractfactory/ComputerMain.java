package org.example.designpatterns.abstractfactory;

abstract class Computer {

    public abstract String getCpu();

    public abstract String getRam();

    public abstract String getHDD();

    @Override
    public String toString() {
        return getCpu() + " " + getRam() + " " + getHDD();
    }
}

class PC extends Computer {

    private final String CPU;
    private final String HDD;
    private final String RAM;

    public PC(String CPU, String HDD, String RAM) {
        this.CPU = CPU;
        this.HDD = HDD;
        this.RAM = RAM;
    }

    @Override
    public String getCpu() {
        return CPU;
    }

    @Override
    public String getHDD() {
        return HDD;
    }

    @Override
    public String getRam() {
        return RAM;
    }
}

class Server extends Computer {

    private final String CPU;
    private final String HDD;
    private final String RAM;

    public Server(String CPU, String HDD, String RAM) {
        this.CPU = CPU;
        this.HDD = HDD;
        this.RAM = RAM;
    }

    @Override
    public String getCpu() {
        return CPU;
    }

    @Override
    public String getHDD() {
        return HDD;
    }

    @Override
    public String getRam() {
        return RAM;
    }
}

interface ComputerAbstractFactory {

    Computer createComputer();
}

class PCFactory implements ComputerAbstractFactory {

    private final String ram;
    private final String cpu;
    private final String hdd;

    public PCFactory(String ram, String cpu, String hdd) {
        this.ram = ram;
        this.cpu = cpu;
        this.hdd = hdd;
    }

    @Override
    public Computer createComputer() {
        return new PC(cpu, hdd, ram);
    }
}

class ServerFactory implements ComputerAbstractFactory {

    private final String ram;
    private final String cpu;
    private final String hdd;

    public ServerFactory(String ram, String cpu, String hdd) {
        this.ram = ram;
        this.cpu = cpu;
        this.hdd = hdd;
    }

    @Override
    public Computer createComputer() {
        return new Server(cpu, hdd, ram);
    }
}

class ComputerFactory {

    public static Computer getComputer(ComputerAbstractFactory computerFactory) {
        return computerFactory.createComputer();
    }
}

public class ComputerMain {
    public static void main(String[] args) {
        var pc = ComputerFactory.getComputer(new PCFactory("10GB RAM", "2.4Ghz CPU", "50GB HDD"));
        var server = ComputerFactory.getComputer(new ServerFactory("20GB RAM", "4.8Ghz CPU", "100GB HDD"));

        System.out.println("PC Specs " + System.getProperty("line.separator") + pc);
        System.out.println("Server Specs " + System.getProperty("line.separator") + server);
    }
}