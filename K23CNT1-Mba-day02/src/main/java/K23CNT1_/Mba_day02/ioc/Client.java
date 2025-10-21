package K23CNT1_.Mba_day02.ioc;

public class Client {
    private Service service;

    public Client() {
        // Client tự tạo đối tượng Service
        service = new Service();
    }

    public void doSomething() {
        service.serve();
    }
}