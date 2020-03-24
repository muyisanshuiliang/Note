package com.example.demo.sgg.algorithm;

import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * @Author yl
 * @Date 2020/1/10 11:23
 * @description: 冒泡排序法, 时间复杂度 O(n²)
 */
public class SortUtil {
    /**
     * 数组指定长度
     */
    private static final int ARRAY_LENGTH = 1000000;

    public static void main(String[] args) {

//        int[] array = {9, -1, 7, 3, 8, 16, -4, 2};
//        int[] array = {2312, -2310, -8, -22,-7,23};
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("排序前：" + Arrays.toString(array));
//        selectSortAsc(array);
//        bubbleSortAsc(array);
//        insertSortAsc(array);
//        shellSortAscByExchange(array);
//        shellSortAscByMove(array);
//        quickSort(array, 0, array.length - 1);
        quickSort_2(array, 0, array.length - 1);
//        int[] temp = new int[array.length];
//        mergeSort(array, 0, array.length - 1, temp);
//        radixSort(array);
//        radixSortSimple(array);
        System.out.println("排序后：" + Arrays.toString(array));

        array = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            array[i] = (int) (Math.random() * 10000000);
        }
        /*测试冒泡排序法*/
        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        bubbleSortAsc(array);
//        stopWatch.stop();
//        System.out.println("冒泡排序法花费的时间：" + stopWatch.getLastTaskTimeMillis());

        /*测试选择排序法*/
//        stopWatch.start();
//        selectSortAsc(array);
//        stopWatch.stop();
//        System.out.println("选择排序法花费的时间：" + stopWatch.getLastTaskTimeMillis());

        /*测试插入排序法*/
//        stopWatch.start();
//        insertSortAsc(array);
//        stopWatch.stop();
//        System.out.println("选择排序法花费的时间：" + stopWatch.getLastTaskTimeMillis());

        /*测试希尔排序法（交换式）*/
//        stopWatch.start();
//        shellSortAscByExchange(array);
//        stopWatch.stop();
//        System.out.println("选择排序法花费的时间：" + stopWatch.getLastTaskTimeMillis());

