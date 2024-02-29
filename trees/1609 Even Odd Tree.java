/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isEvenOddTree(TreeNode root) {
        
        //base case / null root is vacuously even
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        //flag for oddLevel
        boolean oddLevel = true; 

        //push root to q
        queue.offer(root);

        while (!queue.isEmpty()) {
            int sizeOfQueue = queue.size(); 
            int lastVal = oddLevel ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < sizeOfQueue; i++) {
                
                //get node from top of the queue
                TreeNode node = queue.poll();

                if (oddLevel) {

                    //check for violation of odd level properties
                    if (node.val % 2 == 0 || node.val <= lastVal) {
                        return false;
                    }

                } else { 
                    
                    //check for violation of even level properties
                    if (node.val % 2 != 0 || node.val >= lastVal) {
                        return false;
                    }
                }

                //push left and right node to queue
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
                
                lastVal = node.val; 
            }
            //switching level properties
            oddLevel = !oddLevel;            

        }

        return true;
    }
}
