package com.blackbear.leetcode;

import java.util.Stack;

public class P62 {

    public static void main(String[] args) {
        Solution62_DP solution62 = new Solution62_DP();
        int m = 23;
        int n = 12;
        int count = solution62.uniquePaths(m, n);
        System.out.printf("m = %d, n = %d, count = %d\n", m, n, count);
    }
}

class Solution62_DP {

    public int uniquePaths(int m, int n) {
        int[][] result = new int[m][n];

        //init
        for (int i = 0; i < m; i++) {
            result[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            result[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i][j - 1] + result[i - 1][j];
            }
        }

        return result[m - 1][n - 1];
    }

}

class Solution62 {
    public int uniquePaths(int m, int n) {
        int count2End = 0;

        Robort robort = new Robort();
        int rightEnd = n - 1;
        int downEnd = m - 1;

        if (robort.isArrive(rightEnd, downEnd)) {
            //如果一开始就到了，直接结束
            count2End++;
            return count2End;
        }

        Stack<ForkSite> forkSiteStack = new Stack<>();
        boolean goDownFlag = false;

        while (true) {

            if (goDownFlag) {
                robort.goDown();
                goDownFlag = false;
            } else {
                //优先往右走
                boolean canGoRight = robort.canGoRight(rightEnd);
                boolean canGoDown = robort.canGoDown(downEnd);

                if (canGoRight && canGoDown) {
                    //都能走
                    ForkSite robortSiteFirst = new ForkSite(robort);
                    forkSiteStack.push(robortSiteFirst);
                    robort.goRight();
                } else if (canGoRight) {
                    //只能往右走
                    robort.goRight();
                } else if (canGoDown) {
                    //只能往下走
                    robort.goDown();
                } else {
                    //不能走了
                    if (robort.isArrive(rightEnd, downEnd)) {
                        count2End++;
                    }

                    //如果到了终点，且stack里没有元素了，则结束
                    if (forkSiteStack.isEmpty()) {
                        break;
                    }
                    ForkSite preForkSite = forkSiteStack.pop();
                    goDownFlag = true;
                    robort.x = preForkSite.x;
                    robort.y = preForkSite.y;
                }
            }
        }
        return count2End;
    }
}

class Robort {
    //左右
    int x = 0;

    //上下
    int y = 0;

    void goRight() {
        this.x++;
    }

    boolean canGoRight(int rightEnd) {
        return this.x < rightEnd;
    }

    void goDown() {
        this.y++;
    }

    boolean canGoDown(int downEnd) {
        return this.y < downEnd;
    }

    boolean isArrive(int rightEnd, int downEnd) {
        if (this.x == rightEnd && this.y == downEnd) {
            return true;
        }
        return false;
    }
}

//记录深度遍历时的岔路口
class ForkSite {
    int x;
    int y;

    public ForkSite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ForkSite(Robort robort) {
        this.x = robort.x;
        this.y = robort.y;
    }
}