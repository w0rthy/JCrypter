package jcrypter;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class JCrypter {

    public static void main(String[] args) throws Throwable{
        int encrypt = JOptionPane.showConfirmDialog(null, "Encrypt / Decrypt", "Select", JOptionPane.YES_NO_OPTION);
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(jfc);
        File f = jfc.getSelectedFile();
        File out;
        if(encrypt == 0)
            out = new File(f.getPath()+"ENCRYPTED");
        else
            if(f.getPath().contains("ENCRYPTED"))
                out = new File(f.getPath().substring(0, f.getPath().length()-9));
            else
                out = new File(f.getPath()+"DECRYPTED");
        
        Scanner s = new Scanner(f);
        PrintWriter pw = new PrintWriter(out);
        String data = "";
        while(s.hasNextLine()){
            data = s.nextLine();
            if(encrypt == 0){
            int[] a = encrypt(data);
            data = "";
            for(int i = 0; i <a.length; i++)
                data+=(char)a[i];
            }
            else
                data = decrypt(data);
            
            pw.println(data);
        }
        s.close();
        pw.close();
        s.close();
        pw.close();
    }
    
    public static int[] encrypt(String d){
        int[] dat = toIntArray(d);
        for(int i = 0; i < dat.length; i++){
            //ENCRYPTION ALGORITHM
            dat[i] = (int)Math.pow(dat[i]+1,2)*3+17;
        }
        return dat;
    }
    
    public static String decrypt(int[] a){
        String s = "";
        for(int i = 0; i < a.length; i++){
            s+=(char)(int)(Math.sqrt(((a[i]-17)/3))-1);
        }
        return s;
    }
    
    public static String decrypt(String a){
        return decrypt(toIntArray(a));
    }
    
    public static int[] toIntArray(String d){
        char[] ca = d.toCharArray();
        int[] t = new int[ca.length];
        for(int i = 0; i < t.length; i++)
            t[i] = (int)ca[i];
        return t;
    }
    
}
