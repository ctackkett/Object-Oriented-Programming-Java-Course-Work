import java.util.ArrayList;
public class VarArgsEx {
    public static void main(String[] args) {
        System.out.println(makeIntList(5, 2, 43, 2, 1));
    }
    public static ArrayList<Integer> makeIntList(int ... nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int num : nums) {
            list.add(num);
        }
        return list;
    }

    public static boolean canBalance(ArrayList<Integer> numbers) {
        // check every possible position the array can be split
        for (int i=0; i<numbers.size(); i++)
            // if the sum of the left half is equal to the sum of the right, we're good
            if(sumArray(getFirstHalf(numbers, i)) == sumArray(getSecondHalf(numbers, i)))
                return true;

        // we were never able to find a position i where the left and right slices were equal
        return false;
    }

    /** slices the array in two at position n and returns the first slice
     * the value at position n is not included in the first slice
     * @param nums array to be sliced
     * @param n position in which to slice the array
     * @return the left half of the sliced array
     */
    public static ArrayList<Integer> getFirstHalf(ArrayList<Integer> nums, int n) {
        ArrayList<Integer> subArrayList  = new ArrayList<>(n);

        for (int i = 0; i < n; i++){
            subArrayList.add(nums.get(i));

        }

        return subArrayList;
    }

    /** slices the array in two at position n and returns the second slice
     * the value at position n is included in the first slice
     * @param nums array to be sliced
     * @param n position in which to slice the array
     * @return the right half of the sliced array
     */
    public static ArrayList<Integer> getSecondHalf(ArrayList<Integer> nums, int n) {
        ArrayList<Integer> subArrayList = new ArrayList<>();

        for (int i = n; i < nums.size(); i++){
            subArrayList.add(nums.get(i));
        }

        return subArrayList;
    }

    /** sums the values in an array of integers.
     *
     * @param nums array of values to be summed
     * @return sum of all of the values in nums
     */
    public static int sumArray(ArrayList<Integer> nums) {
        int sum = 0;

        for (int i = 0; i < nums.size(); i ++){
            sum += nums.get(i);
        }

        return sum;
    }
}
