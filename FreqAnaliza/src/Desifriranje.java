import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Desifriranje {
    final char[] crke = {'a', 'b', 'c', 'č', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 'š', 't', 'u', 'v', 'z', 'ž'};
    ArrayList<PomozniKlas> sifriraneCrke = new ArrayList<PomozniKlas>();
     ArrayList<PomozniKlas> referencneCrke = new ArrayList<PomozniKlas>();

    public void zacetek() throws IOException {
        Freq_refer fa = new Freq_refer();
        fa.setFreqcrk();
        Freq_sifra fd = new Freq_sifra();
        fd.setFreqsifrcrk();
/*
        for(int i=0; i<fa.getFreqcrk().length; i++){
            System.out.println(fa.getFreqcrk()[i]);
        }
        System.out.println();
        for(int i=0; i<fd.getFreqsifrcrk().length; i++){
            System.out.println(fd.getFreqsifrcrk()[i]);
        }
*/
        indexiranje(fa.getFreqcrk(), fd.getFreqsifrcrk());

        menjavaCrk(beri());


    }

    static File file = new File("C:\\Users\\Igor\\Desktop\\testni teksti\\1sifrirana_datoteka.txt");

    //preglejuje ujemajoce se frekvence crk
    public String beri() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String p = "";
        while ((st = br.readLine()) != null) {
            p = p + st;
        }
        // System.out.println(p);

        return p;

    }


    public void indexiranje(int[] refer, int[] sifra) {
        PomozniKlas z = null;

        for (int i = 0; i < sifra.length; i++) {
            sifriraneCrke.add(z = new PomozniKlas(crke[i], sifra[i]));

        }
        for (int i = 0; i < refer.length; i++) {
            referencneCrke.add(z = new PomozniKlas(crke[i], refer[i]));

        }


    }

    public void menjavaCrk(String p) {
        String izpis="";
        Collections.sort(sifriraneCrke, new Comparator<PomozniKlas>() {
            @Override
            public int compare(PomozniKlas pomozniKlas, PomozniKlas t1) {
                return Integer.valueOf(pomozniKlas.freqCrke).compareTo(t1.freqCrke);
            }
        });
        Collections.sort(referencneCrke, new Comparator<PomozniKlas>() {
            @Override
            public int compare(PomozniKlas pomozniKlas, PomozniKlas t1) {
                return Integer.valueOf(pomozniKlas.freqCrke).compareTo(t1.freqCrke);
            }
        });
        /*      for(int i=0; i<sifriraneCrke.size(); i++){

            izpis=p.replace(sifriraneCrke.get(i).crka, Character.toUpperCase(referencneCrke.get(i).crka));

        }
        */
        StringBuilder rezultat = new StringBuilder(p);
        for(int i=0; i<p.length(); i++){
            for(int j=0; j<sifriraneCrke.size();j++){
                if(p.charAt(i)==sifriraneCrke.get(j).crka){
                    rezultat.setCharAt(i,referencneCrke.get(j).crka);
                }
            }



        }

        System.out.println(rezultat);


    }


}
