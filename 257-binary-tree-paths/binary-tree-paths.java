class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> currPath = new ArrayList<>();
        dfs(root, currPath, result);
        return result;
    }
    
    void dfs(TreeNode node, List<Integer> currPath, List<String> result) {
        if(node==null){
            return ;
        }
        currPath.add(node.val);
        if(node.left==null && node.right==null){
            result.add(buildPathString(currPath));
        }
        else{
            dfs(node.left,currPath,result);
            dfs(node.right,currPath,result);
        }
        currPath.remove(currPath.size()-1);
    }
    String buildPathString(List<Integer>path){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<path.size();i++){
            sb.append(path.get(i));
            if(i!=path.size() -1){
                sb.append("->");
            }
        }
        return sb.toString();
    }
}