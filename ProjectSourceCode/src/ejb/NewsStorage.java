package ejb;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Scanner;

@Singleton
public class NewsStorage {
    private String newsDataFileName = "newsFile.txt";
    private LinkedList<String> newsList = new LinkedList<>();

    public NewsStorage(){

        System.out.println("\n \n -------------- Start loading users info ----------------- \n ");

        if(!(new File(newsDataFileName).exists())){
            try {
                File file1 = new File(newsDataFileName);
                System.out.println(" file " + newsDataFileName + " was not found.");
                System.out.println(" we try to create new file . Result " + file1.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (Scanner scanner = new Scanner(new File(newsDataFileName))) {
            //Gson gson = new Gson();
            while (scanner.hasNext()){
                newsList.addLast(scanner.nextLine());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(" ################################ error in getUsersInfo ");
        }
    }

    public void saweNews(){
        System.out.println(" \n ---------- Start Saving Data sz = " + newsList.size() + " ----------- \n ");
        try (PrintWriter printWriter = new PrintWriter(new File(newsDataFileName))) {
            //Gson gson = new Gson();
            for (String news : newsList) {
                printWriter.println(news);
                printWriter.flush();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public List<String> getNews(){
        return new ArrayList<>(newsList);
    }

    public void setNews(String news){
        newsList.addFirst(news);
        saweNews();
    }
}
