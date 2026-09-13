import java.util.HashMap;

public class Main {

    // ==============================
    // NODE
    // ==============================
    static class Node {
        int data;
        Node next;
        Node random;
        Node bottom;

        Node(int data) {
            this.data = data;
        }
    }

    // ==============================
    // SINGLY LINKED LIST
    // ==============================
    static class LinkedList {

        Node head;

        // 1. Insert at Beginning
        void insertAtBeginning(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }

        // 2. Insert at End
        void insertAtEnd(int data) {
            Node newNode = new Node(data);

            if (head == null) {
                head = newNode;
                return;
            }

            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }

        // 3. Insert at Given Position
        // Position starts from 1
        void insertAtPosition(int data, int position) {

            if (position < 1) {
                System.out.println("Invalid position");
                return;
            }

            if (position == 1) {
                insertAtBeginning(data);
                return;
            }

            Node newNode = new Node(data);
            Node temp = head;

            for (int i = 1; i < position - 1 && temp != null; i++) {
                temp = temp.next;
            }

            if (temp == null) {
                System.out.println("Position does not exist");
                return;
            }

            newNode.next = temp.next;
            temp.next = newNode;
        }

        // 4. Delete First Node
        void deleteFirst() {

            if (head == null) {
                return;
            }

            head = head.next;
        }

        // 5. Delete Last Node
        void deleteLast() {

            if (head == null) {
                return;
            }

            if (head.next == null) {
                head = null;
                return;
            }

            Node temp = head;

            while (temp.next.next != null) {
                temp = temp.next;
            }

            temp.next = null;
        }

        // 6. Search
        boolean search(int key) {

            Node temp = head;

            while (temp != null) {

                if (temp.data == key) {
                    return true;
                }

                temp = temp.next;
            }

            return false;
        }

        // 7. Sorted Insert
        void sortedInsert(int data) {

            Node newNode = new Node(data);

            if (head == null || data <= head.data) {
                newNode.next = head;
                head = newNode;
                return;
            }

            Node temp = head;

            while (temp.next != null &&
                    temp.next.data < data) {

                temp = temp.next;
            }

            newNode.next = temp.next;
            temp.next = newNode;
        }

        // 8. Find Middle
        Node findMiddle() {

            if (head == null) {
                return null;
            }

            Node slow = head;
            Node fast = head;

            while (fast != null &&
                    fast.next != null) {

                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

        // 9. Nth Node From End
        Node nthFromEnd(int n) {

            if (n <= 0) {
                return null;
            }

            Node first = head;
            Node second = head;

            for (int i = 0; i < n; i++) {

                if (first == null) {
                    return null;
                }

                first = first.next;
            }

            while (first != null) {
                first = first.next;
                second = second.next;
            }

            return second;
        }

        // 10. Reverse Linked List
        void reverse() {

            Node prev = null;
            Node current = head;

            while (current != null) {

                Node next = current.next;

                current.next = prev;

                prev = current;
                current = next;
            }

            head = prev;
        }

        // 11. Remove Duplicate From Sorted List
        void removeDuplicates() {

            Node temp = head;

            while (temp != null &&
                    temp.next != null) {

                if (temp.data == temp.next.data) {
                    temp.next = temp.next.next;
                } else {
                    temp = temp.next;
                }
            }
        }

        // 12. Reverse Linked List In Groups
        Node reverseGroups(Node node, int k) {

            if (node == null) {
                return null;
            }

            Node prev = null;
            Node current = node;
            Node next = null;

            int count = 0;

            while (current != null &&
                    count < k) {

                next = current.next;

                current.next = prev;

                prev = current;
                current = next;

                count++;
            }

            if (next != null) {
                node.next = reverseGroups(next, k);
            }

            return prev;
        }

        void reverseInGroups(int k) {
            head = reverseGroups(head, k);
        }

        // 13. Detect Loop
        boolean detectLoop() {

            Node slow = head;
            Node fast = head;

            while (fast != null &&
                    fast.next != null) {

                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    return true;
                }
            }

            return false;
        }

        // 14. Detect and Remove Loop
        void detectAndRemoveLoop() {

            Node slow = head;
            Node fast = head;

            boolean loop = false;

            while (fast != null &&
                    fast.next != null) {

                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    loop = true;
                    break;
                }
            }

            if (!loop) {
                return;
            }

            slow = head;

            if (slow == fast) {

                while (fast.next != slow) {
                    fast = fast.next;
                }

                fast.next = null;
                return;
            }

            while (slow.next != fast.next) {

                slow = slow.next;
                fast = fast.next;
            }

            fast.next = null;
        }

