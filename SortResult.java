public class SortResult implements Comparable<SortResult> {

    private String algorithm;
    private String structure;
    private double time;
    private boolean sorted;

    public SortResult(String algorithm, String structure, double time, boolean sorted) {
        this.algorithm = algorithm;
        this.structure = structure;
        this.time = time;
        this.sorted = sorted;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getStructure() {
        return structure;
    }

    public double getTime() {
        return time;
    }

    public boolean isSorted() {
        return sorted;
    }

    @Override
    public int compareTo(SortResult other) {
        return Double.compare(time, other.time);
    }
}
