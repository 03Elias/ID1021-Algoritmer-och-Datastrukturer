class LinkedLinst {
    Cell first;

    public class Cell {
        int head; // value
        Cell tail; // next

        Cell(int val, Cell tl) {
            head = val;
            tail = tl;
        }
    }

    void add(int item) {
        Cell newCell = new Cell(item, first);
        first = newCell;
    }

    int length() {
        int count = 0;
        Cell current = first;
        while (current != null) {
            count++;
            current = current.tail;
        }
        return count;
    }

    boolean find(int item) {
        Cell current = first;
        while (current != null) {
            if (current.head == item) {
                return true;
            }
            current = current.tail;
        }
        return false;
    }

    void remove(int item) {
        if (first != null && first.head == item) {
            first = first.tail;
            return;
        }
        Cell current = first;
        Cell prev = null;
        while (current != null) {
            if (current.head == item) {
                prev.tail = current.tail;
                return;
            }
            prev = current;
            current = current.tail;
        }
    }

    public void append(LinkedLinst otherList) {
        if (otherList.first != null) {
            Cell current = this.first;
            if (current == null) {
                this.first = otherList.first;
            } else {
                while (current.tail != null) {
                    current = current.tail;
                }
                current.tail = otherList.first;
            }
            otherList.first = null; // Clear the other list
        }
    }

    void insertFirst(int item) {
        Cell newCell = new Cell(item, null);
        if (first == null) {
            first = newCell;
        } else {
            newCell.tail = first;
            first = newCell;
        }
    }

    void unlink(Cell cellToRemove) {
        if (first == null) {
            // The list is empty, nothing to unlink
            return;
        }

        if (first == cellToRemove) {
            // If the cell to remove is the first cell, update the first reference
            first = first.tail;
            return;
        }

        Cell current = first;

        while (current != null && current.tail != cellToRemove) {
            current = current.tail;
        }

        if (current != null) {
            // Found the previous cell, update its tail to skip the cell to remove
            current.tail = cellToRemove.tail;
        }
    }
}
