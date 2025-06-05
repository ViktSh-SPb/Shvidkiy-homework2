package org.viktsh;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * @author Viktor Shvidkiy
 */
public class CustomLinkedList<T> {
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            add(data);
        } else {
            Node<T> current = getNode(index);
            Node<T> newNode = new Node<>(data);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;

            size++;
        }
    }

    public boolean addAll(int index, Collection<? extends T> addPart) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (addPart.isEmpty()) {
            return false;
        }

        Node<T> firstNewNode = null;
        Node<T> lastNewNode = null;

        for (T item : addPart) {
            Node<T> newNode = new Node<>(item);
            if (firstNewNode == null) {
                firstNewNode = newNode;
                lastNewNode = newNode;
            } else {
                lastNewNode.next = newNode;
                newNode.prev = lastNewNode;
                lastNewNode = newNode;
            }
        }

        if (index == 0) {
            if (head != null) {
                lastNewNode.next = head;
                head.prev = lastNewNode;
            }
            head = firstNewNode;
            if (tail == null) {
                tail = lastNewNode;
            }
        } else if (index == size) {
            tail.next = firstNewNode;
            firstNewNode.prev = tail;
            tail = lastNewNode;
        } else {
            Node<T> current = getNode(index);
            firstNewNode.prev=current.prev;
            current.prev.next=firstNewNode;
            lastNewNode.next=current;
            current.prev=lastNewNode;
        }
        size+=addPart.size();
        return true;
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return getNode(index).data;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return data;
    }

    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = tail.data;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return data;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<T> current = getNode(index);
            current.next.prev = current.prev;
            current.prev.next = current.next;
            size--;
            return current.data;
        }
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }


}
