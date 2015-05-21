package org.luapp.practise.alg;

import java.util.Random;

/**
 * 题目场景：
 * <p/>
 * 每首歌曲都是一个评分，现在有2000首歌曲，要求实现一个随机播放器，
 * 每首歌曲播放的概率应该正比于它的评分，
 * 例如评分9.1的歌曲，和评分7.9的歌曲，
 * 播放的次数应该是91:79
 * <p/>
 * Created by lum on 2015/5/21.
 */
public class WeightSelect {

    /**
     * 把评分从小到大排序，之后把根据每首歌的评分，生成一个半闭开区间，
     * 然后生成一个随机数，看随机数落在哪个区间，就是选择的那首歌。
     * 例如，有三首歌，评分是[1,2,3] 那么应该是生成三个区间 [0-1,1-3,3-6]，
     * 之后生成一个0-6之间的随机数，随机数落在哪个区间，就选择对应的歌曲
     */
    public static void solution1() {

    }

    /**
     * 假定每个评分只到小数点后一位，那么其实，利用空间换取时间的思路，
     * 只需要把每首歌按照他的评分多少相应的复制多少重复的歌曲，
     * 并且把所有的歌曲都扔到一个池子里面，之后从池子里面等概率的选取一首歌就行了。
     * 在最坏的情况下，2000首歌曲的评分都是9.9，那么每首歌需要复制99首。
     */
    public static void solution2() {
        float[] itemWeights = new float[]{1.1f, 2.2f, 3.3f, 4.4f};
        int selectedItem = -1;
        int totalWeight = 0;

        for (int i = 0; i < itemWeights.length; i++) {
            totalWeight += itemWeights[i] * 10;
        }

        float[] rndArray = new float[totalWeight];

        int start = 0;

        for (int i = 0; i < itemWeights.length; i++) {
            int j = (int) (itemWeights[i] * 10);
            for (int k = 0; k < j; k++) {
                rndArray[start++] = itemWeights[i];
            }
        }

        Random r = new Random(System.currentTimeMillis());

        for (int i = 0; i < 20; i++) {
            System.out.println(rndArray[r.nextInt(rndArray.length)]);
        }
    }

    public static void main(String[] args) {
        solution1();
        solution2();
    }
}
