package divingcalculations;

import java.text.DecimalFormat;

/**
 * DiveFormulas Class includes all the calculation methods that
 * help user to calculate MOD,SMOD,BM,PP and EAD.
 * It also provides method to generate EAD table and PP table.
 *
 * @QingyunChen
 * @9/21/2022
 */
public class DiveFormulas {

    public double calculateMOD(double pg, double fg) {
        return pg / fg;
    }

    public double calculateSMOD(double fg) {
        return 1.4 / fg;
    }

    public int calculateBM(double pg_ata, int depth) {
        double depth_ata;
        depth_ata = depth / 10.0 + 1;
        return (int) ((pg_ata / depth_ata) * 100);
    }

    public double calculatePP(int depth, double fg) {
        double depth_ata, pg;
        depth_ata = depth / 10.0 + 1;
        DecimalFormat df = new DecimalFormat("0.00");
        pg = fg * depth_ata;
        return Double.parseDouble(df.format(pg));
    }

    public double calculateEAD(double fg, double depth_ata) {
        DecimalFormat df = new DecimalFormat("0.00");
        double ead_ata = ((1 - fg) * depth_ata) / 0.79;
        ead_ata = Double.parseDouble(df.format(ead_ata));
        int ead_meter = (int) ((ead_ata - 1) * 10);
        return ead_meter;
    }


    public void print_PPTable(double[][] table, int start_oxy, int end_oxy, int start_depth) {
        //first print out the oxygen rows
        System.out.print("\\      ");
        for (int i = start_oxy; i <= end_oxy; i++) {
            System.out.print(i + "    ");
        }
        System.out.println("\n——————————————————————————————————————————————————————————");
        //then print the partial pressures value
        int depth = start_depth;
        for (int j = 0; j < table.length; j++) {
            System.out.print(depth + "     ");
            for (int k = 0; k < table[0].length; k++) {
                if (table[j][k] > 1.6) {
                    System.out.print("     ");
                } else {
                    System.out.format("%.2f  ", table[j][k]);
                }
            }
            System.out.println();
            depth += 3;
        }
    }

    public void print_EadtTable(int[][] table, int start_oxy, int end_oxy, int start_depth) {
        //first print out the oxygen rows
        System.out.print("\\      ");
        for (int i = start_oxy; i <= end_oxy; i++) {
            System.out.print(i + "    ");
        }
        System.out.println("\n————————————————————————————————————");
        //then print the equivalent air depth value
        int depth = start_depth;
        for (int j = 0; j < table.length; j++) {
            System.out.print(depth + "     ");
            for (int k = 0; k < table[0].length; k++) {
                System.out.print(table[j][k] + "     ");
            }
            System.out.println();
            depth += 3;
        }
    }
}