        // 15. Delete Node When Only Pointer Is Given
        void deleteNode(Node node) {

            if (node == null ||
                    node.next == null) {
                return;
            }

            node.data = node.next.data;
            node.next = node.next.next;
        }

        // 16. Segregate Even and Odd Nodes
        void segregateEvenOdd() {

            Node evenStart = null;
            Node evenEnd = null;

            Node oddStart = null;
            Node oddEnd = null;

            Node current = head;

            while (current != null) {

                Node next = current.next;
                current.next = null;

                if (current.data % 2 == 0) {

                    if (evenStart == null) {
                        evenStart = current;
                        evenEnd = current;
                    } else {
                        evenEnd.next = current;
                        evenEnd = current;
                    }

                } else {

                    if (oddStart == null) {
                        oddStart = current;
                        oddEnd = current;
                    } else {
                        oddEnd.next = current;
                        oddEnd = current;
                    }
                }

                current = next;
            }

            if (evenStart == null) {
                head = oddStart;
            } else {
                head = evenStart;
                evenEnd.next = oddStart;
            }
        }

        // 17. Intersection Point of Two Linked Lists
        static Node intersectionPoint(
                Node head1,
                Node head2) {

            Node a = head1;
            Node b = head2;

            while (a != b) {

                if (a == null) {
                    a = head2;
                } else {
                    a = a.next;
                }

                if (b == null) {
                    b = head1;
                } else {
                    b = b.next;
                }
            }

            return a;
        }

        // 18. Pairwise Swap Nodes
        void pairwiseSwap() {

            Node current = head;

            while (current != null &&
                    current.next != null) {

                int temp = current.data;

                current.data = current.next.data;
                current.next.data = temp;

                current = current.next.next;
            }
        }

        // 19. Add 1 to Linked List Number
        void addOne() {

            reverse();

            Node current = head;
            Node previous = null;

            int carry = 1;

            while (current != null &&
                    carry != 0) {

                int sum = current.data + carry;

                current.data = sum % 10;
                carry = sum / 10;

                previous = current;
                current = current.next;
            }

            if (carry != 0) {
                previous.next = new Node(carry);
            }

            reverse();
        }

        // 20. Sort Linked List of 0s, 1s and 2s
        void sort012() {

            int zero = 0;
            int one = 0;
            int two = 0;

            Node temp = head;

            while (temp != null) {

                if (temp.data == 0) {
                    zero++;
                } else if (temp.data == 1) {
                    one++;
                } else if (temp.data == 2) {
                    two++;
                }

                temp = temp.next;
            }

            temp = head;

            while (zero-- > 0) {
                temp.data = 0;
                temp = temp.next;
            }

            while (one-- > 0) {
                temp.data = 1;
                temp = temp.next;
            }

            while (two-- > 0) {
                temp.data = 2;
                temp = temp.next;
            }
        }

        // 21. Clone Linked List With Random Pointer
        static Node cloneRandomList(Node head) {

            if (head == null) {
                return null;
            }

            HashMap<Node, Node> map = new HashMap<>();

            Node current = head;

            // Create copy nodes
            while (current != null) {

                map.put(
                        current,
                        new Node(current.data)
                );

                current = current.next;
            }

            // Connect next and random
            current = head;

            while (current != null) {

                Node copy = map.get(current);

                copy.next = map.get(current.next);
                copy.random = map.get(current.random);

                current = current.next;
            }

            return map.get(head);
        }

