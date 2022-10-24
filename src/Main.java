import java.security.PublicKey;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        HashMap<String,String> matches = new HashMap<>();
        HashMap<String,String> matchesWords = new HashMap<>();
        fillNumbers(matches);


        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");
        String number = in.nextLine();
        String part;
        String th="";
        int count=0;
        int order=0;
        String zeros="";
        int k =0;
        ArrayList<String> tens = new ArrayList<>();
        tens.add("thousand");
        tens.add("million");
        tens.add("trillion");
        tens.add("billion");

        try {
            long l = Long.parseLong(number);
            char[] charArray = number.toCharArray();
            returnReversArray(charArray);

            String answer="";

            for (int i=0; i<charArray.length; i++){


                if(k==1){
                    k=0;
                }
                else {
                    part = "" + charArray[i];

                    if (part.equals("-")) {}
                    else {
                        if (!part.equals("0")) {
                            for (int j = 0 + order; j < i; j++) {
                                part += "0";
                            }
                            if (part.length() > 2) {
                                char[] charPart = part.toCharArray();
                                part = "";
                                for (int g = 0; g < charPart.length; g++) {
                                    if (charPart[g] != '0') {
                                        th += charPart[g];
                                    } else {
                                        part += charPart[g];
                                    }
                                }
                            }
                        }
                    }
                    if (matches.containsKey(part)) {
                        if(part.equals("-")){
                            String[] words = answer.split(" ",0);
                            if(tens.contains(words[0])){
                                answer = answer.replace(words[0],"");
                            }
                        }
                        if (i == 0 + order&&i<charArray.length-1) {
                            String s = "" + charArray[i + 1] + charArray[i];
                            if (matches.containsKey(s)) {
                                answer = matches.get(s) + " " + answer;
                                k = 1;
                            }
                            else {
                                answer = matches.get(part) + " " + answer;

                                if (!th.equals("")) {
                                    answer = matches.get(th) + " " + answer;
                                    th = "";
                                }
                            }
                        }
                        else {
                            answer = matches.get(part) + " " + answer;

                            if (!th.equals("")) {
                                answer = matches.get(th) + " " + answer;
                                th = "";
                            }
                        }
                    }
                }
                count++;
                if (count % 3 == 0 && i != charArray.length - 1) {
                    order += 3;
                    zeros += "000";
                    String[] words = answer.split(" ",0);
                    if(tens.contains(words[0])){
                        answer = answer.replace(words[0],"");
                    }
                    answer = matches.get(zeros) + " " + answer;
                }
            }
            System.out.println(answer);
        }


        catch (Exception NumberFormatException) {
            reversHash(matches,matchesWords);
            String answer="";
            String[] words = number.split(" ",0);


            int num=0;
            Boolean hund=false;
            int c=0;
            String b="";
            Boolean minus =false;
            if(Arrays.asList(words).contains("minus")){
                minus=true;
                String g =number.replace("minus", "");
                words = g.split(" ",0);
            }
            returnReversArrayString(words);

            for (String word : words) {
                if (!tens.contains(word)) {
                    if (!hund){
                        if (word.equals("hundred")) {
                            hund = true;
                        } else {
                            if(matchesWords.containsKey(word)) {
                                num += Integer.parseInt(matchesWords.get(word));
                            }
                        }
                    }
                    else{
                        num=(Integer.parseInt(matchesWords.get(word))*100)+num;
                        hund=false;
                    }
                }
                else{
                    c=Integer.parseInt("1"+matchesWords.get(word));
                    String a =String.valueOf(c/10);
                    if(answer.equals("")) {
                        b = String.valueOf(num);
                    }
                    else {
                        b = String.valueOf(num) +answer;
                    }
                    if(b.length()<a.length()){
                        while (b.length()<a.length()){
                            b="0"+b;
                        }
                        answer=b;

                    }
                    else {
                        answer=String.valueOf(num)+answer;
                    }
                    num=0;
                }

            }
            answer=String.valueOf(num)+answer;
            if(minus){
                answer="-"+answer;
            }
            if(answer.equals("0")){
                System.out.println("the program could not select numbers from the incoming text");
            }
            else {
                System.out.println(answer);
            }
        }
    }

   public static void reversHash(HashMap<String,String> oldHasMap, HashMap<String,String> newHasMap){
       ArrayList<String> keys = new ArrayList<>(oldHasMap.keySet());
       ArrayList<String> values = new ArrayList<>(oldHasMap.values());
        for (int i =0; i<oldHasMap.size(); i++){
            newHasMap.put(values.get(i),keys.get(i));
        }

   }


    public static void fillNumbers(HashMap<String, String> hashMap){
        hashMap.put("-","minus");
        hashMap.put("1","one");
        hashMap.put("2","two");
        hashMap.put("3","three");
        hashMap.put("4","four");
        hashMap.put("5","five");
        hashMap.put("6","six");
        hashMap.put("7","seven");
        hashMap.put("8","eight");
        hashMap.put("9","nine");
        hashMap.put("10","ten");
        hashMap.put("11","eleven");
        hashMap.put("12","twelve");
        hashMap.put("13","thirteen");
        hashMap.put("14","fourteen");
        hashMap.put("15","fifteen");
        hashMap.put("16","sixteen");
        hashMap.put("17","seventeen");
        hashMap.put("18","eighteen");
        hashMap.put("19","nineteen");
        hashMap.put("20","twenty");
        hashMap.put("30","thirty");
        hashMap.put("40","forty");
        hashMap.put("50","fifty");
        hashMap.put("60","sixty");
        hashMap.put("70","seventy");
        hashMap.put("80","eighty");
        hashMap.put("90","ninety");
        hashMap.put("00","hundred");
        hashMap.put("000","thousand");
        hashMap.put("000000","million");
        hashMap.put("000000000","billion");
        hashMap.put("000000000000","trillion");
    }

    public static void returnReversArray(char[] arr) {
        char temp;
        for (int i = arr.length - 1, j = 0; i >= arr.length / 2; i--, j++) {
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }

    public static void returnReversArrayString(String[] arr) {
        String temp;
        for (int i = arr.length - 1, j = 0; i >= arr.length / 2; i--, j++) {
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }

    String a = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
}