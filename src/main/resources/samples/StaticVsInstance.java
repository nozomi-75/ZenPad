public class StaticVsInstance {
    public void instanceMethod() {
        System.out.println("This is an instance method.");
    }

    public static void staticMethod() {
        System.out.println("This is a static method.");
    }

    public static void main(String[] args) {
    	StaticVsInstance myObject = new StaticVsInstance();
    	myObject.instanceMethod();

    	staticMethod();
    }
}
