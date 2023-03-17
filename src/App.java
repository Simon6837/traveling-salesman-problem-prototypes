public class App {
    public static void main(String[] args) throws Exception {
        DistanceMatrix dm = new DistanceMatrix();
        dm.setStart(10, 2, 41);
        dm.setPoint1(10, 7, 4);
        dm.setPoint2(2, 7, 11);
        dm.setPoint3(41, 4, 11);
        dm.printDistanceMatrix();
    }
}
