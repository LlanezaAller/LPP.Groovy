public class BinaryTrees {

private final static int minDepth = 4;

public static void main(String[] args){
        final def millis = System.currentTimeMillis();

        def n = 20;
        if (args.length > 0) n = Integer.parseInt(args[0]);

        def maxDepth = (minDepth + 2 > n) ? minDepth + 2 : n;
        def stretchDepth = maxDepth + 1;

        def check = (TreeNode.bottomUpTree(0,stretchDepth)).itemCheck();
        //System.out.println("stretch tree of depth "+stretchDepth+"\t check: " + check);

        TreeNode longLivedTree = TreeNode.bottomUpTree(0,maxDepth);

        for (def depth=minDepth; depth<=maxDepth; depth+=2){
        def iterations = 1 << (maxDepth - depth + minDepth);
        check = 0;

        for (def i=1; i<=iterations; i++){
        check += (TreeNode.bottomUpTree(i,depth)).itemCheck();
        check += (TreeNode.bottomUpTree(-i,depth)).itemCheck();
        }
        //System.out.println((iterations*2) + "\t trees of depth " + depth + "\t check: " + check);
        }
        //System.out.println("long lived tree of depth " + maxDepth + "\t check: "+ longLivedTree.itemCheck());

        def total = System.currentTimeMillis() - millis;
        //System.out.println("[Binary Trees-" + System.getProperty("project.name")+ " Benchmark Result: " + total + "]");
}


private static class TreeNode
{
        private TreeNode left, right;
        private def item;

        TreeNode(int item){
        this.item = item;
}

private static TreeNode bottomUpTree(int item, int depth){
        if (depth>0){
                return new TreeNode(
                bottomUpTree(2*item-1, depth-1)
                , bottomUpTree(2*item, depth-1)
                , item
                );
        }
        else {
                return new TreeNode(item);
        }
}

TreeNode(TreeNode left, TreeNode right, int item){
this.left = left;
this.right = right;
this.item = item;
}

private int itemCheck(){
// if necessary deallocate here
if (left==null)
                return item;
else {
                return item + left.itemCheck() - right.itemCheck();
            }
}
}
}

