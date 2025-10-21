package K23CNT1_.Mba_day02.ioc;

public class IoCClient {
    private IoCService iocService;

    // Inject thông qua constructor
    public IoCClient(IoCService iocService) {
        this.iocService = iocService;
    }

    public void doSomething() {
        iocService.serve();
    }
}
class MainIoC {
    public static void main(String[] args) {
        IoCService service = new IoCService();
        IoCClient client = new IoCClient(service);
        client.doSomething();
    }
}