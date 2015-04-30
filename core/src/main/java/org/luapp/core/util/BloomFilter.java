package org.luapp.core.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

/**
 * 布隆过滤器
 * 
 * http://www.cnblogs.com/haippy/archive/2012/07/13/2590351.html
 *
 */
public class BloomFilter {
    private BitSet bitSet;
    private int bitSetSize;
    private int addedElements;
    private int hashFunctionNumber;

    /**
     * 构造一个布隆过滤器，过滤器的容量为c * n 个bit.
     * 
     * 通常我们要预设一个相对大一点的bit空间，这样冲突才会比较小，
     * 哈希函数的数量K也是越多越好，但是通常8个以上就行了，要看能接受的冲突阈值。
     * 
     * @param c 当前过滤器预先开辟的最大包含记录,通常要比预计存入的记录多一倍.
     * @param n 当前过滤器预计所要包含的记录.
     * @param k 哈希函数的个数，等同每条记录要占用的bit数.
     */
    public BloomFilter(int c, int n, int k) {
        this.hashFunctionNumber = k;
        this.bitSetSize = (int) Math.ceil(c * k);
        this.addedElements = n;
        this.bitSet = new BitSet(bitSetSize);
    }

    public void init(String fileName) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            if (line != null && line.length() > 0) {
                put(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void put(String str) {
        int[] positions = createHashes(str.getBytes(), hashFunctionNumber);
        for (int i = 0; i < positions.length; i++) {
            int position = Math.abs(positions[i] % bitSetSize);
            bitSet.set(position, true);
        }
    }

    public boolean contains(String str) {

        int[] positions = createHashes(str.getBytes(), hashFunctionNumber);

        for (int i : positions) {
            int position = Math.abs(positions[i] / bitSetSize);
            if (!bitSet.get(position)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 将字符串的字节表示进行多哈希编码.
     * 
     * @param bytes 待添加进过滤器的字符串字节表示.
     * @param hashNumber 要经过的哈希个数.
     * @return 各个哈希的结果数组.
     */
    private int[] createHashes(byte[] bytes, int hashNumber) {
        int[] result = new int[hashNumber];

        int k = 0;
        while (k < hashNumber) {
            result[k] = HashFunctions.hash(bytes, k);
            k++;
        }

        return result;
    }

    /**
     * 得到当前过滤器的错误率.
     * @return
     */
    public double getFalsePositiveProbability() {
        // (1 - e^(-k * n / m)) ^ k
        return Math.pow((1 - Math.exp(-hashFunctionNumber * (double) addedElements / bitSetSize)), hashFunctionNumber);
    }

    private static class HashFunctions {

        /**
         * hash  使用的都是常用的字符串hash函数
         * 
         * @param bytes
         * @param k
         * @return
         */
        public static int hash(byte[] bytes, int k) {
            int hashVal = 0;
            switch (k) {
            case 0:
                hashVal = RSHash(bytes);
                break;
            case 1:
                hashVal = JSHash(bytes);
                break;
            case 2:
                hashVal = ELFHash(bytes);
                break;
            case 3:
                hashVal = BKDRHash(bytes);
                break;
            case 4:
                hashVal = APHash(bytes);
                break;
            case 5:
                hashVal = DJBHash(bytes);
                break;
            case 6:
                hashVal = SDBMHash(bytes);
                break;
            case 7:
                hashVal = PJWHash(bytes);
                break;
            default:
                break;
            }
            return hashVal;
        }

        private static int RSHash(byte[] bytes) {
            int hash = 0;
            int magic = 63689;
            int k = bytes.length;
            for (int i = 0; i < k; i++) {
                hash = hash * magic + bytes[k];
                magic *= 378551;
            }

            return hash;
        }

        private static int JSHash(byte[] bytes) {
            int hash = 1315423911;
            for (int i = 0; i < bytes.length; i++) {
                hash ^= ((hash << 5) + bytes[i] + (hash >> 2));
            }
            return hash;
        }

        private static int ELFHash(byte[] bytes) {
            int hash = 0;
            int x = 0;
            int len = bytes.length;
            for (int i = 0; i < len; i++) {
                hash = (hash << 4) + bytes[i];
                if ((x = hash & 0xF0000000) != 0) {
                    hash ^= (x >> 24);
                    hash &= ~x;
                }
            }
            return hash;
        }

        private static int BKDRHash(byte[] bytes) {
            int seed = 131;
            int hash = 0;
            int len = bytes.length;
            for (int i = 0; i < len; i++) {
                hash = (hash * seed) + bytes[i];
            }
            return hash;
        }

        private static int APHash(byte[] bytes) {
            int hash = 0;
            int len = bytes.length;
            for (int i = 0; i < len; i++) {
                if ((i & 1) == 0) {
                    hash ^= ((hash << 7) ^ bytes[i] ^ (hash >> 3));
                } else {
                    hash ^= (~((hash << 11) ^ bytes[i] ^ (hash >> 5)));
                }
            }
            return hash;
        }

        private static int DJBHash(byte[] bytes) {
            int hash = 5381;
            int len = bytes.length;
            for (int i = 0; i < len; i++) {
                hash = ((hash << 5) + hash) + bytes[i];
            }
            return hash;
        }

        private static int SDBMHash(byte[] bytes) {
            int hash = 0;
            int len = bytes.length;
            for (int i = 0; i < len; i++) {
                hash = bytes[i] + (hash << 6) + (hash << 16) - hash;
            }
            return hash;
        }

        private static int PJWHash(byte[] bytes) {
            long BitsInUnsignedInt = (4 * 8);
            long ThreeQuarters = ((BitsInUnsignedInt * 3) / 4);
            long OneEighth = (BitsInUnsignedInt / 8);
            long HighBits = (long) (0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
            int hash = 0;
            long test = 0;
            for (int i = 0; i < bytes.length; i++) {
                hash = (hash << OneEighth) + bytes[i];
                if ((test = hash & HighBits) != 0) {
                    hash = (int) ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
                }
            }
            return hash;
        }
    }

    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(30000000, 10000000, 8);
        bloomFilter.init("data/base.txt");
        System.out.println("Bloom Filter Ready");
        System.out.println("False Positive Probability : " + bloomFilter.getFalsePositiveProbability());
    }

}
