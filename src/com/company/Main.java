package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int mx_sz = 1000;

    public static String lower_consonants = "qwrtpsdfghjklzxcvbnm";
    //public static String upper_consonants = "QWRTPSDFGHJKLZXCVBNM";
    public static String lower_consonants_cyrillic = "йцкнгшщзхфвпрлджчсмтб";
    //public static String upper_consonants_cyrillic = "ЙЦКНГШЩЗХФВПРЛДЖЧСМТБ";
    public static String regex = "[^a-zA-Z0-9а-яіА-ЯІ]";
    public static String consonants = "ayuioe";

    public static List<String> GetAns(List<String> text)
    {
        int mx = 0;
        List<String> ans = new ArrayList<>();
        for(String word: text)
        {
            int counter = 0;
            int mx_in_word = 0;

            for (int i = 0; i < word.length(); i++)
            {
                if(lower_consonants.indexOf(word.toLowerCase().charAt(i)) != -1 ||
                        lower_consonants_cyrillic.indexOf(word.toLowerCase().charAt(i)) != -1)
                {
                    counter += 1;
                    if(counter > mx_in_word) mx_in_word = counter;
                }
                else
                {
                    counter = 0;
                }
            }

            if(mx_in_word > mx)
            {
                mx = mx_in_word;
                ans.clear();
                ans.add(word);
            }
            else if(mx_in_word == mx)
            {
                ans.add(word);
            }
        }

        return ans;
    }

    public static Boolean HaveConsonants(String str){
        for(int i = 0; i < str.length(); i++)
        {
            if (consonants.indexOf(str.charAt(i)) != -1) return true;
        }
        return false;
    }

    public static int CalcSymbols(String str)
    {
        return str.length();
    }

    public static String GetStringToUpperCase(String str){
        return str.toUpperCase();
    }

    public static List<String> RemoveRepeats(StringBuilder[] words)
    {
//        StringBuilder[] temp = new StringBuilder[words.length];
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < words.length; i++)
        {
            if(!temp.contains(words[i].toString())) temp.add(words[i].toString());
        }
        List<String> ans = GetAns(temp);
        return ans;
    }

    public static StringBuilder[] getWordsFromFile(){
        try(FileReader reader = new FileReader("text.txt")) {
            int c;
            String text = "";
            while ((c = reader.read()) != -1) {
                text += (char) c;
            }


            StringBuilder[] words = new StringBuilder[text.split(regex).length];
            for (int i = 0; i < words.length; i++) {
                words[i] = new StringBuilder("");
            }

            int i = 0;
            for (String str : text.split(regex)) {
                if (str.equals("")) continue;
                words[i++].append(str);

            }
            return words;
        }
        catch(IOException ex)
        {
            System.out.print(ex.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {

        try(FileWriter writer = new FileWriter("write.txt", false))
        {
            String t = "asdlkfj";
            writer.write(t);

            writer.flush();
        }
        catch(IOException ex)
        {
            System.out.print(ex.getMessage());
        }


        try(FileReader reader = new FileReader("text.txt"))
        {
            int c;
            String text = "";
            while((c = reader.read()) != -1)
            {
                text += (char)c;
            }


            StringBuilder[] words = new StringBuilder[text.split(regex).length];
            for (int i = 0; i < words.length; i++)
            {
                words[i] = new StringBuilder("");
            }

            int i = 0;
            for(String str: text.split(regex))
            {
                if(str.equals("")) continue;
                if(str.length() >= 30)
                {
                    char[] temp = new char[30];
                    str.getChars(0, 29, temp, 0);
                    words[i++].append(temp);
                }
                else
                {
                    words[i++].append(str);
                }


            }

            List<String> ans = RemoveRepeats(words);

            System.out.print(ans);


        }
        catch(IOException ex)
        {
            System.out.print(ex.getMessage());
        }
    }
}
