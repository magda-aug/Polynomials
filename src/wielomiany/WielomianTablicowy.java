package wielomiany;

import java.util.Arrays;

public class WielomianTablicowy {
    private final int stopień;
    private final int[] współczynniki;

    public WielomianTablicowy(int stopień, int[] współczynniki) {
        this.stopień = stopień;
        this.współczynniki = współczynniki.clone();
    }

    private static int element(int[] a, int i) {
        if ((i < 0) || (i >= a.length)) {
            return 0;
        } else {
            return a[i];
        }
    }

    private static int[] suma(int[] a, int[] b) {
        int i = Math.max(a.length, b.length) - 1;
        while (i >= 0 && (element(a, i) + element(b, i) == 0)) {
            --i;
        }
        int[] res = new int[i + 1];
        while (i >= 0) {
            res[i] = element(a, i) + element(b, i);
            --i;
        }
        return res;
    }

    private static int[] iloczyn(int[] a, int[] b) {
        int k = a.length + b.length + 1;
        int[] res = new int[k];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                res[i + j] += a[i] * b[j];
            }
        }
        res = uprość(res);
        return res;
    }

    private static int[] uprość(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i] == 0) {
            i--;
        }
        int[] res = Arrays.copyOfRange(a, 0, i + 1);
        return res;
    }

    public WielomianTablicowy dodaj(WielomianTablicowy a) {
        int[] wsp = suma(this.współczynniki, a.współczynniki);
        WielomianTablicowy w = new WielomianTablicowy(wsp.length - 1, wsp);
        return w;
    }

    public WielomianTablicowy pomnóż(WielomianTablicowy a) {
        int[] wsp = iloczyn(this.współczynniki, a.współczynniki);
        WielomianTablicowy w = new WielomianTablicowy(wsp.length - 1, wsp);
        return w;
    }

    public WielomianTablicowy złóżZ(WielomianTablicowy a) {
        int[] wsp = new int[]{this.współczynniki[0]};
        int[] x = new int[]{1};
        for (int i = 1; i < this.współczynniki.length; i++) {
            x = iloczyn(x, a.współczynniki);
            int[] y = x.clone();
            for (int j = 0; j < y.length; j++) {
                y[j] *= this.współczynniki[i];
            }
            wsp = suma(wsp, y);
        }
        wsp = uprość(wsp);
        WielomianTablicowy w = new WielomianTablicowy(wsp.length - 1, wsp);
        return w;
    }

    boolean appendIf(int i, StringBuilder s, boolean pierwszy) {
        if (współczynniki[i] > 0) {
            if (pierwszy) s.append("+");
            if ((współczynniki[i] != 1 && i > 0) || i == 0)
                s.append(współczynniki[i]);
            if (i == 1) s.append("x");
            if (i > 1) {
                s.append("x^");
                s.append(i);
            }
            return true;
        } else if (współczynniki[i] < 0) {
            s.append("-");
            if ((współczynniki[i] != -1 && i > 0) || i == 0) {
                s.append(-współczynniki[i]);
            }
            if (i == 1) s.append("x");
            if (i > 1) {
                s.append("x^");
                s.append(i);
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder();
        System.out.println(Arrays.toString(współczynniki));
        int i = współczynniki.length - 1;
        boolean pierwszy = false;  //pierwszy = true wtw. gdy został wypisany jakiś element wielomianu
        for (; i >= 2; --i) {
            pierwszy = appendIf(i, s, pierwszy);
        }
        if (i == 1) {
            appendIf(1, s, pierwszy);
            i--;
        }
        if (i == 0) {
            appendIf(0, s, pierwszy);
            i--;
        }
        return s.toString();
    }

}
