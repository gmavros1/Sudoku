import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class nickname {
    public String name;
    List classicList;
    List killerList;

    nickname(String n) {
        classicList = new ArrayList();
        killerList = new ArrayList();
        Scanner keyboard = new Scanner(System.in);
        name = n;
    }
    nickname(){
       name=null;
    }

    public boolean hasPlayed() throws IOException {
        BufferedReader puf = new BufferedReader(new FileReader("nicknames"));
        String sto;
        while ((sto = puf.readLine()) != null) {
            if (sto.equals(name)) return true;
            for (int i = 0; i < 3; i++) {
                puf.readLine();
            }
        }
        puf.close();
        return false;

    }

    public boolean playedClassic(int i) throws IOException {
        if (hasPlayed()) {
            BufferedReader puf = new BufferedReader(new FileReader("nicknames"));
            String sto;
            while ((sto = puf.readLine()) != null) {
                if (sto.equals(name)) {
                    sto = puf.readLine();
                    String[] u = puf.readLine().trim().split(" ");
                    for (int j = 0; j < u.length; j++) {
                        if ((Integer.parseInt(u[j])) == i) {
                            puf.close();
                            return false;
                        }
                    }
                    sto = puf.readLine();
                }
                for (int j = 0; j < 3; j++) puf.readLine();
            }
            puf.close();
        }
        return true;
    }

     public boolean playedKiller(int i) throws IOException {
         if (hasPlayed()) {
             BufferedReader puf = new BufferedReader(new FileReader("nicknames"));
             String sto;
             while ((sto = puf.readLine()) != null) {
                 if (sto.equals(name)) {
                     sto = puf.readLine();
                     sto = puf.readLine();
                     String[] u = puf.readLine().trim().split(" ");
                     for (int j = 0; j < u.length; j++) {
                         if ((Integer.parseInt(u[j])) == i) {
                             puf.close();
                             return false;
                         }
                     }
                 }
                 for (int j = 0; j < 3; j++) puf.readLine();
             }
             puf.close();
         }
         return true;
     }
 public void newDataClassic(int i) throws IOException {
        if(hasPlayed()){
            try{
                BufferedReader puf=new BufferedReader(new FileReader("nicknames"));
               StringBuffer inpuf =new StringBuffer();
               String lpuf;
               while((lpuf=puf.readLine())!=null){
                   if(lpuf.equals(name)) {
                       lpuf=puf.readLine();
                       lpuf=puf.readLine();
                       lpuf = String.format("%s%d", lpuf, i);
                       inpuf.append(lpuf);
                       inpuf.append('\n');
                   }
               }
                puf.close();
               FileOutputStream pufout=new FileOutputStream("nicknames");
                pufout.write(inpuf.toString().getBytes());
                pufout.close();
            }catch(Exception e){
                System.out.println("PROBLEM HERE");
            }


        }
        else {
            try {
                FileWriter ftw = new FileWriter("classic", true);
                ftw.write(name+"\n");
                ftw.write(" \n");
                ftw.write(i);
                ftw.write(" \n");
            }
            catch (IOException ioe){
                System.err.println("IOException"+ioe.getMessage());

            }

        }
 }
    public void newDataKiller(int i) throws IOException {

        if(hasPlayed()){
            try{
            BufferedReader puf=new BufferedReader(new FileReader("nicknames"));
            StringBuffer inpuf =new StringBuffer();
            String lpuf;
            while((lpuf=puf.readLine())!=null){
                if(lpuf.equals(name)) {
                    lpuf=puf.readLine();
                    lpuf=puf.readLine();
                    lpuf=puf.readLine();
                    lpuf = lpuf+" "+String.valueOf(i);
                    inpuf.append(lpuf);
                    inpuf.append('\n');
                }
            }
            puf.close();
            FileOutputStream pufout=new FileOutputStream("nicknames");
            pufout.write(inpuf.toString().getBytes());
            pufout.close();
        }catch(Exception e){
            System.out.println("PROBLEM HERE");
        }
        }
        else {
            try {
                FileWriter ftw = new FileWriter("classic", true);
                ftw.write(name+"\n");
                ftw.write('\n');
                ftw.write(" \n");
                ftw.write(i+"\n");
            }
            catch (IOException ioe){
                System.err.println("IOException"+ioe.getMessage());

            }

        }
    }
    public void newDataDui(int win,int lose) throws IOException {

        if(hasPlayed()){
            try{
        int[] score=new int[2];
            BufferedReader puf=new BufferedReader(new FileReader("nicknames"));
            StringBuffer inpuf =new StringBuffer();
            String lpuf;
            while((lpuf=puf.readLine())!=null){
                if(lpuf.equals(name)) {
                    String[] u = puf.readLine().trim().split(" ");
                    score[0]=Integer.parseInt(u[0]) +win;
                    score[1]=Integer.parseInt(u[1])+ lose;
                    lpuf= score[0]+" "+score[1];
                    inpuf.append(lpuf);
                    inpuf.append('\n');
                }
            }
            puf.close();
            FileOutputStream pufout=new FileOutputStream("nicknames");
            pufout.write(inpuf.toString().getBytes());
            pufout.close();
        }catch(Exception e){
            System.out.println("PROBLEM HERE");
        }
    }

        else {
            try {
                FileWriter ftw = new FileWriter("nicknames", true);
                ftw.write(name);
                ftw.write(win);
                ftw.write(lose+"\n");
                ftw.write(" \n");
                ftw.write(" \n");
            }
            catch (IOException ioe){
                System.err.println("IOException"+ioe.getMessage());

            }

        }
    }



}