        // 22. Merge Two Sorted Linked Lists
        static Node mergeSorted(
                Node head1,
                Node head2) {

            Node dummy = new Node(0);
            Node tail = dummy;

            while (head1 != null &&
                    head2 != null) {

                if (head1.data <= head2.data) {

                    tail.next = head1;
                    head1 = head1.next;

                } else {

                    tail.next = head2;
                    head2 = head2.next;
                }

                tail = tail.next;
            }

            if (head1 != null) {
                tail.next = head1;
            } else {
                tail.next = head2;
            }

            return dummy.next;
        }

        // 23. Palindrome Linked List
        boolean isPalindrome() {

            if (head == null ||
                    head.next == null) {
                return true;
            }

            Node slow = head;
            Node fast = head;

            while (fast != null &&
                    fast.next != null) {

                slow = slow.next;
                fast = fast.next.next;
            }

            Node secondHalf = reverseNode(slow);
            Node firstHalf = head;

            Node temp = secondHalf;

            while (temp != null) {

                if (firstHalf.data != temp.data) {
                    return false;
                }

                firstHalf = firstHalf.next;
                temp = temp.next;
            }

            return true;
        }

        // Helper Reverse
        Node reverseNode(Node node) {

            Node prev = null;
            Node current = node;

            while (current != null) {

                Node next = current.next;

                current.next = prev;

                prev = current;
                current = next;
            }

            return prev;
        }

        // 24. Flatten Linked List
        static Node flatten(Node head) {

            if (head == null ||
                    head.next == null) {
                return head;
            }

            head.next = flatten(head.next);

            return mergeBottom(head, head.next);
        }

        // Merge bottom lists
        static Node mergeBottom(
                Node a,
                Node b) {

            Node dummy = new Node(0);
            Node temp = dummy;

            while (a != null &&
                    b != null) {

                if (a.data <= b.data) {

                    temp.bottom = a;
                    a = a.bottom;

                } else {

                    temp.bottom = b;
                    b = b.bottom;
                }

                temp = temp.bottom;
            }

            if (a != null) {
                temp.bottom = a;
            } else {
                temp.bottom = b;
            }

            return dummy.bottom;
        }

        // Print normal linked list
        void printList() {

            Node temp = head;

            while (temp != null) {

                System.out.print(temp.data);

                if (temp.next != null) {
                    System.out.print(" -> ");
                }

                temp = temp.next;
            }

            System.out.println();
        }

        // Get node by position
        Node getNode(int position) {

            Node temp = head;

            for (int i = 1;
                 i < position && temp != null;
                 i++) {

                temp = temp.next;
            }

            return temp;
        }

        // Print flattened list
        static void printBottomList(Node head) {

            Node temp = head;

            while (temp != null) {

                System.out.print(temp.data);

                if (temp.bottom != null) {
                    System.out.print(" -> ");
                }

                temp = temp.bottom;
            }

            System.out.println();
        }
    }

