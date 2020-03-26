package ar.com.playmedia;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Main {

  static String[] words = { "verde", "rojo", "azul", "purpura" , "morado", "dorado", "turquesa", "amarillo"};
  static char[] wordFound;
  static Integer lives = 7;
  static Integer errors = 0;
  static Random r = new Random();
  static String word = "";
  static Scanner keyboard = new Scanner(System.in);
  static ArrayList<String> letters = new ArrayList<String>();


  public static String nextWord() {
    return words[r.nextInt(words.length)];
  }

  public static void newGame() {
    errors = 0;
    letters.clear();
    word = nextWord();
    wordFound = new char[word.length()];

    for (int i = 0; i < wordFound.length; i++) {
      wordFound[i] = '_';
    }
  }

  public static boolean correctWord() {
    return word.contentEquals(new String(wordFound));
  }

  public static void enter (
          final String a) {
      if (!letters.contains(a)) {
          if (word.contains(a + "")) {
              int index = word.indexOf(a);

              while (index >= 0) {
                  wordFound[index] = a.charAt(0);
                  index = word.indexOf(a, index + 1);
              }
          } else {
              errors++;
          }
          letters.add(a);
      }
  }

  public static String wordFoundContent() {
      final StringBuilder builder = new StringBuilder();

      for (int i = 0; i < wordFound.length; i++) {
          builder.append(wordFound[i]);

          if (i < wordFound.length - 1) {
              builder.append(" ");
          }
      }
      return builder.toString();
  }

  public static void play() {
      do {
          System.out.print("Escribi una letra: ");
          String letter = keyboard.nextLine();

          if (letter.length() < 1) {
              letter = letter.substring(0, 1);
          }

          enter(letter);

          System.out.println(wordFoundContent());

          if (correctWord()) {
              System.out.println("Ganaste!!");
              break;
          } else {
              System.out.println("Te quedan " + (lives - errors) + " vidas");
          }
      } while (errors < lives);

      if (errors == lives) {
          System.out.println("Perdiste!!");
          System.out.println("La palabra correcta era " + word);
      }
  }

  public static void main(final String[] args) {

    newGame();
    play();
  }
}