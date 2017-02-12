package spagettiCode;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class EasyMain {
    private static final String numberRegExp = "^\\-?[0-9]{1,}$";
    private static Object locker = new Object();

    public static void main(String[] args) {
        String[] resourceAddresses = {"", ""};


        AtomicBoolean isBreak = new AtomicBoolean(false);
        ConcurrentLinkedQueue<Integer> numbersQueue = new ConcurrentLinkedQueue<>();
        for (String resourceName:
             resourceAddresses) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try(Reader reader = getReaderByResourceName(resourceName)){
                        int charCode;
                        Character currentChar;
                        String wordBuffer = "";
                        while((charCode=reader.read())!=-1){
                            currentChar = ((char)charCode);
                            if(currentChar == '−'){
                                currentChar = '-';
                            }
                            if (currentChar != '-' || !Character.isDigit(currentChar)){
                                killAll(isBreak);
                                return;
                            }

                            if(currentChar == ' '){
                                addToQueue(numbersQueue, wordBuffer, isBreak);
                                wordBuffer = "";
                            }

                            if(isBreak.get()){
                                return;
                            }

                            wordBuffer += currentChar;
                        }
                        addToQueue(numbersQueue, wordBuffer, isBreak);

                    } catch (IOException e) {
                        killAll(isBreak);
                    }
                }
            });
        }

        while(true){
            //TODO считать очередь и ждать другие потоки
        }


    }
    private static void addToQueue(ConcurrentLinkedQueue<Integer> queue, String word, AtomicBoolean isBreak){
        String trimmedWord = word.trim();
        if(trimmedWord.length() == 0){
            return;
        }

        Integer value = new Integer(0);
        if(trimmedWord.matches(numberRegExp)
                && integerTryParse(trimmedWord, value)
                && isNumberValid(value)){
            synchronized (locker){
                if(!isBreak.get()) {
                    queue.add(value);
                }
            }
        }
        else{
            killAll(isBreak);
        }
    }

    private static boolean isNumberValid(int value){
        if(value > 0 && (value % 2 == 0)){
            return true;
        }
        return false;
    }



    private static void killAll(AtomicBoolean isBreak){
        synchronized (locker){
            isBreak.set(true);
        }
    }

    private static boolean integerTryParse(String word, Integer value){
        try{
            value = new Integer(Integer.parseInt(word));
            return true;
        }
        catch(NumberFormatException ex){
            return false;
        }
    }

    private static Reader getReaderByResourceName(String resourceName) throws FileNotFoundException {
        try {
            return getUrlReader(resourceName);
        } catch (IOException e) {
            return getFileReader(resourceName);
        }
    }

    private static InputStreamReader getUrlReader(String resourceName) throws IOException {
        URL url = new URL(resourceName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream inputStream = conn.getInputStream();

        return new InputStreamReader(inputStream);
    }

    private static Reader getFileReader(String resourceName) throws FileNotFoundException {
        return new FileReader(resourceName);
    }



}
