package service;

import entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//二叉树操作题目集合
public class Tree {
    //二叉树深度
    public int maxDepth(TreeNode root){
        if(root==null) {
            return 0;
        }else{
            int ld = maxDepth(root.left);
            int rd = maxDepth(root.right);
            return 1+Math.max(ld,rd);
        }
    }
    //二叉排序树插入
    public TreeNode insertNode(TreeNode root, TreeNode node){
        if(root==null){
            return node;
        }
        if(node.val<root.val){
            if(root.left==null){
                root.left=node;
                return root;
            }else{
                insertNode(root.left,node);
            }
        }else{
            if(root.right==null){
                root.right=node;
                return root;
            }else{
                insertNode(root.right,node);
            }
        }
        return root;
    }
    //先序遍历
    public void inOrder(TreeNode root){
        System.out.println(root.val);
        //使用递归进行遍历左孩子
        if (root.left != null) {
            inOrder(root.left);
        }
        //递归遍历右孩子
        if (root.right != null) {
            inOrder(root.right);
        }
    }
    //层次遍历
    public List<Integer> levelOrder(TreeNode root){
        List<Integer> res = new ArrayList<Integer>();
        Queue<TreeNode> qt = new LinkedList<TreeNode>();
        TreeNode temp = root;
        qt.offer(temp);
        while(!qt.isEmpty()){
            temp = qt.remove();
            if(temp!=null){
                if(temp.right!=null){
                    qt.offer(temp.right);
                }
                if(temp.left!=null){
                    qt.offer(temp.left);
                }
                res.add(temp.val);
            }
        }
        return res;
    }

    //按照层次遍历建立二叉树
    public TreeNode create(String[] str,int index){
        if(index>=str.length){
            return null;
        }
        TreeNode root;
        if(str[index].equals("#")){
            return null;
        }else{
            root = new TreeNode(Integer.parseInt(str[index]));
        }
        TreeNode lchild = create(str,2*index);
        TreeNode rchild = create(str,2*index+1);
        root.left=lchild;
        root.right=rchild;
        return root;
    }

    //序列化
    public String serialize(TreeNode root){
        if (root == null) {
            return "{}";
        }

        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(root);

        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (node == null) {
                continue;
            }
            queue.add(node.left);
            queue.add(node.right);
        }

        while (queue.get(queue.size() - 1) == null) {
            queue.remove(queue.size() - 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(queue.get(0).val);
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i) == null || queue.get(i).val == -1) {
                sb.append(",#");
            } else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    //反序列化
    public TreeNode deserialize(String data) {
        if(data.length()==2){
            return null;
        }
        String pure = data.substring(1,data.length()-1);
        String a = "0,";
        pure = a.concat(pure);
        String[] str = pure.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(str[1]));
        root.left = create(str,2);
        root.right = create(str,3);
        return root;
    }
}
