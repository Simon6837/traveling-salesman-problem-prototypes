import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        int[] productCords1 = new int[] { 1, 1 };
        int[] productCords2 = new int[] { 1, 1 };
        int[] productCords3 = new int[] { 1, 1 };

        DistanceMatrix store = new DistanceMatrix(productCords1, productCords2, productCords3);

        int[] nearestNeighbor = store.getNearestNeighborRoute();
        int[] bruteForce = store.getBruteForceRoute();
        int[] farthestInsertion = store.getFarthestInsertionRoute();
        store.printDistanceMatrix();
        System.out.println(store.toString());
        // Print the routes
        System.out.println("Nearest Neighbor Route:");
        System.out.println(Arrays.toString(nearestNeighbor));
        System.out.println("Brute Force Route:");
        System.out.println(Arrays.toString(bruteForce));
        System.out.println("Farthest Insertion Route:");
        System.out.println(Arrays.toString(farthestInsertion));
    }
}
