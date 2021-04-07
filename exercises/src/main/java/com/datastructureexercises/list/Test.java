package com.datastructureexercises.list;

public class Test {
    public static void main(String[] args) {

        ListNode nodeSta = new ListNode(0);    //创建首节点
        ListNode nextNode;                     //声明一个变量用来在移动过程中指向当前节点
        nextNode = nodeSta;                      //指向首节点

        //创建链表
        for (int i = 1; i < 10; i++) {
            ListNode node = new ListNode(i);  //生成新的节点
            nextNode.next = node;               //把心节点连起来
            nextNode = nextNode.next;           //当前节点往后移动
        } //当for循环完成之后 nextNode指向最后一个节点，

        nextNode = nodeSta;                     //重新赋值让它指向首节点
        print(nextNode);                      //打印输出

        //插入节点
        while (nextNode != null) {
            if (nextNode.val == 5) {
                ListNode newnode = new ListNode(99);  //生成新的节点
                ListNode node = nextNode.next;          //先保存下一个节点
                nextNode.next = newnode;                //插入新节点
                newnode.next = node;                    //新节点的下一个节点指向 之前保存的节点
            }
            nextNode = nextNode.next;
        }//循环完成之后 nextNode指向最后一个节点
        nextNode = nodeSta;                            //重新赋值让它指向首节点
        print(nextNode);                             //打印输出

        //替换节点
        while (nextNode != null) {
            if (nextNode.val == 4) {
                ListNode newnode = new ListNode(99);  //生成新的节点
                ListNode node = nextNode.next.next;     //先保存要替换节点的下一个节点
                nextNode.next.next = null;              //被替换节点 指向为空 ，等待java垃圾回收
                nextNode.next = newnode;                //插入新节点
                newnode.next = node;                    //新节点的下一个节点指向 之前保存的节点
            }
            nextNode = nextNode.next;
        }//循环完成之后 nextNode指向最后一个节点
        nextNode = nodeSta;                            //重新赋值让它指向首节点
        print(nextNode);                             //打印输出

        //删除节点
        while (nextNode != null) {
            if (nextNode.val == 5) {
                ListNode listNode = nextNode.next.next;     //保存要删除节点的下一个节点
                nextNode.next.next = null;                  //被删除节点 指向为空 ，等待java垃圾回收
                nextNode.next = listNode;                   //指向要删除节点的下一个节点
            }
            nextNode = nextNode.next;
        }//循环完成之后 nextNode指向最后一个节点
        nextNode = nodeSta;                            //重新赋值让它指向首节点
        print(nextNode);                             //打印输出


    }


    //打印输出方法
    static void print(ListNode listNoed) {
        //创建链表节点
        while (listNoed != null) {
            System.out.println("节点:" + listNoed.val);
            listNoed = listNoed.next;
        }
        System.out.println();
    }

}

class ListNode {        //类名 ：Java类就是一种自定义的数据结构
    int val;            //数据 ：节点数据
    ListNode next;      //对象 ：引用下一个节点对象。在Java中没有指针的概念，Java中的引用和C语言的指针类似

    ListNode(int val) {  //构造方法 ：构造方法和类名相同
        this.val = val;   //把接收的参数赋值给当前类的val变量
    }
}