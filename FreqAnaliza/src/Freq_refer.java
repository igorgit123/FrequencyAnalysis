import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Freq_refer {
    final char[] crke = {'a', 'b', 'c', 'č', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 'š', 't', 'u', 'v', 'z', 'ž','q','x','y','w'};
    int[] freqcrk = new int[29];

    String referBesedilo="C:\\Users\\Igor\\Desktop\\testni teksti\\1referencna_datoteka.txt";





    //frekvence crk referencnega besedila dodaja v besedilo freqcrk
    public void setFreqcrk() throws IOException {
        System.out.println(referBesedilo);
        BufferedReader br = new BufferedReader(new FileReader(new File(referBesedilo)));
        String st;
        while ((st = br.readLine()) != null) {

            for (int i = 0; i < st.length(); i++) {
                for (int j = 0; j < crke.length; j++) {
                    if (st.charAt(i) == crke[j]) {
                        freqcrk[j]++;
                    }
                }
            }
        }
    }

    public int[] getFreqcrk() throws IOException {

        return freqcrk;
    }

    public void setReferBesedilo(String referBesedilo) {
        this.referBesedilo = referBesedilo;
    }
}
