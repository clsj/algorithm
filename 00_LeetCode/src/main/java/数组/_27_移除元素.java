package 数组;

/**
 * https://leetcode-cn.com/problems/remove-element/submissions/
 */
public class _27_移除元素 {


    public static void main(String[] args) {
        _27_移除元素 v = new _27_移除元素();
        int[] nums = new int[] {0,1,2,2,3,0,4,2};
        System.out.println(v.removeElement(nums, 2));
        for (int num :
                nums) {
            System.out.print(num + " -> ");
        }

    }

    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int j = nums.length - 1;

        for (int i = 0; ; i++) {
            while (j >= i) {
                if (nums[j] != val) {
                    break;
                }else {
                    j--;
                }
            }

            if (i > j) {
                break;
            }

            if (nums[i] == val) {
                nums[i] = nums[j];
                nums[j] = val;
            }
        }

        return j + 1;
    }

}
