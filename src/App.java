import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        // create the product cords
        int[] productCords1 = new int[] { 14, 78 };
        int[] productCords2 = new int[] { 32, 1 };
        int[] productCords3 = new int[] { 2, 3 };
        // create the distance matrix based on the above cords
        DistanceMatrix store = new DistanceMatrix(productCords1, productCords2, productCords3);
        // print the cords and distance matrix
        System.out.println(store.toString());
        store.printDistanceMatrix();
        // run each algorithm and time it
        long nearestNeigbourStartTime = System.nanoTime();
        int[] nearestNeighbor = store.getNearestNeighborRoute();
        long nearestNeigbourEndTime = System.nanoTime();
        long bruteForceStartTime = System.nanoTime();
        int[] bruteForce = store.getBruteForceRoute();
        long bruteForceEndTime = System.nanoTime();
        long farthestInsertionStartTime = System.nanoTime();
        int[] farthestInsertion = store.getFarthestInsertionRoute();
        long farthestInsertionEndTime = System.nanoTime();
        // Print the routes
        System.out.println("\n");
        System.out.println("Nearest Neighbor Route:");
        System.out.println(Arrays.toString(nearestNeighbor));
        System.out.println("Total distance: " + store.getRouteDistance(nearestNeighbor));
        System.out.println("Time taken: " + (nearestNeigbourEndTime - nearestNeigbourStartTime) + "ns");
        System.out.println("\n");
        System.out.println("Brute Force Route:");
        System.out.println(Arrays.toString(bruteForce));
        System.out.println("Total distance: " + store.getRouteDistance(bruteForce));
        System.out.println("Time taken: " + (bruteForceEndTime - bruteForceStartTime) + "ns");
        System.out.println("\n");
        System.out.println("Farthest Insertion Route:");
        System.out.println(Arrays.toString(farthestInsertion));
        System.out.println("Total distance: " + store.getRouteDistance(farthestInsertion));
        System.out.println("Time taken: " + (farthestInsertionEndTime - farthestInsertionStartTime) + "ns");
    }
}
