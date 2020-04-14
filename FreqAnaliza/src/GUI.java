import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GUI {

    private JButton desifrirajButton;
    private JTextField staraCrka;
    private JTextField novaCrka;
    private JButton zamenjajCrkiButton;
    private JTextField lokacijaReferencnegaBesedilaTextField;
    private JTextField lokacijaSifriranegaBesedilaTextField;
    private JPanel panel;
    private JTextArea sifriranoBesedilo;
    private JTextArea  desifriranoBesedilo;
    private JButton izvoziButton;
    final char[] crke = {'a', 'b', 'c', 'č', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 'š', 't', 'u', 'v', 'z', 'ž','q','x','y','w'};
    ArrayList<PomozniKlas> sifriraneCrke = new ArrayList<PomozniKlas>();
    ArrayList<PomozniKlas> referencneCrke = new ArrayList<PomozniKlas>();


    public static void main(String[] args) {
        JFrame frame = new JFrame("Ui");
        frame.setContentPane(new GUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }





    public String beri() throws IOException {
        String lokSifriranoBesedilo = lokacijaSifriranegaBesedilaTextField.getText();
        BufferedReader br = new BufferedReader(new FileReader(new File(lokSifriranoBesedilo)));
        String st;
        String p = "";
        while ((st = br.readLine()) != null) {
            p = p + st;
        }

        sifriranoBesedilo.setLineWrap(true);
        sifriranoBesedilo.setWrapStyleWord(true);
        sifriranoBesedilo.setText(p);




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

        StringBuilder rezultat = new StringBuilder(p);
        for (int i = 0; i < p.length(); i++) {
            for (int j = 0; j < sifriraneCrke.size(); j++) {
                if (p.charAt(i) == sifriraneCrke.get(j).crka) {
                    rezultat.setCharAt(i, referencneCrke.get(j).crka);
                }
            }
        }

        desifriranoBesedilo.setLineWrap(true);
        desifriranoBesedilo.setWrapStyleWord(true);
        desifriranoBesedilo.setText(rezultat.toString().toLowerCase());





    }


    public GUI() {


        desifrirajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sifriraneCrke.clear();
                referencneCrke.clear();
                System.out.println("klikno gumb");

                try {
                    Freq_refer fa = new Freq_refer();
                    fa.setReferBesedilo(lokacijaReferencnegaBesedilaTextField.getText());
                    fa.setFreqcrk();

                    Freq_sifra fd = new Freq_sifra();
                    fd.setSifriranoBesedilo(lokacijaSifriranegaBesedilaTextField.getText());
                    fd.setFreqsifrcrk();

                    System.out.println(lokacijaReferencnegaBesedilaTextField.getText());
                    System.out.println();
                    System.out.println(lokacijaSifriranegaBesedilaTextField.getText());


                    for(int i=0; i<fa.getFreqcrk().length; i++){
                        System.out.println(fa.getFreqcrk()[i]);
                    }
                    System.out.println();
                    for(int i=0; i<fd.getFreqsifrcrk().length; i++){
                        System.out.println(fd.getFreqsifrcrk()[i]);
                    }

                    indexiranje(fa.getFreqcrk(), fd.getFreqsifrcrk());

                    menjavaCrk(beri());
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


        zamenjajCrkiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                for(int i=0; i<referencneCrke.size(); i++){
                    if(referencneCrke.get(i).crka==novaCrka.getText().charAt(0)){
                        referencneCrke.get(i).setCrka(Character.toUpperCase(staraCrka.getText().charAt(0)));
                    }
                }
                for(int i=0; i<referencneCrke.size(); i++){
                    if(referencneCrke.get(i).crka==staraCrka.getText().charAt(0)){
                        referencneCrke.get(i).setCrka(novaCrka.getText().charAt(0));
                    }
                }
                try{
                    menjavaCrk(beri());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        izvoziButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    FileWriter writer = new FileWriter("MyFile.txt", true);
                    writer.write(desifriranoBesedilo.getText());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createUIComponents() {


    }


}
