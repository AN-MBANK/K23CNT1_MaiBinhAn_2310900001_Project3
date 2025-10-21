package K23CNT1_.Mba_day02.tight_loosely_coupling;

public class LooselyCoupledService {
    private SortAlgorithm sortAlgorithm;

    public LooselyCoupledService() {
    }

    public LooselyCoupledService(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    public void complexBusiness(int[] array) {
        sortAlgorithm.sort(array);
    }

    public static void main(String[] args) {
        LooselyCoupledService service =
                new LooselyCoupledService(new LooselyBubbleSortAlgorithm());
        service.complexBusiness(new int[]{11, 21, 13, 42, 15});
    }
}
