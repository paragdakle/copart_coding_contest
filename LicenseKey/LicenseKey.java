public class LicenseKey{
  public static void main(String args[]){
    if(args.length < 2){
      System.out.println("Requires S and K"); //Does not proceed without arguments S and K
    }
    else{
      String S = args[0];
      int K = Integer.parseInt(args[1]);
      S = S.replaceAll("-","").toUpperCase();  //Remove hyphens and make Upper Case
      StringBuilder s = new StringBuilder();
      if(S.length() > 0){ //If no letters nothing to print
        int len = 0; 
         //Building the key in reverse (so first group may be smaller than K)
        s.append(S.charAt(S.length()-1));
        len++;
        for(int i=S.length()-2;i >= 0;i--){
          if(len % K == 0)
            s.append('-');  //Add hyphen after groups ok K
          s.append(S.charAt(i));
          len++;
        }
        //Print Reverse
        System.out.println(s.reverse());  
      }
    }
  }
}
