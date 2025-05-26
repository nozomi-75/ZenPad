public class Abstraction {
    public static void main(String[] args) {
        SmartTV stv1 = new SmartTV();
        stv1.brand = "LG";
        stv1.model = "LQ63";

        stv1.turnOn();
        stv1.connectToWifi();
        stv1.turnOff();
    }
}

class SmartTV extends Appliance implements SmartDevice {
    @Override
    public void turnOn() {
        System.out.println("SmartTV " + brand + " " + model + " plugged in and turned on with remote.");
    }

    @Override
    public void connectToWifi() {
        System.out.println("SmartTV " + brand + " " + model + " connecte to network Guest WiFi.");
        System.out.println("Requesting authentication...");
    }
}

interface SmartDevice {
    void connectToWifi();
}

abstract class Appliance {
    protected String brand;
    protected String model;

    abstract void turnOn();
    void turnOff() {
        System.out.println("Appliance turned off.");
    }
}