        /*测试希尔排序法（移动式）*/
//        stopWatch.start();
//        shellSortAscByMove(array);
//        stopWatch.stop();
//        System.out.println("选择排序法花费的时间：" + stopWatch.getLastTaskTimeMillis());

//        stopWatch.start();
//        quickSort(array,0,array.length-1);
//        stopWatch.stop();
//        System.out.println("选择排序法花费的时间：" + stopWatch.getLastTaskTimeMillis());
    }

    /**
     * 冒泡排序法（升序）：时间复杂度 O(n²)
     *
     * @param array
     */
    private static void bubbleSortAsc(int[] array) {
        int temp;
        /*用于标志当前循环体内是否进行了数据交换*/
        boolean flag = false;
        /*共循环N-1次，N为数组的长度，前面N-1次完成后，最后一个元素肯定已经属于最值，毋需再进行额外的循环*/
        for (int i = 0, length = array.length; i < length - 1; i++) {
            /*依次比较未完成排序的元素*/
            for (int j = 0; j < length - 1 - i; j++) {
                /*升序：如果当前位置元素大于后一位置元素，交换两个位置元素，实现大值上浮过程*/
                /*降序：如果当前位置元素小于后一位置元素，交换两个位置元素，实现小值上浮过程*/
                if (array[j + 1] < array[j]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    /*一旦进行了数据交换，将标志位改为true*/
                    flag = true;
                }
            }
            /*进行了数据交换，将flag初始化为false,如果未进行数据交换，证明数组已存在顺序，毋需进行下次排序*/
            if (flag) {
                flag = false;
            } else {
                break;
            }
        }

    }

    /**
     * 选择排序法（升序）：时间复杂度 O(n²)
     *
     * @param array
     */
    private static void selectSortAsc(int[] array) {
        /*共循环N-1次，N为数组长度*/
        for (int i = 0; i < array.length - 1; i++) {
            /*中间值，用于存储数组中的最值，每次假定数组内未完成排序的第一个元素为最小值或者最大值*/
            int tempValue = array[i];
            int tempIndex = i;
            /*依次对后面的元素与假定值进行比较*/
            for (int j = i + 1; j < array.length; j++) {
                /*升序：如果后续元素中还存在比假定值小的元素，将该元素保存在中间值中*/
                /*降序：如果后续元素中还存在比假定值大的元素，将该元素保存在中间值中*/
                if (array[j] < tempValue) {
                    tempValue = array[j];
                    tempIndex = j;
                }
            }
            /*如果中间值索引未变化，说明当前值确为最值，否则交换未完成排序第一个元素和实际最小值*/
            if (tempIndex != i) {
                array[tempIndex] = array[i];
                array[i] = tempValue;
            }
        }
    }

    /**
     * 插入排序法（升序）：时间复杂度 O(n²)
     *
     * @param array
     */
    private static void insertSortAsc(int[] array) {
        /*用于记录当前即将要插入的值*/
        int tempValue;
        /*用于记录当前索引位置的前一个索引*/
        int beforeIndex;
        /*默认第一个位置索引0的数据是有序的，从第二个位置的数据开始循环，直到最后一个位置的元素*/
        for (int i = 1; i < array.length; i++) {
            tempValue = array[i];
            beforeIndex = i;
            /*以下两个条件与关系*/
            /*1、若beforeIndex = 0：说明当前位置已第一个位置，即为当前元素应在位置*/
            /*2、升序：tempValue < array[beforeIndex]，如果当前小于前一个位置的元素*/
            /*2、降序：tempValue > array[beforeIndex]，如果当前大于前一个位置的元素*/
            /*如果条件存在，说明还未为当前元素找到正确位置*/
            while (beforeIndex > 0 && tempValue < array[beforeIndex - 1]) {
                /*将有序元素进行位置挪动*/
                array[beforeIndex] = array[beforeIndex - 1];
                /*指针移动，进行元素循环，对前一个位置元素进行判断*/
                beforeIndex--;
            }
            /*循环完毕，找到元素位置,将当前值赋给所找到位置*/
            array[beforeIndex] = tempValue;
        }
    }

    /**
     * 希尔排序法（升序，交换式）：时间复杂度 O(n log n)
     *
     * @param array
     */
    private static void shellSortAscByExchange(int[] array) {
        /*用于交换中间值*/
        int tempValue;
        /*将数组元素进行分组，初始组数为元素长度的1/2，步长为组数*/
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            /*从第gap个元素，逐个对其所在组进行插入排序操作*/
            for (int i = gap; i < array.length; i++) {
                /*对组内元素进行遍历，依次对组内的元素和组内已完成排序的元素进行对比，直至到正确的位置
                 * 1、j = i - gap：判断组内当前位置前是否还有元素，如果有元素，就用当前元素和前面的元素进行比较，不满元顺序
                 *    则交换当前位置元素和前面的元素，避免越下界
                 * 2、j -= gap：依次用当前元素和组内前面的元素比较，直到找到合适的位置*/
                for (int j = i - gap; j >= 0; j -= gap) {
                    /*如果组内元素的值顺序不对，对组内元素进行交换*/
                    if (array[j] > array[j + gap]) {
                        tempValue = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = tempValue;
                    }
                }

            }
        }
    }

    /**
     * 希尔排序法（升序，移位式）:时间复杂度 O(n log n)
     *
     * @param array
     */
    private static void shellSortAscByMove(int[] array) {
        /*用于交换中间值*/
        int tempValue;
        /*将数组元素进行分组，初始组数为元素长度的1/2，*/
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            /*对组内元素进行遍历，依次对组内的元素和组内已完成排序的元素进行对比，直至到正确的位置*/
            for (int i = gap; i < array.length; i++) {
                int j = i;
                tempValue = array[j];
                /*如果当前值小于前一位置元素，对数组内的元素进行位置挪动，最终j所指向的位置即为当前元素应处的位置*/
                while (j - gap >= 0 && tempValue < array[j - gap]) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = tempValue;
            }
        }
    }

    /**
     * 快速排序法（升序）:时间复杂度 O(n log n)
     *
     * @param array
     */
    private static void quickSort(int[] array, int left, int right) {
        /*获取数组的中轴值*/
        int pivot = array[(left + right) / 2];
        /*左下标,开始排序的左下标值*/
        int startLeft = left;
        /*右下标,结束排序的右下标值*/
        int endRight = right;
        /*中间值，用于存储数值交换的临时变量*/
        int temp;
        /*此处循环的目的是为了将pivot左边大的值放到右边，右边小的值放到左边*/
        while (startLeft <= endRight) {
            /*在pivot左边找，知道找到大于等于pivot，才退出*/
            while (array[startLeft] < pivot) {
                startLeft += 1;
            }
            /*在pivot左边找，知道找到小于等于pivot，才退出*/
            while (array[endRight] > pivot) {
                endRight -= 1;
            }
            /*说明pivot左右两变的值，已经按照以下顺序排列：
             * 左边全部小于等于pivot，右边全部大于等于pivot*/
            if (startLeft >= endRight) {
                break;
            }
            /*交换左右的数值*/
            temp = array[startLeft];
            array[startLeft] = array[endRight];
            array[endRight] = temp;

//            /*交换完成后，发现array[startLeft] == pivot ,endRight前移一位*/
//            if (array[startLeft] == pivot) {
//                endRight -= 1;
//            }
//            /*交换完成后，发现array[endRight] == pivot ,startLeft后移一位*/
//            if (array[endRight] == pivot) {
//                startLeft += 1;
//            }
        }
        /*如果 l == r,必须l++,r--,否则会出现栈溢出*/
        if (startLeft == endRight) {
            startLeft += 1;
            endRight -= 1;
        }
        /*向左递归*/
        if (left < endRight) {
            quickSort(array, left, endRight);
        }

        /*向右递归*/
        if (right > startLeft) {
            quickSort(array, startLeft, right);
        }
    }


    private static void quickSort_2(int[] array, int left, int right) {
        if (array == null || left >= right) {
            return;
        }
        int i = left, j = right;
        /*以左侧值为基准值*/
        int pivotKey = array[left];
        /*直到左右两侧移动指针重合*/
        while (i < j) {
            /*从右侧开始寻找比基准值小的值*/
            while (i < j && array[j] >= pivotKey) {
                j--;
            }
            /*如果此时左右两侧指针仍然未重合，将比基准值小的值赋给左侧起始位置，并经左侧起始位置向右移动一位*/
            if (i < j) {
                array[i++] = array[j];
            }
            /*从左侧开始寻找比基准值大的值*/
            while (i < j && array[i] <= pivotKey) {
                i++;
            }
            /*如果此时左右两侧指针仍然未重合，将比基准值大的值赋给右侧起始位置，并经右侧起始位置向左移动一位*/
            if (i < j) {
                array[j--] = array[i];
            }
        }
        /*当两个索引重合的时候，将基准值赋给当前位置*/
        array[i] = pivotKey;
        /*此时重合位置就是基准值的确切位置，故在后续递归的过程中，将该位置不再参与排序的过程*/
        System.out.println(Arrays.toString(array));
        quickSort_2(array, left, i - 1);
        quickSort_2(array, i + 1, right);
    }

    /**
     * 归并算法:时间复杂度 O(n log n)
     *
     * @param array
     * @param left
     * @param right
     * @param temp
     */
    private static void mergeSort(int[] array, int left, int right, int[] temp) {
        /*依次对数组的元素进行拆分，直到每个组内只有一个元素为止*/
        /*根据栈规则，最后分解的，最先合并*/
        if (left < right) {
            /*中间值*/
            int mid = (left + right) / 2;
            /*向左递归进行分解*/
            mergeSort(array, left, mid, temp);
            /*向右递归进行分解*/
            mergeSort(array, mid + 1, right, temp);
            /*每分解一次，就对数据合并一次*/
            merge(array, left, mid, right, temp);
        }
    }

    /**
     * @param array 原始数组
     * @param left  左边有序序列的初始索引
     * @param right 右边索引
     * @param temp  中转数组
     * @param mid   中间变量
     */
    private static void merge(int[] array, int left, int mid, int right, int[] temp) {
        /*初始化左侧有序索引*/
        int startLeft = left;
        /*初始化右侧有序索引*/
        int startRight = mid + 1;
        /*用于记录中间数据的索引移动情况，起始索引为0*/
        int index = 0;
        /*依次扫描左侧、右侧有序数列，直到左侧序列或右侧序列数据循环完毕*/
        while (startLeft <= mid && startRight <= right) {
            /*如果左侧序列内的元素小于右侧序列的元素，则将左侧序列元素加入中间数组，
             * 赋值完毕后，将左侧索引、中间序列索引下移*/
            if (array[startLeft] <= array[startRight]) {
                temp[index++] = array[startLeft++];
            } else {
                /*反之，将右侧序列元素加入到中间数组中，
                 * 赋值完毕后，将右侧索引、中间序列索引下移*/
                temp[index++] = array[startRight++];
            }
        }
        /*如果左侧有序数列未扫描完毕，将剩余元素依次添加到中间数组中
         * 赋值完毕后，将左侧索引、中间序列索引下移*/
        while (startLeft <= mid) {
            temp[index++] = array[startLeft++];
        }
        /*如果右侧有序数列未扫描完毕，将剩余元素依次添加到中间数组中
         * 赋值完毕后，将右侧索引、中间序列索引下移*/
        while (startRight <= right) {
            temp[index++] = array[startRight++];
        }
        index = 0;
        /*中间索引，用以将中间序列的元素拷贝至数组
         * 并不是每次都拷贝所有，每次只需对数据左边起始索引至右边的索引进行赋值即可*/
        int tempLeft = left;
        while (tempLeft <= right) {
            array[tempLeft++] = temp[index++];
        }
    }

    /**
     * 基数排序：
     *
     * @param array
     */
    private static void radixSort(int[] array) {
        /*定义一个二维数组，表示10个桶，每个桶是一位数组*/
        /*说明：
         * 1、二维数组包含10个以为数组
         * 2、为了防止在放入数据的时候，数据溢出，则每个一位数组（桶），大小定义为array.length
         * 3、基数排序是使用空间换时间的经典算法*/
        int[][] bucket = new int[10][array.length];
        /*为了记录每个桶中，实际存放了多少个数据，定义一个一维数组记录每个桶中的数组个数
         * eg：bucketElementsCount[0]，记录的是放入bucket[0]中的数据个数*/
        int[] bucketElementsCount = new int[10];
        /*获取数组中最大值,默认数组内的第一个元素为最大值*/
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        /*获取最大数据的最大位数*/
        int lengthMax = (max + "").length();
        /*循环数据的长度，第一轮取个位，第二轮取十位，依次类推*/
        for (int i = 0, m = 1; i < lengthMax; i++, m *= 10) {
            for (int j = 0; j < array.length; j++) {
                /*取相应位数的模值*/
                int digitalNumber = array[j] / m % 10;
                /*根据模值将数据依次放入对应的桶中*/
                bucket[digitalNumber][bucketElementsCount[digitalNumber]++] = array[j];
            }
            /*用于记录数组中的索引位置*/
            int index = 0;
            /*依次取出桶中的元素，还原至数组中*/
            for (int k = 0; k < bucket.length; k++) {
                /*判断各个桶中的元素是否为0，不为0 则将数据还原至数组*/
                if (bucketElementsCount[k] != 0) {
                    /*bucketElementsCount[k]代表编号为k的桶中的数据个数*/
                    for (int n = 0; n < bucketElementsCount[k]; n++) {
                        array[index++] = bucket[k][n];
                    }
                    /*数据全部还原之后，将对应编号桶的数据个数置为0，避免影响下一次数据还原，
                     * 在整个二维数组的赋值过程中，是覆盖过程*/
                    bucketElementsCount[k] = 0;
                }
            }
        }
    }

    /**
     * 基数排序简化版
     *
     * @param arr
     */
    private static void radixSortSimple(int[] arr) {
        //临时数组
        Integer[] temp = new Integer[arr.length];
        //找出目标数组中的最大值、最小值，用于计算有多少位
        int maxValue = arr[0];
        int minValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        maxValue = Math.max(Math.abs(maxValue), Math.abs(minValue));
        /*求出数据的最大位数*/
        int maxLen = (maxValue + "").length();
        int base = 10;
        /*从个位到最高位*/
        for (int i = 0, pow = 1; i < maxLen; i++, pow *= base) {
            /*定义一个一维数组，代表19个桶，用于存储各个桶中数据的个数，
            负数的存在，故需要19个桶*/
            int[] bucket = new int[base * 2 - 1];

            for (int j = 0; j < arr.length; j++) {
                /*根据余数来计算桶的编号，并存储各个桶中数据的总数
                 * 计算桶的编号：arr[j] / pow % base
                 * 计算对应编号在数组内的索引：arr[j] / pow % base + base - 1
                 * bucket[1] = 8,代表编号为 -8 这个桶内一共有8个元素*/
                bucket[arr[j] / pow % base + base - 1]++;
            }

            /*桶是有序的，将各个桶中的数字个数，转化成各个桶中最后一个数字的下标索引*/
            for (int j = 1; j < bucket.length; j++) {
                bucket[j] += bucket[j - 1];
            }

            /*桶排序属于稳定排序，当数据相同的时候，原数组排在后面的任然会排在后面
             * 故此处的循环必须从原数组的末端向前扫描*/
            for (int j = arr.length - 1; j >= 0; j--) {
//                /*获取数组中的数据*/
//                int i1 = arr[j];
//                /*对数据取模*/
//                int i2 = i1 / pow % base;
//                /*获取桶数组的index*/
//                int i3 = i2 + base - 1;
//                /*获取桶数据*/
//                int i4 = bucket[i3];
//                /*由于数组index是以0开始的，统计数据是以1开始的，故在对中间数据赋值的时候，index需要偏移一位*/
//                temp[--i4] = arr[j];
//                /*数据取出之后，需要修改桶中数据统计值，进行减一操作，*/
//                bucket[i3] = i4;
                /*以上部分是对这段代码的说明*/
                int len = --bucket[arr[j] / pow % base + base - 1];
                temp[len] = arr[j];
                System.out.println("第" + (j + 1) + "次中间数组temp:" + Arrays.toString(temp));
            }
            /*将中间数组的元素拷贝至数组*/
            for (int j = 0; j < arr.length; j++) {
                arr[j] = temp[j];
            }
            System.out.println(Arrays.toString(arr));
        }

    }
}
