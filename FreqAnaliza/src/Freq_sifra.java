import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Freq_sifra {
    final char[] crke = {'a', 'b', 'c', 'č', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 'š', 't', 'u', 'v', 'z', 'ž','q','x','y','w'};
    int[] freqsifrcrk = new int[29];
    String sifriranoBesedilo="C:\\Users\\Igor\\Desktop\\testni teksti\\1sifrirana_datoteka.txt";




    //frekvence crk sifriranega besedila dodaja v tabelo freqsifrcrk
    public void setFreqsifrcrk() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(sifriranoBesedilo)));

        String st;
        while ((st = br.readLine()) != null) {
            //    System.out.println(st);
            for (int i = 0; i < st.length(); i++) {
                for (int j = 0; j < crke.length; j++) {
                    if (st.charAt(i) == crke[j]) {
                    //    System.out.println(st.charAt(i)+" "+crke[j]);
                        freqsifrcrk[j]++;
                    }
                }
            }
        }
    }

    public int[] getFreqsifrcrk() throws IOException {

        return freqsifrcrk;
    }

    public void setSifriranoBesedilo(String sifriranoBesedilo) {
        this.sifriranoBesedilo = sifriranoBesedilo;
    }
}
