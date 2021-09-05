public class AllThriceExceptOne {
    public static void solution(int[] arr){
        //write your code here
        
        int tn = Integer.MAX_VALUE;
        int tnp1 = 0;
        int tnp2 = 0;
        
        for(int a: arr){
            
            int temp_tn = tn, temp_tnp1 = tnp1, temp_tnp2 = tnp2;
            
            temp_tn = temp_tn & a;
            temp_tnp1 = temp_tnp1 & a;
            temp_tnp2 = temp_tnp2 & a;
            
            
            tn = tn ^ temp_tn;
            tnp1 = tnp1 | temp_tn;
            
            tnp1 = tnp1 ^ temp_tnp1;
            tnp2 = tnp2 | temp_tnp1;
            
            tnp2 = tnp2 ^ temp_tnp2;
            tn = tn | temp_tnp2;
        }
       
        System.out.println(tnp1);
      }
}