    // ==========================================
    // MAIN METHOD
    // ==========================================
    public static void main(String[] args) {

        // ==========================================
        // 1. INSERT AT BEGINNING
        // ==========================================

        LinkedList list = new LinkedList();

        list.insertAtBeginning(30);
        list.insertAtBeginning(20);
        list.insertAtBeginning(10);

        System.out.println("1. Insert at Beginning:");
        list.printList();


        // ==========================================
        // 2. INSERT AT END
        // ==========================================

        list.insertAtEnd(40);
        list.insertAtEnd(50);

        System.out.println("\n2. Insert at End:");
        list.printList();


        // ==========================================
        // 3. INSERT AT GIVEN POSITION
        // ==========================================

        list.insertAtPosition(25, 3);

        System.out.println("\n3. Insert 25 at Position 3:");
        list.printList();


        // ==========================================
        // 4. DELETE FIRST
        // ==========================================

        list.deleteFirst();

        System.out.println("\n4. Delete First:");
        list.printList();


        // ==========================================
        // 5. DELETE LAST
        // ==========================================

        list.deleteLast();

        System.out.println("\n5. Delete Last:");
        list.printList();


        // ==========================================
        // 6. SEARCH
        // ==========================================

        System.out.println("\n6. Search 25:");

        if (list.search(25)) {
            System.out.println("25 Found");
        } else {
            System.out.println("25 Not Found");
        }


        // ==========================================
        // 7. SORTED INSERT
        // ==========================================

        LinkedList sorted = new LinkedList();

        sorted.insertAtEnd(10);
        sorted.insertAtEnd(20);
        sorted.insertAtEnd(30);

        sorted.sortedInsert(25);

        System.out.println("\n7. Sorted Insert:");
        sorted.printList();


        // ==========================================
        // 8. MIDDLE
        // ==========================================

        Node middle = list.findMiddle();

        System.out.println("\n8. Middle Node:");

        if (middle != null) {
            System.out.println(middle.data);
        }


        // ==========================================
        // 9. NTH NODE FROM END
        // ==========================================

        Node nth = list.nthFromEnd(2);

        System.out.println("\n9. 2nd Node From End:");

        if (nth != null) {
            System.out.println(nth.data);
        }


        // ==========================================
        // 10. REVERSE
        // ==========================================

        list.reverse();

        System.out.println("\n10. Reverse:");
        list.printList();


        // ==========================================
        // 11. REMOVE DUPLICATES
        // ==========================================

        LinkedList duplicate = new LinkedList();

        duplicate.insertAtEnd(1);
        duplicate.insertAtEnd(1);
        duplicate.insertAtEnd(2);
        duplicate.insertAtEnd(2);
        duplicate.insertAtEnd(3);

        duplicate.removeDuplicates();

        System.out.println("\n11. Remove Duplicates:");
        duplicate.printList();


        // ==========================================
        // 12. REVERSE IN GROUPS
        // ==========================================

        LinkedList groups = new LinkedList();

        for (int i = 1; i <= 8; i++) {
            groups.insertAtEnd(i);
        }

        groups.reverseInGroups(3);

        System.out.println("\n12. Reverse in Groups of 3:");
        groups.printList();


        // ==========================================
        // 13. DETECT LOOP
        // ==========================================

        LinkedList loopList = new LinkedList();

        for (int i = 1; i <= 5; i++) {
            loopList.insertAtEnd(i);
        }

        Node last = loopList.getNode(5);
        Node second = loopList.getNode(2);

        last.next = second;

        System.out.println("\n13. Detect Loop:");

        if (loopList.detectLoop()) {
            System.out.println("Loop Found");
        } else {
            System.out.println("No Loop");
        }


        // ==========================================
        // 14. DETECT AND REMOVE LOOP
        // ==========================================

        loopList.detectAndRemoveLoop();

        System.out.println("\n14. After Removing Loop:");

        if (loopList.detectLoop()) {
            System.out.println("Loop Still Exists");
        } else {
            System.out.println("Loop Removed");
        }

        loopList.printList();


        // ==========================================
        // 15. DELETE NODE WITH ONLY POINTER
        // ==========================================

        LinkedList deleteList = new LinkedList();

        for (int i = 1; i <= 5; i++) {
            deleteList.insertAtEnd(i);
        }

        Node nodeToDelete = deleteList.getNode(3);

        deleteList.deleteNode(nodeToDelete);

        System.out.println("\n15. Delete Node 3:");
        deleteList.printList();


        // ==========================================
        // 16. SEGREGATE EVEN AND ODD
        // ==========================================

        LinkedList evenOdd = new LinkedList();

        evenOdd.insertAtEnd(1);
        evenOdd.insertAtEnd(2);
        evenOdd.insertAtEnd(3);
        evenOdd.insertAtEnd(4);
        evenOdd.insertAtEnd(5);
        evenOdd.insertAtEnd(6);

        evenOdd.segregateEvenOdd();

        System.out.println("\n16. Even and Odd:");
        evenOdd.printList();


        // ==========================================
        // 17. INTERSECTION POINT
        // ==========================================

        LinkedList first = new LinkedList();
        LinkedList secondList = new LinkedList();

        first.insertAtEnd(1);
        first.insertAtEnd(2);

        Node common = new Node(10);
        common.next = new Node(20);
        common.next.next = new Node(30);

        first.getNode(2).next = common;

        secondList.insertAtEnd(5);
        secondList.head.next = common;

        Node intersection =
                LinkedList.intersectionPoint(
                        first.head,
                        secondList.head
                );

        System.out.println("\n17. Intersection Point:");

        if (intersection != null) {
            System.out.println(intersection.data);
        } else {
            System.out.println("No Intersection");
        }


        // ==========================================
        // 18. PAIRWISE SWAP
        // ==========================================

        LinkedList pair = new LinkedList();

        for (int i = 1; i <= 6; i++) {
            pair.insertAtEnd(i);
        }

        pair.pairwiseSwap();

        System.out.println("\n18. Pairwise Swap:");
        pair.printList();


        // ==========================================
        // 19. ADD 1
        // ==========================================

        LinkedList number = new LinkedList();

        number.insertAtEnd(1);
        number.insertAtEnd(2);
        number.insertAtEnd(9);

        number.addOne();

        System.out.println("\n19. Add 1:");
        number.printList();


        // ==========================================
        // 20. SORT 0, 1, 2
        // ==========================================

        LinkedList zeroOneTwo = new LinkedList();

        zeroOneTwo.insertAtEnd(2);
        zeroOneTwo.insertAtEnd(1);
        zeroOneTwo.insertAtEnd(0);
        zeroOneTwo.insertAtEnd(2);
        zeroOneTwo.insertAtEnd(1);
        zeroOneTwo.insertAtEnd(0);

        zeroOneTwo.sort012();

        System.out.println("\n20. Sort 0, 1, 2:");
        zeroOneTwo.printList();


        // ==========================================
        // 21. CLONE RANDOM POINTER LIST
        // ==========================================

        Node r1 = new Node(10);
        Node r2 = new Node(20);
        Node r3 = new Node(30);

        r1.next = r2;
        r2.next = r3;

        r1.random = r3;
        r2.random = r1;
        r3.random = r2;

        Node cloned =
                LinkedList.cloneRandomList(r1);

        System.out.println("\n21. Clone Random List:");

        Node temp = cloned;

        while (temp != null) {

            System.out.print(temp.data);

            if (temp.next != null) {
                System.out.print(" -> ");
            }

            temp = temp.next;
        }

        System.out.println();


        // ==========================================
        // 22. MERGE TWO SORTED LISTS
        // ==========================================

        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();

        l1.insertAtEnd(1);
        l1.insertAtEnd(3);
        l1.insertAtEnd(5);

        l2.insertAtEnd(2);
        l2.insertAtEnd(4);
        l2.insertAtEnd(6);

        Node merged =
                LinkedList.mergeSorted(
                        l1.head,
                        l2.head
                );

        System.out.println("\n22. Merge Sorted Lists:");

        temp = merged;

        while (temp != null) {

            System.out.print(temp.data + " ");

            temp = temp.next;
        }

        System.out.println();


        // ==========================================
        // 23. PALINDROME
        // ==========================================

        LinkedList palindrome = new LinkedList();

        palindrome.insertAtEnd(1);
        palindrome.insertAtEnd(2);
        palindrome.insertAtEnd(3);
        palindrome.insertAtEnd(2);
        palindrome.insertAtEnd(1);

        System.out.println("\n23. Palindrome:");

        if (palindrome.isPalindrome()) {
            System.out.println("Yes, Palindrome");
        } else {
            System.out.println("No, Not Palindrome");
        }


        // ==========================================
        // 24. FLATTEN LINKED LIST
        // ==========================================

        Node f1 = new Node(5);
        Node f2 = new Node(10);
        Node f3 = new Node(19);

        f1.next = f2;
        f2.next = f3;

        f1.bottom = new Node(7);
        f1.bottom.bottom = new Node(8);

        f2.bottom = new Node(20);

        f3.bottom = new Node(22);
        f3.bottom.bottom = new Node(50);

        Node flattened =
                LinkedList.flatten(f1);

        System.out.println("\n24. Flatten Linked List:");

        LinkedList.printBottomList(flattened);


        // ==========================================
        // DONE
        // ==========================================

        System.out.println("\n==============================");
        System.out.println("All Linked List Problems Done!");
        System.out.println("==============================");
    }
}
