import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistanceMatrix {
    private int[] startCoords = { 0, 0 };
    private int[] productCords1 = new int[2];
    private int[] productCords2 = new int[2];
    private int[] productCords3 = new int[2];
    private int[][] distanceMatrix = new int[4][4];

    public DistanceMatrix(int[] productCords1, int[] productCords2, int[] productCords3) {
        this.productCords1 = productCords1;
        this.productCords2 = productCords2;
        this.productCords3 = productCords3;
        distanceMatrix = createDistanceMatrix();
    }

    public DistanceMatrix(int product1x, int product1y, int product2x, int product2y, int product3x, int product3y) {
        productCords1[0] = product1x;
        productCords1[1] = product1y;
        productCords2[0] = product2x;
        productCords2[1] = product2y;
        productCords3[0] = product3x;
        productCords3[1] = product3y;
        distanceMatrix = createDistanceMatrix();
    }

    public int[][] createDistanceMatrix() {
        int[][] matrix = new int[4][4];
        // set all location that go to itself to 0
        matrix[0][0] = 0;
        matrix[1][1] = 0;
        matrix[2][2] = 0;
        matrix[3][3] = 0;
        // Start to product 1, 2 and 3
        matrix[0][1] = matrix[1][0] = distance(startCoords, productCords1);
        matrix[0][2] = matrix[2][0] = distance(startCoords, productCords2);
        matrix[0][3] = matrix[3][0] = distance(startCoords, productCords3);
        // Product 1 to product 2 and 3
        matrix[1][2] = matrix[2][1] = distance(productCords1, productCords2);
        matrix[1][3] = matrix[3][1] = distance(productCords1, productCords3);
        // Product 2 to product 3
        matrix[2][3] = matrix[3][2] = distance(productCords2, productCords3);

        return matrix;
    }

    //set the distance matrix manualy
    public void setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public void printDistanceMatrix() {
        for (int[] row : distanceMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private int distance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    public String toString() {
        return "Start Coordinates: (" + startCoords[0] + ", " + startCoords[1] + ")\n"
                + "Product Coordinates 1: (" + productCords1[0] + ", " + productCords1[1] + ")\n"
                + "Product Coordinates 2: (" + productCords2[0] + ", " + productCords2[1] + ")\n"
                + "Product Coordinates 3: (" + productCords3[0] + ", " + productCords3[1] + ")";
    }

    // nearest neighbor algorithm

    public int[] getNearestNeighborRoute() {
        int numLocations = distanceMatrix.length;
        int[] route = new int[numLocations];

        // Initialize the route with the starting location (0)
        route[0] = 0;

        // Keep track of which locations have been visited
        boolean[] visited = new boolean[numLocations];
        visited[0] = true;

        // For each location in the route, find the nearest unvisited neighbor
        for (int i = 1; i < numLocations; i++) {
            int currentLocation = route[i - 1];
            int nearestNeighbor = -1;
            int shortestDistance = Integer.MAX_VALUE;

            // Find the nearest unvisited neighbor of the current location
            for (int j = 0; j < numLocations; j++) {
                if (!visited[j] && distanceMatrix[currentLocation][j] < shortestDistance) {
                    nearestNeighbor = j;
                    shortestDistance = distanceMatrix[currentLocation][j];
                }
            }

            // Add the nearest neighbor to the route and mark it as visited
            route[i] = nearestNeighbor;
            visited[nearestNeighbor] = true;

            // Update the current location to be the nearest neighbor
            currentLocation = nearestNeighbor;
        }

        return route;
    }

    // brute force algorithm

    public int[] getBruteForceRoute() {
        int numLocations = distanceMatrix.length;
        int[] route = new int[numLocations];

        // Initialize the route as the default order of locations (0, 1, 2, 3)
        for (int i = 0; i < numLocations; i++) {
            route[i] = i;
        }

        // Try all possible permutations of the route to find the shortest one
        int shortestDistance = Integer.MAX_VALUE;
        int[] shortestRoute = new int[numLocations];
        do {
            int distance = 0;
            for (int i = 1; i < numLocations; i++) {
                distance += distanceMatrix[route[i - 1]][route[i]];
            }
            distance += distanceMatrix[route[numLocations - 1]][route[0]]; // add distance back to start
            if (distance < shortestDistance) {
                shortestDistance = distance;
                shortestRoute = Arrays.copyOf(route, numLocations);
            }
        } while (nextPermutation(route));

        return shortestRoute;
    }

    private boolean nextPermutation(int[] arr) {
        // Find the largest index k such that a[k] < a[k + 1]. If no such index exists,
        // the permutation is the last permutation.
        int k = arr.length - 2;
        while (k >= 0 && arr[k] >= arr[k + 1]) {
            k--;
        }
        if (k < 0) {
            return false;
        }

        // Find the largest index l greater than k such that a[k] < a[l].
        int l = arr.length - 1;
        while (arr[k] >= arr[l]) {
            l--;
        }

        // Swap the value of a[k] with that of a[l].
        int temp = arr[k];
        arr[k] = arr[l];
        arr[l] = temp;

        // Reverse the sequence from a[k + 1] up to and including the final element
        // a[n].
        int i = k + 1;
        int j = arr.length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    // farthest insertion algorithm

    public int[] getFarthestInsertionRoute() {
        int numLocations = distanceMatrix.length;
        int[] route = new int[numLocations];

        // Initialize the route with the starting location (0)
        route[0] = 0;

        // Keep track of which locations have been visited
        boolean[] visited = new boolean[numLocations];
        visited[0] = true;

        // Initialize the list of unvisited locations
        List<Integer> unvisited = new ArrayList<Integer>();
        for (int i = 1; i < numLocations; i++) {
            unvisited.add(i);
        }

        // For each location in the route, find the farthest unvisited neighbor
        for (int i = 1; i < numLocations; i++) {
            int farthestLocation = -1;
            int farthestDistance = -1;

            // Find the farthest unvisited location from the current route
            for (int j = 0; j < i; j++) {
                int currentLocation = route[j];
                for (int k : unvisited) {
                    int distance = distanceMatrix[currentLocation][k];
                    if (distance > farthestDistance) {
                        farthestLocation = k;
                        farthestDistance = distance;
                    }
                }
            }

            // Find the location in the route where the farthest location can be inserted
            int insertIndex = -1;
            int shortestIncrease = Integer.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                int location1 = route[j - 1];
                int location2 = route[j];
                int increase = distanceMatrix[location1][farthestLocation] + distanceMatrix[farthestLocation][location2]
                        - distanceMatrix[location1][location2];
                if (increase < shortestIncrease) {
                    insertIndex = j;
                    shortestIncrease = increase;
                }
            }

            // Insert the farthest location into the route
            if (insertIndex == -1) {
                route[i] = farthestLocation;
            } else {
                for (int j = i; j > insertIndex; j--) {
                    route[j] = route[j - 1];
                }
                route[insertIndex] = farthestLocation;
            }

            // Mark the farthest location as visited and remove it from the unvisited list
            visited[farthestLocation] = true;
            unvisited.remove(new Integer(farthestLocation));
        }

        return route;
    }

    // 2-opt algorithm

    public int[] get2OptRoute() {
        int numLocations = distanceMatrix.length;
        int[] route = new int[numLocations];

        // Initialize the route as the default order of locations (0, 1, 2, 3)
        for (int i = 0; i < numLocations; i++) {
            route[i] = i;
        }

        // Keep track of the best route and distance
        int[] bestRoute = Arrays.copyOf(route, numLocations);
        int bestDistance = getRouteDistance(bestRoute);

        // Keep track of whether any improvements have been made
        boolean improved = true;

        // Keep looking until no improvements are found
        while (improved) {
            improved = false;

            // Try reversing segments of the route
            for (int i = 1; i < numLocations - 1; i++) {
                for (int j = i + 1; j < numLocations; j++) {
                    // Reverse the segment from i to j
                    reverse(route, i, j);

                    // Check if the new route is better
                    int distance = getRouteDistance(route);
                    if (distance < bestDistance) {
                        bestRoute = Arrays.copyOf(route, numLocations);
                        bestDistance = distance;
                        improved = true;
                    }

                    // Reverse the segment back to its original order
                    reverse(route, i, j);
                }
            }
        }

        return bestRoute;
    }

    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }


    //get the distance between all points in the route

    public int getRouteDistance(int[] route) {
        int distance = 0;
        for (int i = 1; i < route.length; i++) {
            distance += distanceMatrix[route[i - 1]][route[i]];
        }
        distance += distanceMatrix[route[route.length - 1]][route[0]]; // add distance back to start
        return distance;
    }
}
