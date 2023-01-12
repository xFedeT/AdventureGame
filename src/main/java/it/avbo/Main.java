package it.avbo;

import java.util.Random;

public class Main {

    private static short[][] stanze = new short[5][5];

    public static void main(String[] args) {
        generateRooms();
    }


    private static void generateRooms() {
        int nStanze = 0;

        System.out.println("| =  =  =  =  = |");

        int bossRand = new Random().nextInt(4);
        switch (bossRand) {
            case 0 -> {
                stanze[0][2] = 3;
            }
            case 1 -> {
                stanze[2][4] = 3;
            }
            case 2 -> {
                stanze[4][2] = 3;
            }
            case 3 -> {
                stanze[2][0] = 3;
            }
        }

        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                int random = new Random().nextInt(3);

                if (random == 0 && stanze[x+2][y+2] != 3 && nStanze < 7) {
                    stanze[x+2][y+2] = 1;
                    nStanze++;
                }
            }
        }

        for (int a = 0; a < 5; a++) {
            System.out.print("| ");
            for (int b = 0; b < 5; b++) {
                if (b == 4) {
                    System.out.print(stanze[b][a]);
                } else {
                    System.out.print(stanze[b][a] + "  ");
                }
            }
            System.out.println(" |");
        }

        System.out.println("| =  =  =  =  = |");
    }


}