/* *****************************************************************************
 * Name: Matthew Green
 * Date: 12Oct2022
 * Purpose:  To solve the Tower of Hanoi using iteration and track the duration
 * of the full process.
 **************************************************************************** */

package Lab2;

import java.io.PrintWriter;

public class TowerIteration extends TowerOfHanoi {
    private final Tower aTower = new Tower('A');
    private final Tower bTower = new Tower('B');
    private final Tower cTower = new Tower('C');

    TowerIteration(PrintWriter writer) {
        super(writer);
    }

    // The iterative approach requires a data structure; I use my own version of
    // an ArrayList to keep track of each of the three towers.  The transform process
    // is as follows: move disk 1 to a particular tower defined by
    // how many disks need to be moved from the current tower it is in to a
    // new tower.  Move disk 2 to the tower that disk 1 did not move to, then
    // move disk 1 on top of disk 2, and finally move the only eligible disk
    // (whatever is smallest of the other two towers, or whichever tower has
    // a disk if the other tower is empty) from its tower to the residual tower.
    // Repeat until all the disks are moved to the destination tower.
    public void transform(int size) {
        long startTime = System.nanoTime();

        for (int i = size; i > 0; i--) {
            aTower.tower.add(i);
        }

        Tower curTower = aTower;
        Tower destTower = bTower;
        Tower midTower = cTower;
        Tower tower1target;
        Tower tower2Target;

        int numToMove = 0;

        while (bTower.tower.size() < size) {
            if (curTower.tower.size() == size) {
                if (size == 1) {
                    moveDisk(curTower, destTower);
                    break;
                }
                numToMove = size;
            }

            if (numToMove % 2 == 0) {
                tower1target = midTower;
                tower2Target = destTower;
            } else {
                tower1target = destTower;
                tower2Target = midTower;
            }

            moveDisk(curTower, tower1target);
            moveDisk(curTower, tower2Target);
            moveDisk(tower1target, tower2Target);

            if (!curTower.tower.isEmpty() || !tower1target.tower.isEmpty()) {
                int diskToMove;
                if (curTower.tower.isEmpty()) {
                    diskToMove = moveDisk(tower1target, curTower);
                    destTower = curTower;
                    midTower = tower1target;
                } else if (tower1target.tower.isEmpty()) {
                    diskToMove = moveDisk(curTower, tower1target);
                    destTower = tower1target;
                    midTower = curTower;
                } else {
                    int diskA = tower1target.tower.get(tower1target.tower.size() - 1);
                    int diskB = curTower.tower.get(curTower.tower.size() - 1);
                    if (diskA < diskB) {
                        diskToMove = moveDisk(tower1target, curTower);
                        destTower = curTower;
                        midTower = tower1target;
                    } else {
                        diskToMove = moveDisk(curTower, tower1target);
                        destTower = tower1target;
                        midTower = curTower;
                    }
                }
                curTower = tower2Target;
                numToMove = 0;
                for (int i = curTower.tower.size() - 1; i >= 0; i--) {
                    if (curTower.tower.get(i) < diskToMove) {
                        numToMove++;
                    } else {
                        break;
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        totalTime = endTime - startTime;
    }

    // Moves a disk from one tower to another
    private int moveDisk(Tower start, Tower end) {
        int disk = start.tower.removeLast();
        end.tower.add(disk);
        printSteps(disk, start.name, end.name);
        return disk;
    }

    // I use a nested class, Tower, that stores each tower's name
    // and its corresponding MGArrayList, which is my own version of
    // an array list
    private static class Tower {
        private final char name;
        private final MGArrayList<Integer> tower = new MGArrayList<>();

        Tower(char name) {
            this.name = name;
        }
    }

    // For debugging, to check the success of the algorithm when there are too
    // many steps to trace
    void printFinalTower() {
        for (int i = 0; i < bTower.tower.size(); i++) {
            System.out.println(bTower.tower.get(i));
        }
    }
}
