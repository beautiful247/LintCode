package service;

import entity.ListNode;

import java.util.List;
//链表操作题目集合
public class LinkedList {
    //插入排序
    public ListNode insertionSortList(ListNode head){
        ListNode temp = head;
        while(temp!=null){
            ListNode test = temp.next;
            while(test!=null){
                if(test.val<temp.val){
                    int t = test.val;
                    test.val = temp.val;
                    temp.val = t;
                }
                test = test.next;
            }
            temp = temp.next;
        }
        return head;
    }
    //删除倒数第n个节点
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode temp = head;
        ListNode res = head;
        for(int i=1;i<n;i++){
            temp=temp.next;
        }
        while(temp.next!=null){
            temp=temp.next;
            res=res.next;
        }
        if(res==head){
            return res.next;
        }else{
            ListNode t = head;
            while(t.next!=res){
                t=t.next;
            }
            t.next=res.next;
            return head;
        }
    }
}
