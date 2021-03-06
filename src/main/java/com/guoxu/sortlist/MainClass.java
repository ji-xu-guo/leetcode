package com.guoxu.sortlist;

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {val = x;}
}
class Solution {
    public ListNode sortList(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode headtemp = new ListNode(Integer.MIN_VALUE);
//        headtemp.next = head;
//        head = headtemp;
//        ListNode p = head.next;
//        ListNode q = null;
//        ListNode r = null;
//        if (p != null) {
//            r = p.next;
//            p.next = null;
//            p = r;
//            while (p != null) {
//                r = p.next;
//                q = head;
//                while (q.next != null && q.next.val < p.val) {
//                    q = q.next;
//                }
//                p.next = q.next;
//                q.next = p;
//                p = r;
//            }
//        }
//        return head.next;

        if (head == null && head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode q = null,r = null,pr = null;
        if (p != null) {
            r = p.next;
            p.next = null;
            p = r;
            while (p != null) {
                r = p.next;
                q = head;
                pr = head;
                while (q != null && q.val < p.val) {
                    pr = q;
                    q = q.next;
                }
                if (pr == head) {
                    p.next = head;
                    head = p;
                } else {
                    p.next = pr.next;
                    pr.next = p;
                }
                p = r;
            }
        }
        return head;
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);

            ListNode ret = new Solution().sortList(head);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
}
