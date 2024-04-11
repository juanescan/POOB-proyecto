package spiderweb;
import java.util.*;

public class Solve {
    static int n, m, s;
    static int[] f = new int[200100];
    static class Pair {
        int D, x;
        Pair(int D, int x) {
            this.D = D;
            this.x = x;
        }
    }
    static Pair[] E = new Pair[501000];
    
    static int di(int x, int y) {
        if (x > y)
            return Math.min(y - x, n - (y - x));
        else
            return Math.min(y - x, n - (x - y));
    }
    
    static class SegmentTree {
        int[] tg = new int[801000];
        int ty;
        
        void build(int p, int l, int r) {
            tg[p] = Integer.MAX_VALUE;
            if (l == r)
                return;
            int mid = (l + r) / 2;
            build(p * 2, l, mid);
            build(p * 2 + 1, mid + 1, r);
        }
        
        void ad(int p, int z) {
            tg[p] = Math.min(tg[p], z);
        }
        
        void pd(int p) {
            ad(p * 2, tg[p]);
            ad(p * 2 + 1, tg[p]);
            tg[p] = Integer.MAX_VALUE;
        }
        
        void up(int p, int l, int r, int x, int y, int z) {
            if (x <= l && r <= y) {
                ad(p, z);
                return;
            }
            pd(p);
            int mid = (l + r) / 2;
            if (x <= mid)
                up(p * 2, l, mid, x, y, z);
            if (y > mid)
                up(p * 2 + 1, mid + 1, r, x, y, z);
        }
        
        int A(int x) {
            int p = 1, l = 1, r = n;
            while (l < r) {
                int mid = (l + r) / 2;
                pd(p);
                if (x <= mid)
                    r = mid;
                else
                    l = mid + 1;
                p = (x <= mid) ? p * 2 : p * 2 + 1;
            }
            int O = tg[p];
            tg[p] = Integer.MAX_VALUE;
            return O;
        }
    }
    
    static SegmentTree[] T = new SegmentTree[2];
    
    static int ask(int x) {
        int o1 = T[0].A(x) + x;
        int o2 = T[1].A(x) - x;
        f[x] = Math.min(f[x], Math.min(o1, o2));
        return f[x];
    }
    
    static void eg(int x, int px) {
        T[0].up(1, 1, n, x, n, px - x);
        T[0].up(1, 1, n, 1, x, px + n - x);
        T[1].up(1, 1, n, 1, x, px + x);
        T[1].up(1, 1, n, x, n, px + n + x);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Leer la entrada como una sola línea de números separados por espacios
        String[] input = scanner.nextLine().split(" ");
        
        // Extraer los valores n, m y s de la entrada
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        s = Integer.parseInt(input[2]);
        
        // Inicializar el SegmentTree
        T[1] = new SegmentTree();
        T[1].ty = 1;
        
        for (int o = 0; o < 2; o++)
            T[o].build(1, 1, n);
        
        // Calcular f
        for (int i = 1; i <= n; i++)
            f[i] = di(i, s);
        
        // Leer las coordenadas x, y de E
        for (int i = 1; i <= m; i++) {
            input = scanner.nextLine().split(" ");
            E[i] = new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }
        
        // Ordenar E en base a la distancia D
        Arrays.sort(E, 1, m + 1, (a, b) -> b.D - a.D);
        
        // Procesar E
        for (int ii = 1; ii <= m; ii++) {
            int x = E[ii].x, y = (x % n) + 1;
            int px = Math.min(ask(y), ask((x + n - 2) % n + 1) + 1);
            int py = Math.min(ask(x), ask((y % n) + 1) + 1);
            f[x] = px;
            f[y] = py;
            eg(x, px);
            eg(y, py);
        }
        
        // Imprimir el resultado
        for (int i = 1; i <= n; i++)
            System.out.println(ask(i));
    }
}
