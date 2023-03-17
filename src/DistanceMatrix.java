public class DistanceMatrix {
    private int start[] = new int[4];
    private int point1[] = new int[4];
    private int point2[] = new int[4];
    private int point3[] = new int[4];

    public DistanceMatrix() {
        // Empty constructor
    }
    // public DistanceMatrix(int[] start, int[] point1, int[] point2, int[] point3) {
    //     this.start = start;
    //     this.point1 = point1;
    //     this.point2 = point2;
    //     this.point3 = point3;
    // }

    public void setStart(int distanceToPoint1, int distanceToPoint2, int distanceToPoint3) {
        start[0] = 0;
        start[1] = distanceToPoint1;
        start[2] = distanceToPoint2;
        start[3] = distanceToPoint3;
    }

    public void setPoint1(int distanceToStart, int distanceToPoint2, int distanceToPoint3) {
        point1[0] = distanceToStart;
        point1[1] = 0;
        point1[2] = distanceToPoint2;
        point1[3] = distanceToPoint3;
    }

    public void setPoint2(int distanceToStart, int distanceToPoint1, int distanceToPoint3) {
        point2[0] = distanceToStart;
        point2[1] = distanceToPoint1;
        point2[2] = 0;
        point2[3] = distanceToPoint3;
    }

    public void setPoint3(int distanceToStart, int distanceToPoint1, int distanceToPoint2) {
        point3[0] = distanceToStart;
        point3[1] = distanceToPoint1;
        point3[2] = distanceToPoint2;
        point3[3] = 0;
    }

    public void printDistanceMatrix() {
        // Print the distance matrix
        System.out.println("Distance Matrix:");
        System.out.println("\tStart\tPoint 1\tPoint 2\tPoint 3");

        System.out.println("Start\t" + start[0] + "\t" + start[1] + "\t" + start[2] + "\t" + start[3]);
        System.out.println("Point 1\t" + point1[0] + "\t" + point1[1] + "\t" + point1[2] + "\t" + point1[3]);
        System.out.println("Point 2\t" + point2[0] + "\t" + point2[1] + "\t" + point2[2] + "\t" + point2[3]);
        System.out.println("Point 3\t" + point3[0] + "\t" + point3[1] + "\t" + point3[2] + "\t" + point3[3]);

    }
}
