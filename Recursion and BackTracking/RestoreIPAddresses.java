import java.util.*;

public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        doRestore(result, "", s, 0, 0);
        return result;
    }
    
    private void doRestore(List<String> result, String path, String str, int k, int i) {
        if(i == str.length() || k == 4){
            if(i == str.length() && k == 4){
                result.add(path.substring(0, path.length() - 1));
                
                return;
            }
            return;
        }
        
        doRestore(result, path + str.charAt(i) + ".",str, k + 1, i + 1);
        
        if(i + 2 <= str.length() && isValid(str.substring(i, i + 2))){
            doRestore(result, path + str.substring(i, i + 2) + ".",str, k + 1, i + 2);    
        }
        
        if(i + 3 <= str.length() && isValid(str.substring(i, i + 3))){
            doRestore(result, path + str.substring(i, i + 3) + ".",str, k + 1, i + 3);    
        }
    }
    
    private boolean isValid(String str){
        if(str.charAt(0) == '0'){
            return false;
        }
        
        if(Integer.parseInt(str) > 255){
            return false;
        }
        
        return true;
    }
}
