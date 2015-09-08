import java.io.*;
public class LineDeleter {
  public static void main(String args[]) {
    try {
      File fi = new File(args[0]);
      File fo = new File("tmp.sql");
      BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(fi),"UTF-8"));
      BufferedWriter bo = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(fo),"UTF-8"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();
      while (line != null) {
        if(!line.toUpperCase().contains(args[1].toUpperCase())){          
          sb.append(line + "\n");   
        }          
        line = br.readLine();
      }
      bo.write(sb.toString());
      br.close();      
      bo.close();      
      fi.delete();
      fo.renameTo(fi); 
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e);      
    }
  }
}