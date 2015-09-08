import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class SyncSubtitle {
  public static void main(String args[]) {
    int modifier = Integer.parseInt(args[1]);
    try{
      File fi = new File(args[0]);
      File fo = new File("tmp.sql");
      BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(fi),"UTF-8"));
      BufferedWriter bo = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(fo),"UTF-8"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();
      while (line != null) {
        if(line.toUpperCase().contains("-->")){          
          String hh_mm_ss_xxx[] = line.split("-->");
          String hh_mm_ss1[] = hh_mm_ss_xxx[0].split(",");
          String hh_mm_ss2[] = hh_mm_ss_xxx[1].split(",");
          String tt1[] = hh_mm_ss1[0].split(":");          
          String tt2[] = hh_mm_ss2[0].split(":");
          
          int ihh1 = Integer.parseInt(tt1[0].trim());
          int imm1 = Integer.parseInt(tt1[1].trim());
          int iss1 = Integer.parseInt(tt1[2].trim());
          
          int ihh2 = Integer.parseInt(tt2[0].trim());
          int imm2 = Integer.parseInt(tt2[1].trim());
          int iss2 = Integer.parseInt(tt2[2].trim());
          
          Date date1 = new Date(0,0,0,ihh1,imm1,iss1);   
          Date date2 = new Date(0,0,0,ihh2,imm2,iss2);         
                    
          hh_mm_ss1[0] = addTime(date1, modifier);
          hh_mm_ss2[0] = addTime(date2, modifier);
          
          hh_mm_ss_xxx[0] = strJoin(hh_mm_ss1, ",").trim();
          hh_mm_ss_xxx[1] = strJoin(hh_mm_ss2, ",").trim();
          
          line = strJoin(hh_mm_ss_xxx, " --> ");        
        } 
          sb.append(line + "\n");               
        line = br.readLine();
      }      
      br.close();      
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
  public static String addTime(Date d1, int m){
    SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss");
    long time1 = d1.getTime() + m * 1000;    
    return printFormat.format(new Date(time1));
  }
  public static String strJoin(String[] aArr, String sSep) {
    StringBuilder sbStr = new StringBuilder();
    for (int i = 0, il = aArr.length; i < il; i++) {
        if (i > 0)
            sbStr.append(sSep);
        sbStr.append(aArr[i]);
    }
    return sbStr.toString();
  }
}