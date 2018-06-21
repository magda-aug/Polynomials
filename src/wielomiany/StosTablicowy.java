package wielomiany;

import java.util.Arrays;

public class StosTablicowy implements Stos {

    private static final int POCZATKOWY = 0;
    private static final int MNOZNIK = 3;
    private static final int DZIELNIK = 2;

    private WielomianTablicowy[] elementy;
    private int rozmiar = 0;

    public StosTablicowy() {
        this(POCZATKOWY);
    }

    public StosTablicowy(int rozmiarPoczatkowy) {
        elementy = new WielomianTablicowy[rozmiarPoczatkowy];
    }

    public boolean jestPusty() {
        return (rozmiar == 0);
    }

    public void wstaw(WielomianTablicowy x) {
        if (rozmiar == elementy.length) {
            final int nowy = 1 + rozmiar * MNOZNIK / DZIELNIK;
            elementy = Arrays.copyOf(elementy, nowy);
        }
        elementy[rozmiar++] = x;
    }

    public WielomianTablicowy pobierz() {
        assert !jestPusty();
        return elementy[--rozmiar];
    }

    @Override
    public String toString() {
        return elementy[0].toString();
    }
}
