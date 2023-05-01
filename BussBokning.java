import java.util.InputMismatchException;
import java.util.Scanner;

import java.lang.Math;

public class BussBokning    


{
    public static final int[] DATUM = {2023,04,12};
    
    public static int[][] platser = new int[][]{ 
      
       {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21}, 
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ,
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

       public static String[][] platserinfo = new String[][]{ 
    
        {"","","","","","","","","","","","","","","","","","","","",""}, 
        {"","","","","","","","","","","","","","","","","","","","",""}};
   
   static void sittplatser() {
        for (int i=0; i<21; i++) {
            if (platser[1][i] == 1) {
                System.out.print("x");
            }
            else {
                System.out.print(platser[0][i]);
            }
            if (platser[0][i]%4==0 && platser[0][i]!=20) {
                System.out.println();
            }
            else if (platser[0][i]%4==2 && platser[0][i]!=18) {
                if (platser[0][i]+1<10) {
                    System.out.print("    ");
                }
                else {
                    System.out.print("    ");
                }
            }
            else {
                if (platser[0][i]+1<11) {
                    System.out.print("  ");
                    if (platser[0][i]+1<9) {
                        System.out.print(" ");

                    }
                }
                else {
                    System.out.print(" ");
                }
                
            }
        }
    }


    static int HittaPlats (int Perssök) {
        int söktplats = 0;
        for (int i=0; i<20; i++){
            if (platser[2][i]==Perssök){
                söktplats = i+1;
            }
            else{
                continue;
            }
        }
        return söktplats;
    }
    static int HittaPlatsNamn (String Namnsök) {
        int söktplats = 0;
        for (int i=0; i<20; i++){
            if (platserinfo[0][i].equals(Namnsök)){
                söktplats = i+1;
            }
            else{
                continue;
            }
        }
        return söktplats;
    }

//här har du ordentlig robust kod har inte tid att gör för hela ;(
    static void bokning() {
        
        Scanner scanner = new Scanner(System.in);
int sittplats = 0;
int persnum = 0;
String namn = "";
String kön = "";

while (true) {
    System.out.println("Skriv in vilken sittplats du vill ha (1-21):");
    try {
        sittplats = scanner.nextInt();
        if (sittplats < 1 || sittplats > 21) {
            throw new InputMismatchException();
        }
        break;
    } catch (InputMismatchException e) {
        System.out.println("Ogiltigt val, var god ange en siffra mellan 1 och 21.");
        scanner.nextLine(); 
    }
}

while (true) {
    System.out.println("Skriv personnummer (ÅÅÅÅMMDD):");
    try {
        persnum = scanner.nextInt();
        scanner.nextLine();
        if (String.valueOf(persnum).length() != 8) {
            throw new InputMismatchException();
        }
        break;
    } catch (InputMismatchException e) {
        System.out.println("Ogiltigt personnummer, 8 siffror.");
    }
}

scanner.nextLine(); 


while (namn.isEmpty()) {
    System.out.println("Skriv in namn (För/efter):");
    namn = scanner.nextLine().trim();
}


while (true) {
    System.out.println("Skriv in kön (M/K):");
    kön = scanner.nextLine().trim().toUpperCase();
    if (kön.equals("M") || kön.equals("K")) {
        break;
    }
    System.out.println("Ogiltigt kön, var god ange M eller K.");
}
      
        

        if(platser[1][sittplats]==1){
            System.out.println("platsen är redan bokad");
        }
        else{
        platserinfo[0][sittplats] = namn;
        platserinfo[1][sittplats] = kön;
        platser[1][sittplats] = 1;
        platser[2][sittplats] = persnum;
        System.out.println("din plats är nu bokad");
      }
    }


    static void avbokning(int persbort) {
        int söktplats = 0;
        for (int i=0; i<20; i++){
            if (platser[2][i]==persbort){
                söktplats = i;
            }
            else{
                continue;
            }
        }
        if(söktplats==0){
            System.out.println("personummrert har inte bokat en plats");

        }
        else{
            platser[1][söktplats]=0;
            platser[2][söktplats]=0;
            System.out.println("din plats är avbokad");
        }
    }

        static int PersNumÅlder  (String personummer) {
            int year = Integer.parseInt(personummer.substring(0, 4));
            int month = Integer.parseInt(personummer.substring(4, 6));
            int day = Integer.parseInt(personummer.substring(6, 8));
            double[] ålder1 = {(DATUM[0]-year),(DATUM[1]-month),(DATUM[2]-day)};
            int age = (int) (Math.floor(ålder1[0]+(ålder1[1]/12)+(ålder1[2]/365)));
            return age;
        }

        public static void vinst(){
            int antalplats = 0;
            for (int i=0; i<20; i++){
                if (platser[1][i]==1){
                    antalplats += 1;
                }
                else{
                    continue;
                }
                
            }
            int personummerplatser[]  = new int[antalplats];
            int g = 0;
            for (int i=0; i<20; i++){
                if (platser[2][i]>0){
                    personummerplatser[g] = platser[2][i];
                    g = g+1;
                }
                else{
                    continue;
                }
            }

            int bokadeplatserunder18 = 0;
            int bokadeplatseröver18 = 0;
            int bokadeplatseröver69 = 0;
            for (int i=0; i<personummerplatser.length; i++){
                if(PersNumÅlder(Integer.toString(personummerplatser[i]))>=18){
                    bokadeplatseröver18+=1;
                }
                else if (PersNumÅlder(Integer.toString(personummerplatser[i]))<18){
                    bokadeplatserunder18+=1;
                }
                else if (PersNumÅlder(Integer.toString(personummerplatser[i]))>=69){
                    bokadeplatseröver69+=1;
                }
            }
         int vinst = bokadeplatserunder18*149+bokadeplatseröver18*299+bokadeplatseröver69*200;
         System.out.println("bussen har tjänat "+vinst +"kr");
            
        }

        // här är rekursion metoden men outputen funkar inte???
        public static int bokningskostnaduträkning(int under18, int over18, int over69) {
            int cost = 0;
            if (under18 == 0 && over18 == 0 && over69 == 0) {
                return 0; 
            } else {
                
                if (under18 > 0) {
                    cost += 149;
                    cost += bokningskostnaduträkning(under18-1, over18 , over69);
                }
                if (over18 > 0) {
                    cost += 299;
                    cost += bokningskostnaduträkning(under18, over18 - 1, over69);
                }
                if (over69 > 0) {
                    cost += 200;
                    cost += bokningskostnaduträkning(under18, over18 , over69-1);
                }
                return cost;
            }
        }


        static int[] antalålder(){
            int antalplats = 0;
            for (int i=0; i<20; i++){
                if (platser[1][i]==1){
                    antalplats += 1;
                }
                else{
                    continue;
                }
                
            }
            int personummerplatser[]  = new int[antalplats];
            int g = 0;
            for (int i=0; i<20; i++){
                if (platser[2][i]>0){
                    personummerplatser[g] = platser[2][i];
                    g = g+1;
                }
                else{
                    continue;
                }
            }

            int bokadeplatserunder18 = 0;
            int bokadeplatseröver18 = 0;
            for (int i=0; i<personummerplatser.length; i++){
                if(PersNumÅlder(Integer.toString(personummerplatser[i]))>=18){
                    bokadeplatseröver18+=1;
                }
                else if (PersNumÅlder(Integer.toString(personummerplatser[i]))<18){
                    bokadeplatserunder18+=1;
                }
            }
            int åldrar[] ={(bokadeplatserunder18),(bokadeplatseröver18)};
            return åldrar;
        }





        public static void sorteing(){
        //Tar fram antelet plateser som är bokade
            int antalplats = 0;
            for (int i=0; i<20; i++){
                if (platser[1][i]==1){
                    antalplats += 1;
                }
                else{
                    continue;
                }
            }

        //Skapar två arrayer som är längden av antalet platser  
            int personummerplatserålder[]  = new int[antalplats];
            int personummerplatser[] = new int[antalplats];
            int g = 0;
            for (int i=0; i<20; i++){
                if (platser[2][i]>0){
                    personummerplatserålder[g] = PersNumÅlder(Integer.toString(platser[2][i]));
                    personummerplatser[g] = i;
                    g = g+1;
                }
                else{
                    continue;
                }
            }  
        //går igenom alla platser och ger ena arrayen värdet på personummret och andra åldern på personen som sitter på den platsen 
            


            int temp = 0;          
            for (int i = 0; i <personummerplatserålder.length; i++) {  
              for (int j = i+1; j <personummerplatserålder.length; j++) {     
                //För varje element i personummerplatserålder jämförs det med varje efterföljande element i arrayen
                  if(personummerplatserålder[i] >personummerplatserålder[j]) {  
                    // Om det efterföljande elementet är mindre än det aktuella elementet så byter de plats i arrayen.
                    // Samtidigt byter också motsvarande element i personummerplatser-arrayen plats.   
                     temp = personummerplatserålder[i];    
                     personummerplatserålder[i] = personummerplatserålder[j];    
                     personummerplatserålder[j] = temp;  
                     temp = personummerplatser[i];    
                     personummerplatser[i] =personummerplatser[j];    
                     personummerplatser[j] = temp;  
                   }     
                }     
            }
            //Sorterar åldrarna från minst till störst och anger samtidigt vilken plats den åldern sitter på 
            
            System.out.println("info för platser i ordninng");
            for (int i=0; i<antalplats; i++){
                System.out.println("plats "+i+" i ordningen");
                System.out.println("------------------------");
                System.out.println("sitter på plats: "+ (personummerplatser[i]));
                System.out.println("personummer: "+platser[2][personummerplatser[i]]);
                System.out.println("Namn: "+platserinfo[0][personummerplatser[i]]);
                System.out.println("Kön: "+ platserinfo[1][personummerplatser[i]]);
                System.out.println("ålder: "+ personummerplatserålder[i]);
                System.out.println("------------------------");
                // Printar ut allt i ordning baserat på tidigare alorithmer 
            }
         }
     public static void antalplatts(){
        int bokadeplatser = 0;
            for (int i=0; i<20; i++){
                if (platser[1][i]==1){
                    bokadeplatser += 1;
                }
                else{
                    continue;
                }
            }
            System.out.println("det finns:"+(21-bokadeplatser)+" kvar");
     }




   
   public static void main(String[] args)
   {   
    
    Scanner helium1 = new Scanner(System.in);
 
  
     
    while(true){
        System.out.println("välj instruktion: 1.bokning 2.Antal lediga platser 3.Beräkna vinst 4.Hitta bokning 5.kolla alla över/under 18 6.Ta bort bokning 7.Sorterad lista 8.Rekursion problem??? ");
        int playerMove = helium1.nextInt();
        switch (playerMove) {
            case 1:
            //printa ut en ilustration av bussen med markerade bokningar 
            sittplatser();
            //boka plats
            bokning();
            break;
            case 2:
            antalplatts();
            break;
            case 3:
             //beräkna vinst
             vinst();
            break;
            case 4:
             //hitta bokning 
             helium1.nextLine();
             System.out.println("vill du söka efter plats med Namn eller Personummer?");
             String svar = helium1.nextLine();
             if(svar.equals("Namn")){
                System.out.println("Skriv namnet för platsen du vill hitta:För efter");
                    String namnsök = helium1.nextLine();
                    int söktplats = HittaPlatsNamn(namnsök);
                    if(söktplats==0){
                        System.out.println("personen har inte bokning");
                    }
                    else{
                        System.out.println("presonummret sitter på plats "+söktplats );
                    }
                }
             else if (svar.equals("Personummer")){
                    System.out.println("Skriv presonummret för platsen du vill hitta:ÅÅÅÅMMDD");
                    int Perssök = helium1.nextInt();
                    int söktplats = HittaPlats(Perssök);
                    if(söktplats==0){
                        System.out.println("personen har inte bokning");
                    }
                    else{
                        System.out.println("presonummret sitter på plats "+söktplats );
                    }
                }
             else{
                System.out.println("ogiltigt svar");
                break;
             }
            break;
            case 5:
            
            // skriva ut alla personer över och under 18
            int[] result = antalålder();
            System.out.println("det finns "+ result[0]+" under 18"+ " och det finns "+result[1]+ " över 18" );
        
            
            break;
            case 6:
             //ta bort bokning 
             System.out.println("Skriv presonummret för plats du avboka:ÅÅÅÅMMDD");
             int persbort = helium1.nextInt();
             avbokning(persbort);

           
            
           
            break;
            case 7:
            //utskrift av bokningar sorterad äldst först yngst sist Utskriften ska innehålla förnamn, efternamn,födelsedatum & plats.
            sorteing();

            break;
            case 8:
            //problem med rekursion 
            System.out.println("referera till metoden bokningskostnaduträkning");
            System.out.println("t.e.x");
            int A = 2;
            int B = 2;
            int C = 2; 
            System.out.println(bokningskostnaduträkning(A,B,C));    
            System.out.println("ingen aning varför den printar 58320 borde printa rätt pris?????");

            break;
          
            default:
                System.out.println("Invalid move.");
        
        }
      }
    }        
}
