package 数组;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class _11_盛最多水的容器 {

    public static void main(String[] args) {

    }

    public int maxArea(int[] height) {

        int i = 0;
        int j = height.length -1;
        int max = 0;

        while (i != j) {
            // 先求出面积
            int area = (j - i) * (height[i] > height[j] ? height[j--] : height[i++]);
            max = Math.max(max, area);
        }

        return max;
    }
}
