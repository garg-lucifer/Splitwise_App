package com.namangarg.splitwiseapp.Utils;

public class Minimze_flow {

    public int[][] minCashFlow(int graph[][])
    {
        int N = graph.length;
        int[][] ans = new int [N][N];
        int[] amount = new int[N];

        for (int p = 0; p < N; p++)
            for (int i = 0; i < N; i++)
                amount[p] += (graph[i][p] - graph[p][i]);

        minCashFlowRec(amount, ans);
        return ans;
    }

    private void minCashFlowRec(int amount[], int [][] ans)
    {

        int mxCredit = getMax(amount), mxDebit = getMin(amount);

        if (amount[mxCredit] == 0 && amount[mxDebit] == 0)
            return;

        int min = minOf2(-amount[mxDebit], amount[mxCredit]);
        amount[mxCredit] -= min;
        amount[mxDebit] += min;

        ans[mxDebit][mxCredit] = min;
        minCashFlowRec(amount, ans);
    }

    private int getMin(int arr[])
    {
        int minInd = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[minInd])
                minInd = i;
        return minInd;
    }


    private int getMax(int arr[])
    {
        int maxInd = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > arr[maxInd])
                maxInd = i;
        return maxInd;
    }

    private int minOf2(int x, int y)
    {
        return Math.min(x, y);
    }

}
