public class TimeLimitResult implements Comparable<TimeLimitResult> {

    private String algorithm;
    private String structure;
    private int completed;
    private double averageTime;
    private boolean sorted;

    public TimeLimitResult(String algorithm, String structure, int completed,
            double averageTime, boolean sorted) {
        this.algorithm = algorithm;
        this.structure = structure;
        this.completed = completed;
        this.averageTime = averageTime;
        this.sorted = sorted;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getStructure() {
        return structure;
    }

    public int getCompleted() {
        return completed;
    }

    public double getAverageTime() {
        return averageTime;
    }

    public boolean isSorted() {
        return sorted;
    }

    @Override
    public int compareTo(TimeLimitResult other) {
        if (completed != other.completed) {
            return Integer.compare(other.completed, completed);
        }
        return Double.compare(averageTime, other.averageTime);
    }
}
