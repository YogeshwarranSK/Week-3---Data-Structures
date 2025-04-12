package Day13;
class CircularTourFinder {
    public int findStartPoint(int[] petrol, int[] distance) {
        int n = petrol.length;
        int totalPetrol = 0;
        int totalDistance = 0;
        int currentPetrol = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            totalPetrol += petrol[i];
            totalDistance += distance[i];
            currentPetrol += petrol[i] - distance[i];
            if (currentPetrol < 0) {
                start = i + 1;
                currentPetrol = 0;
            }
        }
        return totalPetrol >= totalDistance ? start : -1;
    }
    public static void main(String[] args) {
        CircularTourFinder tour = new CircularTourFinder();
        int[] petrol = {4, 6, 7, 4};
        int[] distance = {6, 5, 3, 5};
        int startPoint = tour.findStartPoint(petrol, distance);
        if (startPoint != -1) {
            System.out.println("Start at petrol pump: " + startPoint);
        } else {
            System.out.println("No solution exists");
        }
    }
}
