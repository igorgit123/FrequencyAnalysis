public class PomozniKlas {
    char crka;
    int freqCrke;

    public PomozniKlas() {

    }

    public PomozniKlas(char crka, int freqCrke) {
        this.crka = crka;
        this.freqCrke = freqCrke;
    }

    @Override
    public String toString() {
        return "PomozniKlas{" +
                "crka=" + crka +
                ", freqCrke=" + freqCrke +
                '}';
    }

    public char getCrka() {
        return crka;
    }

    public void setCrka(char crka) {
        this.crka = crka;
    }

    public int getFreqCrke() {
        return freqCrke;
    }

    public void setFreqCrke(int freqCrke) {
        this.freqCrke = freqCrke;
    }
}
