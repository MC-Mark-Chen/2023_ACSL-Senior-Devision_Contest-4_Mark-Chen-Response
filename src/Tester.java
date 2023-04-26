public class Tester {
    public static void main(String[] args)
    {
        System.out.println("Result: " + Result.missingArrow(4, "02 11 20 21", "0103 1012", "13E 30B 33E"));
        System.out.println("Expected: 32E\n");
        
        System.out.println("Result: " + Result.missingArrow(6, "15 23 24 32 33 34 42 43 51", "401211 401211", "00G 20G 40G 53B 02G 03D 04G 35A"));
        System.out.println("Expected: 30C\n");
        
        System.out.println("Result: " + Result.missingArrow(6, "00 10 13 20 21 24 30 31 43 50 53", "420113 022124", "01H 02H 03H 04H 15H 14H 45A 51B 55E 52E"));
        System.out.println("Expected: 35H\n");

        System.out.println("Result: " + Result.missingArrow(6, "02 05 13 23 12 35 24 00", "011114 410111", "14F 20F 50F 53F 51F 40F 30F"));
        System.out.println("Expected: 55E\n");
        
        System.out.println("Result: " + Result.missingArrow(6, "01 10 23 42 53 12 04 52 00", "200232 111024", "05H 34H 35H 54E 55E 40F 45A 41B"));
        System.out.println("Expected: 02D\n");

        System.out.println((int)3.8);
    }
}
