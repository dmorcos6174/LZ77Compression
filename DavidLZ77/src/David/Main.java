package David;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    static void compress(String x, int search_buffer){
        int currentPos = 0, index, pos, len;
        char nextChar = ' ';
        while(true){
            String substring1 = x.substring(Math.max(0, currentPos-search_buffer), currentPos);
            for (int i = search_buffer; i >= 0; --i){
                String substring2 = x.substring(currentPos,Math.min(currentPos+i, x.length()));
                index = substring1.lastIndexOf(substring2);
                if(index != -1){
                    pos = substring1.length() - index;
                    len = substring2.length();
                    System.out.println(pos + " " + len + " " + nextChar);
                    currentPos += i;
                    break;
                }
                nextChar = substring2.charAt(substring2.length() - 1);
            }

            currentPos++;
            if(currentPos >= x.length())
                break;
        }
    }

    static void decompress(ArrayList<List<String>> listOfTags){
        String og = "";
        int currentPos = 0, index,position, length;
        String symbol = "";
        String toBeAdded = "";
        for (int i = 0; i<listOfTags.size(); i++){
            position = Integer.parseInt(listOfTags.get(i).get(0));
            length = Integer.parseInt(listOfTags.get(i).get(1));
            symbol = listOfTags.get(i).get(2);
            if(position == 0 && length == 0){
                og += symbol;
            }
            else{
                String sub = og.substring(Math.max(0, currentPos-position),((currentPos-position)+length));

                og += sub;
                og += symbol;
            }
            currentPos++;
            currentPos += length;
        }
        System.out.println(og);
    }

    public static void main(String[] args) {
        //COMPRESS CODE
//        try{
//            File file = new File("C:\\Users\\David\\Desktop\\DavidLZ77\\src\\David\\input.txt");
//            Scanner compressInput = new Scanner(file);
//            String inputContent = compressInput.nextLine();
//            compress(inputContent,12);
//        }
//        catch (IOException err){
//            System.out.println(err);
//        }

        //DECOMPRESS CODE
        try{
            BufferedReader bufReader = new BufferedReader(new FileReader("C:\\Users\\David\\Desktop\\DavidLZ77\\src\\David\\output.txt"));
            ArrayList<List<String>> listOfTags = new ArrayList<>();

            String line = bufReader.readLine();
//            String[] splitLine = line.split(" ");
//            List<String> splitLineList = Arrays.asList(splitLine);
//            listOfTags.add(splitLineList);
            while (line != null){
                String[] splitLine = line.split(" ");
                List<String> splitLineList = Arrays.asList(splitLine);
                listOfTags.add(splitLineList);
                //listOfTags.add(line);
                line = bufReader.readLine();
            }
            bufReader.close();
            System.out.println(listOfTags);
            decompress(listOfTags);
        }
        catch (IOException err){
            System.out.println(err);
        }

        //String s="ABAABABAABBBBBBBBBBBBA";
        //compress(currentLine,12);
    }
}
