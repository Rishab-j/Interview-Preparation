int swimInWater(vector<vector<int>>& grid) {
        
        if (grid.empty() || grid[0].empty())
        return 0;

        typedef pair<int,int> index;

        priority_queue<index,vector<index>,greater<index>> que;

        vector<vector<int>> dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        que.push({grid[0][0],0});
        grid[0][0]=-1;

        int ans = 0;

        int n = grid.size();
        int m = grid[0].size();


        while(que.size()!=0){
            
            index rvtx = que.top();
            que.pop();

            int x = rvtx.second / m;
            int y = rvtx.second % m;
            
            ans = max(ans,rvtx.first);
            
             if (x == n - 1 && y == m - 1) // destination
            return ans;

            for (int d = 0; d < 4; d++)
            {
                int r = x + dir[d][0];
                int c = y + dir[d][1];
                if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] != -1)
                {
                    que.push({grid[r][c], r * m + c});
                    grid[r][c] = -1;
                }
            }
        }

        return ans;      
    }