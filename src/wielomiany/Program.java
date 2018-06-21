package wielomiany;

import java.util.Scanner;

public class Program {

    private static boolean isInteger(String str) {
        int i = 0;
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private static void czytaj(String wiersz, StosTablicowy stos) {
        final Scanner wyrażenie = new Scanner(wiersz);
        while (wyrażenie.hasNext()) {
            final String s = wyrażenie.next();
            if (s.equals("x")) {
                stos.wstaw(new WielomianTablicowy(1, new int[]{0, 1}));
            } else if (isInteger(s)) {
                stos.wstaw(new WielomianTablicowy(0, new int[]{Integer.parseInt(s)}));
            } else {
                switch (s) {
                    case "+":
                        stos.wstaw(stos.pobierz().dodaj(stos.pobierz()));
                        break;
                    case "*":
                        stos.wstaw(stos.pobierz().pomnóż(stos.pobierz()));
                        break;
                    case "@":
                        WielomianTablicowy f = stos.pobierz();
                        WielomianTablicowy g = stos.pobierz();
                        stos.wstaw(g.złóżZ(f));
                        break;
                }
            }
        }
        wyrażenie.close();
    }

    public static void main(String[] args) {
        StosTablicowy stos = new StosTablicowy();
        final Scanner wiersze = new Scanner(System.in);
        final String wiersz = wiersze.nextLine();
        czytaj(wiersz, stos);
        wiersze.close();
        System.out.println(stos);
    }
}
