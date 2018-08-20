import java.util.Scanner;

public class Sequencer {
    public static void main(String[] args) {

        /* part 1: Reconstruction
            TODO: check the validation of the input DNA sequence and resort it.
         */
        Scanner in = new Scanner(System.in);
        String dna = "";
        String line;
        System.out.println("Input lowercase DNA fragments one line at a time. End with a blank line.");

        // join dna sequences while receiving inputs
        while (in.hasNextLine()) {
            line = in.nextLine();
            line = line.toLowerCase().trim();

            if (line.equals("")) {
                break;
            } else {
                if (dna.equals(""))
                    dna = line;
                else {
                    int len = dna.length();
                    int len1 = line.length();
                    int num = 0;
                    int j = 0;

                    for (int i = 0; i < len; i++){
                        while (i < len && j < len1) {
                            if (dna.charAt(i) == line.charAt(j)) {
                                num++;
                                i++;
                                j++;
                            } else {
                                num = 0;
                                j = 0;
                                break;
                            }
                        }
                }
                    int end = len - num;
                    dna = dna.substring(0, end) + line.substring(0, len1);

                }
            }
        }
        dna = dna.trim();
        int length = dna.length();
        int a = 0;

        // check validation of the dna sequence
        while (a < length) {
            if (dna.charAt(a) == 'a' || dna.charAt(a) == 't' || dna.charAt(a) == 'c' || dna.charAt(a) == 'g') {
                a++;
            } else {
                break;
            }
        }

        // print out dna sequences if it is not valid
        if (a < length) {
            System.out.println("DNA is invalid.");
            System.out.println("Input DNA: " + dna);
        }
        else {
         /* part 2: Finding a Gene
         TODO: Find the start and end codon position if there are any of them and print the new DNA sequence.
          */
            System.out.println("Input DNA: " + dna);

            // initialize values
            int codon1 = 0;
            String seg1;
            String seg2;
            int codon2 = 0;
            int index = 0;
            int b=0;

            // find first start codon
            while (index == 0) {
                seg1 = dna.substring(b, length);
                if (!seg1.startsWith("atg")){
                    b++;
                    if (b >= length-2) {
                        System.out.println("DNA does not contain a gene start codon");
                        return;
                    }
                }
                else {
                    codon1 = b;
                    index = -1;
                }
            }

            // finding first end codon
            int index1 = 0;
            int c = codon1 + 3;

            while (index1 ==0) {
                seg2 = dna.substring(c, length);

                if (!seg2.startsWith("tag") && !seg2.startsWith("taa") && !seg2.startsWith("tga")) {
                    c = c + 3;
                    if (seg2.length() < 3) {
                        System.out.println("DNA does not contain a gene end codon");
                        return;
                    }
                } else {
                    codon2 = c ;
                    index1 = -1;
                }
            }

            if (((codon2 - codon1) % 3) != 0){
                System.out.println("DNA does not contain a gene end codon");
                return;
            }
            String gene = dna.substring(codon1,codon2);
            System.out.printf("Start codon position: %d\nEnd codon position: %d\nGene: %s\n", codon1,codon2, gene);

                /* part 3: Decoding the Gene
                TODO: Determine a person's eye color, hair color and whether or not she/he can roll her/his tongue based on the nucleotides in the her/his DNA sequence.
                 */

            if (gene.length() < 30) {
                System.out.printf("\nThe gene is not long enough to continue.\n");
                return;
            }
            else {
                System.out.printf("\nAnalysis Results\n\n");
                char p20;
                char p18;
                char p8;
                String eye="";
                String hair="";
                String tongue ="";

                p20 = gene.charAt(20);
                p18 = gene.charAt(18);
                p8 = gene.charAt(8);

                switch (p20){
                    case 'a': eye = "blue";
                        break;
                    case 't': eye = "green";
                        break;
                    case 'c': eye = "brown";
                        break;
                    case 'g': eye = "brown";
                        break;
                }
                System.out.println("Eye color: " + eye);

                switch (p18){
                    case 'a': hair = "black";
                        break;
                    case 't': hair = "blond";
                        break;
                    case 'c': hair = "brown";
                        break;
                    case 'g': hair = "red";
                        break;
                }
                System.out.println("Hair color: " + hair);

                switch (p8){
                    case 'a': tongue = "yes";
                        break;
                    case 't': case 'c': case 'g': tongue = "no";
                        break;
                }
                System.out.println("Can roll tongue? " + tongue);
            }
        }
    }
}