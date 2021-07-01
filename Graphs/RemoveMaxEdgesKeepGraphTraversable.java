public class RemoveMaxEdgesKeepGraphTraversable {
    class UnionFind{
        int[] par;
        int component;
        // int[] rank;
        
        UnionFind(int n){
            par = new int[n];
            for(int i = 0; i < n; i++){
                par[i] = i;
            }
            component = n;
            // rank = new int[n];
            // Arrays.fill(rank,1);
        }
        
        int find(int x){
            if(x == par[x]) return x;
            par[x] = find(par[x]);
            return par[x];
        }
        
        boolean union(int x,int y){
            int par1 = find(x);
            int par2 = find(y);
            
            boolean same = false;
            
            if(par1 != par2){
                par[par2] = par1;
                component--;
            }else{
                same = true;
            }
            
            return same;
        }
        
//         boolean singleComponent(int n){
//             // int i = 0;
//             int parent = find(0);
//             // System.out.println(parent);
//             for(int i = 1; i < n; i++){
//                 // System.out.println(find(i));
//                 if(parent != find(i)) return false;
//             }
            
//             return true;
//         }
        
    }
    
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        
        if(n == 0 || edges.length == 0) return -1;
        
        // Arrays.sort(edges,(a,b) -> {
        //    return b[0] - a[0]; 
        // });
        
        UnionFind alice = new UnionFind(n);
        UnionFind bob = new UnionFind(n);
        
        int count = 0;
        
        for(int[] edge: edges){
            int type = edge[0];
            int u = edge[1];
            int v = edge[2];
            
            if(type == 3){
                if(!alice.union(u-1,v-1) && !bob.union(u-1,v-1)){
                    count++;   
                }
            }
        }
        
        for(int[] edge: edges){
            int type = edge[0];
            int u = edge[1];
            int v = edge[2];
            
            if(type == 2){ // bob
                if(!bob.union(u-1,v-1)){
                    count++;
                }
            }else if(type == 1){ // alice
                if(!alice.union(u-1,v-1)){
                    count++;
                }
            }
        }
         
        
        if(alice.component != 1 || bob.component != 1){
            return -1;
        }
        
        return edges.length - count;
        
    }
}
