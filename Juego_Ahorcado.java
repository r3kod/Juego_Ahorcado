package juego_ahorcado;

import java.util.Arrays;

/**
 * @author ~#Dr_R3kod#~
 */

public class Juego_Ahorcado {
    public static void main(String[] args) {
        int op;
        final String[] DB_Default_Words = {"MANANTIAL", "ORDENADOR", "VEGETALES"};

        do{
            System.out.println("\n\tJUEGO DEL AHOGADO\n");
            System.out.println("1.- NUEVA PARTIDA");
            System.out.println("2.- PARTIDA PERSONALIZADA");
            System.out.println("3.- MANUAL");
            System.out.println("4.- EXIT");
            System.out.print("Opción: ");
            op = Entrada.entero();
            
            switch(op){
                case 1:
                    Game(DB_Default_Words);
                    break;
                case 2:
                    Game(Custom_Game());
                    break;
                case 3:
                    Manual();
                    break;
                case 4:
                    System.out.println("BYE!");
            }
        }while((op<4)&&(op>0));
    }
    
    private static void Game(String[] Word_DataBase){
        final int max_fails = 5;
        int fail_counter = 0, round_counter = 1;
        char word;
        char[] secret_word = secret_word_Constructor(Word_DataBase);
        char[] user_word = user_word_Constructor(secret_word);
        char[] error_words = error_words_Constructor(max_fails);

        while(!Arrays.equals(secret_word, user_word)&&(fail_counter < max_fails)){
            boolean verif = false;
            print_round_counter(round_counter);
            print_Stickman(fail_counter);
            print_error_words(error_words);
            print_space_word(secret_word, user_word);
            
            word = Input_word(error_words);
            
            for(int i=0; i<secret_word.length; i++)
                if(secret_word[i] == word){
                    user_word[i] = word;
                    verif = true;
                }
            if(verif == false){
                fail_counter++;
                error_words[fail_counter-1] = word;
                print_error_words(error_words);
            }
            round_counter++;
        }
        
        if(fail_counter == max_fails){
            System.out.println("DEAD!");
            print_dead(round_counter);
            print_Stickman(fail_counter);
            print_error_words(error_words);
        }else if(Arrays.equals(secret_word, user_word))
            print_win(round_counter, secret_word);
    }
    
    private static String[] Custom_Game(){
        System.out.print("Cantidad de palabras: ");
        String[] DB_Custom_Words = new String[Entrada.entero()];
        for(int d=0; d<DB_Custom_Words.length; d++){
            System.out.print("Palabra " + (d+1) + ": ");
            DB_Custom_Words[d] = Entrada.cadena().toUpperCase();
        }
        return DB_Custom_Words;
    }
    
    private static void Manual(){
        System.out.println("\tMANUAL:");
        System.out.println("1. Nueva Partida: Comienza una partida con palabras por defecto del juego.");
        System.out.println("2. Partda Peronalizada: Pide la cantidad de palabras, las palabras, y");
        System.out.println("\tcomienza la partida con las palabras introducidas.");
        System.out.println("\n* El juego acaba solo de dos formas: Winner, Dead.");
        System.out.println("* Dead se mostrará como:");
        System.out.println("=====");
        System.out.println("=   O");
        System.out.println("= --|--");
        System.out.println("=  | |");
        System.out.println("=");
        System.out.println("======");
        System.out.println("* Las palabras en cada partida se generan de forma aleatoria.");
        System.out.println("* No existe límite de palabras en una partida personalizada.");
        System.out.println("* Cualquier entrada de dato que no sea una letra contará como fallo.");
        System.out.println("* Introducir letras erróneas dos o más veces no contará como error.");
    }
    
    private static char[] secret_word_Constructor(String[] Word_DataBase){
        int min = 0, max = Word_DataBase.length;
        char[] secret_word = Word_DataBase[min + (int)(Math.random()*(max - min))].toCharArray();
        return secret_word;
    }
    
    private static char[] user_word_Constructor(char[] secret_word){
        char[] user_word = new char[secret_word.length];
        for(char c : secret_word)
            c = 0;
        return user_word;
    }
    
    private static char[] error_words_Constructor(int max_fails){
        char[] error_words = new char[max_fails];
        for(char c : error_words)
            c = 0;
        return error_words;
    }
    
    private static char Input_word(char[] error_words){
        char word = 0;
        boolean flag = true;
        while(flag){
            boolean flag_exist = false;
            System.out.print("\nLetra: ");
            word = Entrada.caracter();
            
            for(char c : error_words)
                if(c == Character.toUpperCase(word))
                    flag_exist = true;
            
            if(flag_exist == false)
                flag = false;
            else System.out.print("¡Ya has intentado usar esa letra!");
        }
        return Character.toUpperCase(word);
    }
    
    private static void print_win(int round_counter, char[] secret_word){
        System.out.println("\n_______________________________");
        System.out.print("¡WINNER!\tDuración = " + (round_counter-1) + " rondas\tPalabra secreta: ");
        for(int w=0; w<secret_word.length; w++)
            System.out.print(secret_word[w]);
        System.out.println("\n_______________________________");
    }
    
    private static void print_dead(int round_counter){
        System.out.println("\n_______________________________");
        System.out.print("¡DEAD!\tHas durado " + (round_counter-1) + " rondas");
        System.out.println("\n_______________________________");
    }
    
    private static void print_round_counter(int round_counter){
        System.out.println("\n_______________________________");
        System.out.println("Ronda nº = " + round_counter);
    }
    
    private static void print_space_word(char[] secret_word, char[] user_word){
        System.out.print("\t\t");
        for(int j=0; j<secret_word.length; j++){
            if(secret_word[j] == user_word[j]){
                System.out.print(user_word[j]);
            }else System.out.print("_ ");
        }
    }
    
    private static void print_Stickman(int fail_counter){
        switch(fail_counter){
            case 0:
                System.out.println("\nFALLOS = " + fail_counter);
                System.out.println("=====");
                System.out.println("=");
                System.out.println("=");
                System.out.println("=");
                System.out.println("=");
                System.out.println("======");
                break;
            case 1:
                System.out.println("\nFALLOS = " + fail_counter);
                System.out.println("=====");
                System.out.println("=   O");
                System.out.println("=   |");
                System.out.println("=");
                System.out.println("=");
                System.out.println("======");
                break;
            case 2:
                System.out.println("\nFALLOS = " + fail_counter);
                System.out.println("=====");
                System.out.println("=   O");
                System.out.println("= --|");
                System.out.println("=");
                System.out.println("=");
                System.out.println("======");
                break;
            case 3:
                System.out.println("\nFALLOS = " + fail_counter);
                System.out.println("=====");
                System.out.println("=   O");
                System.out.println("= --|--");
                System.out.println("=");
                System.out.println("=");
                System.out.println("======");
                break;
            case 4:
                System.out.println("\nFALLOS = " + fail_counter);
                System.out.println("=====");
                System.out.println("=   O");
                System.out.println("= --|--");
                System.out.println("=  |");
                System.out.println("=");
                System.out.println("======");
                break;
            case 5:
                System.out.println("\nFALLOS = " + fail_counter);
                System.out.println("=====");
                System.out.println("=   O");
                System.out.println("= --|--");
                System.out.println("=  | |");
                System.out.println("=");
                System.out.println("======");
                break;
        }
    }
    
    private static void print_error_words(char[] error_words){
        System.out.print("Letras incorrectas hasta el momento: ");
        for(int j=0; j<error_words.length; j++)
            System.out.print(error_words[j] + " ");
        System.out.println("");
    }    
}
