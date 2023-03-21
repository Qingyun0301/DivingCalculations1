package divingcalculations;

import java.util.Scanner;

public class DiveBroker {
    DiveFormulas diveF = new DiveFormulas();

    public void helpOption() {
        System.out.println(
                "\nYou can select from the following choices: \n" +
                        "1. HELP\tprints this message.\n" +
                        "2. MOD\t(Maximum Operating Depth) for a supplied percentage of Oxygen and partial pressure.\n" +
                        "3. SMOD\t(Standard Maximum Operating Depth) for a supplied percentage of Oxygen and a standard 1.4 partial pressure.\n" +
                        "4. BM\t(Best Mix) for a dive with a supplied partial pressure and depth (in metres).\n" +
                        "5. PP\t(Partial Pressure) for a supplied percentage of Oxygen and depth (in metres).\n" +
                        "6. EAD\t(Equivalent Air Depth) for a supplied percentage of Oxygen and depth (in metres).\n" +
                        "7. EXIT\tExit the Dive Formula Calculator.");
    }

    public void modOption() {
        int oxy, depth_meter;
        double fg, pg, depth_ata;
        System.out.println("Calculating the MOD");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the percentage of Oxygen: ");
        oxy = scan.nextInt();
        fg = oxy / 100.0;
        System.out.print("Enter the partial pressure of Oxygen (between 1.1 and 1.6 inclusive): ");
        pg = scan.nextDouble();
        depth_ata = diveF.calculateMOD(pg, fg);
        depth_meter = (int) ((depth_ata - 1) * 10);
        System.out.println("Maximum operating depth (MOD) for a dive with " + oxy + "% O2 and a partial pressure of " + pg
                + " is " + depth_meter + " metres");
    }

    public void smodOption() {
        int oxy, depth_meter;
        double fg, depth_ata;
        System.out.println("Calculating the MOD for the standard 1.4 partial pressure");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the percentage of Oxygen: ");
        oxy = scan.nextInt();
        fg = oxy / 100.0;
        depth_ata = diveF.calculateSMOD(fg);
        depth_meter = (int) ((depth_ata - 1) * 10);
        System.out.println("Maximum operating depth (MOD) for a dive with " + oxy + "% O2 and a partial pressure of 1.4"
                + " is " + depth_meter + " metres");
    }

    public void bmOption() {
        double pg;
        int depth, fg;
        System.out.println("Calculating the Best Mix");
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the partial pressure of Oxygen (between 1.1 and 1.6 inclusive): ");
        pg = in.nextDouble();
        System.out.print("Enter the depth of the dive(in metres): ");
        depth = in.nextInt();
        fg = diveF.calculateBM(pg, depth);
        System.out.println("Best mix for a drive to " + (double) depth + " metres with a partial pressure of " + pg
                + " is " + fg + "% O2");
    }

    public void ppOption() {
        int depth, oxy;
        double pg, fg;
        System.out.println("Calculating the Partial Pressure");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the depth of the dive(in metres): ");
        depth = scan.nextInt();
        System.out.print("Enter the percentage of Oxygen: ");
        oxy = scan.nextInt();
        fg = oxy / 100.0;
        pg = diveF.calculatePP(depth, fg);
        System.out.println("The partial pressure of Oxygen for a dive to " + (double) depth + " with a percentage of Oxygen of " + oxy + "% is " + pg + " ata");
    }


    public void eadOption() {
        int oxy, depth_meter, ead;
        double depth_ata, fg;
        System.out.println("Calculating the Equivalent Air Depth");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the depth of the dive (in metres): ");
        depth_meter = scan.nextInt();
        depth_ata = depth_meter / 10.0 + 1;
        System.out.print("Enter the percentage of Oxygen: ");
        oxy = scan.nextInt();
        fg = oxy / 100.0;
        ead = (int) (diveF.calculateEAD(fg, depth_ata));
        System.out.println("Equivalent Air Depth for a dive with " + oxy + "% O2 to a depth of " + (double) depth_meter + " metres is " + ead + " metres");
    }

    public void pptOption() {
        System.out.print("\nGenerating Partial Pressures Table\n" +
                "Enter a start and end percentage of Oxygen: ");
        Scanner scan = new Scanner(System.in);
        int start_oxy = scan.nextInt();
        int end_oxy = scan.nextInt();
        // to check if user inputs are valid or not
        while (start_oxy > end_oxy) {
            System.out.print("The end values needs to be lager than start values.\n" +
                    "Please reenter: ");
            start_oxy = scan.nextInt();
            end_oxy = scan.nextInt();
        }
        System.out.print("Enter a start and end depth (in metres): ");
        int start_depth = scan.nextInt();
        int end_depth = scan.nextInt();
        while (start_depth > end_depth) {
            System.out.print("The end values needs to be lager than start values.\n" +
                    "Please reenter: ");
            start_depth = scan.nextInt();
            end_depth = scan.nextInt();
        }
        System.out.println("Partial Pressures Table for " + start_oxy + " to " + end_oxy + " percent Oxygen" +
                " and depths of " + start_depth + " to " + end_depth + " metres\n" +
                "=================================================================================");

        int rows = (end_depth - start_depth) / 3 + 1; // 9 rows in total
        int columns = (end_oxy - start_oxy) + 1; // 8 columns in total
        int depth_meter = start_depth;
        double depth_ata = (depth_meter / 10.0) + 1;
        double[][] table = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            int oxy = start_oxy;
            for (int j = 0; j < columns; j++) {
                table[i][j] = (oxy / 100.0) * depth_ata;
                oxy += 1;
            }
            depth_meter += 3;
            depth_ata = (depth_meter / 10.0) + 1;
        }
        //invoke the print method to print the result
        diveF.print_PPTable(table, start_oxy, end_oxy, start_depth);
    }

    public void eadtOption() {
        System.out.print("\nGenerating Equivalent Air Depths Table\n" +
                "Enter a start and end percentage of Oxygen: ");
        Scanner scan = new Scanner(System.in);
        int start_oxy = scan.nextInt();
        int end_oxy = scan.nextInt();
        // to check if user inputs are valid or not
        while (start_oxy > end_oxy) {
            System.out.print("The end values needs to be lager than start values.\n" +
                    "Please reenter: ");
            start_oxy = scan.nextInt();
            end_oxy = scan.nextInt();
        }
        System.out.print("Enter a start and end depth (in metres): ");
        int start_depth = scan.nextInt();
        int end_depth = scan.nextInt();
        while (start_depth > end_depth) {
            System.out.print("The end values needs to be lager than start values.\n" +
                    "Please reenter: ");
            start_depth = scan.nextInt();
            end_depth = scan.nextInt();
        }
        System.out.println("Equivalent Air Depth Table for " + start_oxy + " to " + end_oxy + " percent Oxygen" +
                " and depths of " + start_depth + " to " + end_depth + " metres\n" +
                "=================================================================================");

        int rows = (end_depth - start_depth) / 3 + 1;
        int columns = (end_oxy - start_oxy) + 1;
        int depth_meter = start_depth;
        double depth_ata = (depth_meter / 10.0) + 1;
        int[][] table = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            int oxy = start_oxy;
            for (int j = 0; j < columns; j++) {
                table[i][j] = (int) (Math.round(((((1 - (oxy / 100.0)) * depth_ata) / 0.79) - 1) * 10));
                oxy += 1;
            }
            depth_meter += 3;
            depth_ata = (depth_meter / 10.0) + 1;
        }
        //invoke the print method to print the result
        diveF.print_EadtTable(table, start_oxy, end_oxy, start_depth);
    }
}
