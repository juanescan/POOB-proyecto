package spiderweb;
import java.util.*;

class Main {
    static final int INF = 1000000000;
    static final int N = 200010;
    static final int M = 500010;

    static class SegT {
        int[] lo = new int[N << 2];
        int[] hi = new int[N << 2];
        int[] val1 = new int[N << 2];
        int[] val2 = new int[N << 2];

        void build(int x, int l, int r) {
            lo[x] = l;
            hi[x] = r;
            val1[x] = val2[x] = INF;

            if (l == r) {
                return;
            }

            int mid = (l + r) >> 1;
            int a = x << 1;
            build(a, l, mid);
            build(a | 1, mid + 1, r);
        }

        void pushdown(int x) {
            int a = x << 1;
            val1[a] = Math.min(val1[a], val1[x]);
            val1[a | 1] = Math.min(val1[a | 1], val1[x]);
            val2[a] = Math.min(val2[a], val2[x]);
            val2[a | 1] = Math.min(val2[a | 1], val2[x]);
            val1[x] = val2[x] = INF;
        }

        void setv(int x, int l, int v) {
            int tl = lo[x], tr = hi[x];

            if (tl == tr) {
                val1[x] = v - tl;
                val2[x] = v + tl;
                return;
            }

            pushdown(x);
            int mid = (tl + tr) >> 1;
            int a = x << 1;

            if (mid >= l) {
                setv(a, l, v);
            } else {
                setv(a | 1, l, v);
            }
        }

        void update1(int x, int l, int r, int v) {
            int tl = lo[x], tr = hi[x];

            if (l <= tl && tr <= r) {
                val1[x] = Math.min(val1[x], v);
                return;
            }

            pushdown(x);
            int mid = (tl + tr) >> 1;
            int a = x << 1;

            if (mid >= l) {
                update1(a, l, r, v);
            }

            if (mid < r) {
                update1(a | 1, l, r, v);
            }
        }

        void update2(int x, int l, int r, int v) {
            int tl = lo[x], tr = hi[x];

            if (l <= tl && tr <= r) {
                val2[x] = Math.min(val2[x], v);
                return;
            }

            pushdown(x);
            int mid = (tl + tr) >> 1;
            int a = x << 1;

            if (mid >= l) {
                update2(a, l, r, v);
            }

            if (mid < r) {
                update2(a | 1, l, r, v);
            }
        }

        int query1(int x, int l) {
            int tl = lo[x], tr = hi[x];

            if (tl == tr) {
                return val1[x];
            }

            pushdown(x);
            int mid = (tl + tr) >> 1;
            int a = x << 1;

            if (mid >= l) {
                return query1(a, l);
            }

            return query1(a | 1, l);
        }

        int query2(int x, int l) {
            int tl = lo[x], tr = hi[x];

            if (tl == tr) {
                return val2[x];
            }

            pushdown(x);
            int mid = (tl + tr) >> 1;
            int a = x << 1;

            if (mid >= l) {
                return query2(a, l);
            }

            return query2(a | 1, l);
        }
    }

    static int n, m;
    static int[][] ed = new int[M][2];

    static int ask(int x, SegT segt) {
        return Math.min(segt.query1(1, x) + x, segt.query2(1, x) - x);
    }

    static void update(int x, SegT segt) {
        int mid = x + (n - 1) / 2;
        int v = ask(x, segt);

        if (mid >= n) {
            segt.update1(1, x, n - 1, v - x);
            segt.update1(1, 0, mid % n, v + n);
        } else {
            segt.update1(1, x, mid, v - x);
        }

        if (mid >= n - 1) {
            mid = (mid + 1) % n;
            segt.update2(1, mid, x, v + x);
        } else {
            segt.update2(1, 0, x, v + x);
            segt.update2(1, mid + 1, n - 1, v + x + n);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        int st = scanner.nextInt();
        st--;

        SegT segt = new SegT();
        segt.build(1, 0, n - 1);
        segt.setv(1, st, 0);
        update(st, segt);

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            int prex = ask(x, segt);
            int prey = ask(y, segt);
            prex = Math.min(prex, ask((x + n - 1) % n, segt) + 1);
            prey = Math.min(prey, ask((y + 1 ) % n, segt) + 1);
            segt.setv(1, x, prex);
            segt.setv(1, y, prey);
            update(x, segt);
            update(y, segt);
        }

        for (int i = 0; i < n; i++) {
            System.out.println(ask(i, segt));
        }
    }
}


















