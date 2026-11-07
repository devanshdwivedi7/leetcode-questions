
class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[m];
        int currentTime = 0;

        for (int i = 0; i < m; i++) {
            currentTime = Math.max(currentTime, i);

            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int[] server = busyServers.poll();
                freeServers.offer(new int[]{server[1], server[2]});
            }

            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                    int[] server = busyServers.poll();
                    freeServers.offer(new int[]{server[1], server[2]});
                }
            }

            int[] bestServer = freeServers.poll();
            ans[i] = bestServer[1];
            busyServers.offer(new int[]{currentTime + tasks[i], bestServer[0], bestServer[1]});
        }

        return ans;
    }
}