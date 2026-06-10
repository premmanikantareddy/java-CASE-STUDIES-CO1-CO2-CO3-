public class BPlusTreeAnalysis {

    public static void main(String[] args) {

        int pageSize = 4096;          // 4 KB SSD page
        int keySize = 64;             // 64-byte key
        int payloadSize = 100;        // 100-byte payload
        int pointerSize = 8;          // 8-byte child pointer

        long totalRecords = 500000000L; // 5 × 10^8 records

        // Leaf Fanout
        int leafEntrySize = keySize + payloadSize;
        int leafFanout = pageSize / leafEntrySize;

        // Internal Fanout
        int internalEntrySize = keySize + pointerSize;
        int internalFanout = pageSize / internalEntrySize;

        // Leaf Pages Required
        long leafPages = (long) Math.ceil((double) totalRecords / leafFanout);

        // Estimate Tree Height
        int height = 1;
        double nodes = leafPages;

        while (nodes > 1) {
            nodes = Math.ceil(nodes / internalFanout);
            height++;
        }

        // SSD Reads with top 3 levels cached
        int cachedLevels = 3;
        int ssdReads = Math.max(0, height - cachedLevels);

        System.out.println("===== B+ Tree Analysis =====");
        System.out.println("Page Size: " + pageSize + " bytes");
        System.out.println("Key Size: " + keySize + " bytes");
        System.out.println("Payload Size: " + payloadSize + " bytes");

        System.out.println("\nLeaf Entry Size = " + leafEntrySize + " bytes");
        System.out.println("Leaf Fanout = " + leafFanout + " entries/page");

        System.out.println("\nInternal Entry Size = " + internalEntrySize + " bytes");
        System.out.println("Internal Fanout = " + internalFanout + " pointers/page");

        System.out.println("\nTotal Records = " + totalRecords);
        System.out.println("Leaf Pages Required = " + leafPages);

        System.out.println("\nEstimated Tree Height = " + height + " levels");

        System.out.println("\nTop 3 Levels Cached in Memory");
        System.out.println("SSD Page Reads per Lookup = " + ssdReads);

        System.out.println("\nTime Complexity:");
        System.out.println("Search  : O(log n)");
        System.out.println("Insert  : O(log n)");
        System.out.println("Delete  : O(log n)");
        System.out.println("Range Query : O(log n + k)");
    }
}