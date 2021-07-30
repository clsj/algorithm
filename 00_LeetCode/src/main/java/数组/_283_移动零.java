package 数组;

/**
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class _283_移动零 {
    public static void main(String[] args) {
        _283_移动零 v = new _283_移动零();
        int[] nums = new int[] {1,0,1,0,3,0,12};
        v.moveZeroes(nums);

        for (int num :
                nums) {
            System.out.println(num);
        }

    }

    public void moveZeroes(int[] nums) {
        int firstZero = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (firstZero >= 0) {
                    int temp = nums[i];
                    nums[i] = nums[firstZero];
                    nums[firstZero++] = temp;

                    // 重新定义firstZero的位置
                    while (firstZero < i) {
                        if (nums[firstZero] != 0){
                            firstZero++;
                        }else {
                            break;
                        }

                    }
                }
            }else {
                if (firstZero < 0) {
                    firstZero = i;
                }
            }
        }
    }
}
