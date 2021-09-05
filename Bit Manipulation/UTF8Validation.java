public class UTF8Validation {
    public boolean validUtf8(int[] data) {
        int rbytes = 0;
        
        for(int d: data){
            if(rbytes == 0){
                if((d >> 7) == 0b0){
                    rbytes = 0;
                }else if((d >> 5) == 0b110){
                    rbytes = 1;
                }else if((d >> 4) == 0b1110){
                    rbytes = 2;
                }else if((d >> 3) == 0b11110){
                    rbytes = 3;
                }else if((d >> 7) != 0){
                    return false;
                } 
            }else{
                if((d >> 6) == 0b10){
                    rbytes--;
                }else{
                    return false;
                }
            }
        }
        
        
        
        if(rbytes == 0) return true;
        else return false;
    }
}
