package com.bvtech.toolslibrary.Utility;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ByteUtil {

    public static byte[] getBytes(short v) {
        byte[] writeBuffer = new byte[2];
        writeBuffer[0] = (byte) ((v >>> 0) & 0xFF);
        writeBuffer[1] = (byte) ((v >>> 8) & 0xFF);
        return writeBuffer;
    }

    public static byte[] getBytes(int v) {
        byte[] writeBuffer = new byte[4];
        writeBuffer[3] = (byte) ((v >>> 24) & 0xFF);
        writeBuffer[2] = (byte) ((v >>> 16) & 0xFF);
        writeBuffer[1] = (byte) ((v >>> 8) & 0xFF);
        writeBuffer[0] = (byte) ((v >>> 0) & 0xFF);
        return writeBuffer;
    }

    public static byte[] getBytes(long v) {
        byte[] writeBuffer = new byte[8];
        writeBuffer[7] = (byte) ((v >>> 56) & 0xFF);
        writeBuffer[6] = (byte) ((v >>> 48) & 0xFF);
        writeBuffer[5] = (byte) ((v >>> 40) & 0xFF);
        writeBuffer[4] = (byte) ((v >>> 32) & 0xFF);
        writeBuffer[3] = (byte) ((v >>> 24) & 0xFF);
        writeBuffer[2] = (byte) ((v >>> 16) & 0xFF);
        writeBuffer[1] = (byte) ((v >>> 8) & 0xFF);
        writeBuffer[0] = (byte) ((v >>> 0) & 0xFF);
        return writeBuffer;
    }

    public static byte[] getBytes(float v) {
        return getBytes(Float.floatToRawIntBits(v));
    }

    public static byte[] getBytes(double v) {
        return getBytes(Double.doubleToRawLongBits(v));
    }

    public static byte[] getBytes(String v) {
        if (v == null) {
            v = "";
        }
        byte[] buf = new byte[4 + v.length()];
        System.arraycopy(getBytes(v.length()), 0, buf, 0, 4);
        try {
            byte[] vb = v.getBytes("UTF-8");
            System.arraycopy(vb, 0, buf, 4, vb.length);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("NO UTF-8");
        }
        return buf;
    }

    public static String toString(byte[] data, int offset) {
        int length = toInt32(data, offset);
        try {
            return new String(data, offset + 4, length, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("NO UTF-8");
        }
    }

    public static float toFloat(byte[] data, int offset) {
        return Float.intBitsToFloat(toInt32(data, offset));
    }

    public static double toDouble(byte[] data, int offset) {
        return Double.longBitsToDouble(toInt64(data, offset));
    }

    public static short toInt16(byte[] data, int offset) {
        return (short) (data[offset] & 0xFF | (data[offset + 1] & 0xFF) << 8);
    }

    public static int toInt32(byte[] data, int offset) {
        short a1 = (short) (data[offset] & 0x00FF);
        short a2 = (short) (data[offset + 1] & 0x00FF);
        short a3 = (short) (data[offset + 2] & 0x00FF);
        short a4 = (short) (data[offset + 3] & 0x00FF);
        return (a1 | (a2 << 8)
                | (a3 << 16)
                | (a4 << 24));
    }

    public static int toInt32_desc(byte[] data, int offset){
        short a1 = (short) (data[offset] & 0x00FF);
        short a2 = (short) (data[offset + 1] & 0x00FF);
        short a3 = (short) (data[offset + 2] & 0x00FF);
        short a4 = (short) (data[offset + 3] & 0x00FF);
        return ((a1  << 24)|(a2 << 16)
                | (a3 << 8)
                | a4);
    }
    /**
     public static int toInt32(byte[] data, int offset) {
     return (data[offset] & 0xFF) | ((data[offset + 1] & 0xFF) << 8)
     | ((data[offset + 2] & 0xFF) << 16)
     | ((data[offset + 3] & 0xFF) << 24);
     }
     */

    public static long toInt64(byte[] data, int offset) {
        return (((long) (data[offset + 7] & 0xff) << 56)
                | ((long) (data[offset + 6] & 0xff) << 48)
                | ((long) (data[offset + 5] & 0xff) << 40)
                | ((long) (data[offset + 4] & 0xff) << 32)
                | ((long) (data[offset + 3] & 0xff) << 24)
                | ((long) (data[offset + 2] & 0xff) << 16)
                | ((long) (data[offset + 1] & 0xff) << 8) | (data[offset] & 0xff));
    }

    public static int byteArrayToInt_desc(byte[] data, int offset){
        return(
                ((data[offset] & 0xFF) << 24) +
                        ((data[offset + 1] & 0xFF) << 16) +
                        ((data[offset + 2] & 0xFF) << 8) +
                        (data[offset + 3] & 0xFF));
    }

    public static byte[] doublToByteArray(double value) {
        return ByteBuffer.allocate(8).putDouble(value).array();
    }

    public static double byteArrayToDouble(byte[] data, int offset)
    {
        return(
                ((data[offset + 3] & 0x000000FF) << 24) |
                        ((data[offset + 2] & 0x000000FF) << 16) |
                        ((data[offset + 1] & 0x000000FF) << 8) |
                        (data[offset] & 0x000000FF));
    }

    public static List<Byte> floatArrayToByteArray(float[] values)
    {
        List<Byte> list = new ArrayList<>();
        int i = 0;
        for (float value : values) {
            byte[] b = ByteBuffer.allocate(4).putFloat(value).array();
            list.add(b[i++]);
            list.add(b[i++]);
            list.add(b[i++]);
            list.add(b[i++]);
        }
        return list;
    }

    public static List<Byte> stringToByteArray(String s)
    {
        List<Byte> list = new ArrayList<>();
        byte[] byteArray = null;
        if(s!=null)
        {
            if(s.length()>0)
            {
                try
                {
                    byteArray = s.getBytes("UTF-8");
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if (byteArray != null) {
            for (byte b : byteArray) {
                list.add(b);
            }
        } else Log.i("Log", "String converter... nothing to convert"+ Arrays.toString(byteArray));
        return list;
    }

    public static List<Byte> byteArrayToByte(byte[] byteArray){
        List<Byte> list = new ArrayList<>();
        if (byteArray != null) {
            for (byte b : byteArray) {
                list.add(b);
            }
        }
        return list;
    }

    public static byte[] copyBytesToBytes(byte[] byteArray, int ind, int offset){
        List<Byte> list = new ArrayList<>();
        list = copyBytesToArray(byteArray, ind, offset);
        byte[] b = new byte[list.size()];
        for(int i = 0; i < b.length ; i++){
            b[i] = list.get(i);
        }
        return b;
    }

    public static float[] copyOfArray(ArrayList<Float> srcArray){
        float[] desArray = new float[srcArray.size()];
        for(int i = 0; i < srcArray.size() ; i++){
            desArray[i] = srcArray.get(i);
        }
        return desArray;
    }

    public static List<Byte> copyBytesToArray(byte[] byteArray, int ind, int offset){
        List<Byte> list = new ArrayList<>();
        if (byteArray != null) {
            for (int i = ind; i < (ind + offset); i++){
                list.add(byteArray[i]);
            }
        }
        return list;
    }

    public static byte[] copyOfArray(char[] srcArray){
        byte[] des = new byte[srcArray.length];
        if (srcArray != null) {
            for (int i = 0; i < srcArray.length; i++){
                des[i] = (byte) srcArray[i];
            }
        }
        return des;
    }

    public static byte[] copyOfArray(char[] srcArray, int size){
        byte[] des = new byte[size];
        if (srcArray != null) {
            for (int i = 0; i < size; i++){
                des[i] = (byte) srcArray[i];
            }
        }
        return des;
    }

    public static void copyOfArray(List<Byte> desArray, byte[] srcArray){
        if (desArray != null && srcArray != null) {
            for (int i = 0; i < srcArray.length; i++){
                desArray.add(srcArray[i]);
            }
        }
    }

    public static void copyOfArray(List<Byte> desArray, byte[] srcArray, int f, int offset){
        if (desArray != null && srcArray != null) {
            for (int i = f; i < (f + offset); i++){
                desArray.add(srcArray[i]);
            }
        }
    }


    public static void copyOfArray(List<Byte> desArray, List<Byte> srcArray){
        if (desArray != null && srcArray != null) {
            for (int i = 0; i < srcArray.size(); i++){
                desArray.add(srcArray.get(i));
            }
        }
    }

    public static void copyOfArray(List<Byte> desArray, List<Byte> srcArray, int f, int offset){
        if (desArray != null && srcArray != null) {
            for (int i = f; i < (f + offset); i++){
                desArray.add(srcArray.get(i));
            }
        }
    }

    public static List<Byte> copyOfArray(List<Byte> srcArray, int start, int end){
        List<Byte> desArray = new ArrayList<>();
        if (srcArray != null) {
            for (int i = start; i < end; i++){
                desArray.add(srcArray.get(i));
            }
        }
        return desArray;
    }

    public static byte[] copyOfArray(byte[] srcArray, int start, int end){
        List<Byte> list = new ArrayList<>();
        copyOfArray(list, srcArray, start, end);
        byte[] b = new byte[list.size()];
        for(int i = 0; i < b.length ; i++){
            b[i] = list.get(i);
        }
        return b;
    }

    public static byte[] byteToByteArray(List<Byte> list){
        byte [] byteArray = new byte[list.size()];
        for (int i = 0 ; i<list.size(); i++){
            byteArray [i] = list.get(i);
        }
        return byteArray;
    }

    public static List<Byte> floatToByteArray(float value)
    {
        List<Byte> list = new ArrayList<>();
        byte [] b = ByteBuffer.allocate(4).putFloat(value).array();
        list.add(b[3]);
        list.add(b[2]);
        list.add(b[1]);
        list.add(b[0]);
        return list;
    }

    public static short byteArrayToShort(byte[] data, int offset) {
        return (short) (((data[offset+1] << 8)) | ((data[offset] & 0xff)));
    }

    public static List<Byte> shortToByteArray(short s) {
        List<Byte> list = new ArrayList<>();
        list.add((byte) (s & 0x00FF));
        list.add((byte) ((s & 0xFF00) >> 8));
        return list;
    }

    public static int byteArrayToTime(byte[] data, int offset) {
        return (
                ((data[offset + 3] & 0x000000FF) << 24) |
                        ((data[offset + 2] & 0x000000FF) << 16) |
                        ((data[offset + 1] & 0x000000FF) << 8) |
                        (data[offset] & 0x000000FF));
    }


    public static List<Byte> timeToByteArray(Date cal){

        Calendar sourceTime = Calendar.getInstance();
        sourceTime.set(Calendar.YEAR, 2015);
        sourceTime.set(Calendar.MONTH, 0);
        sourceTime.set(Calendar.DAY_OF_MONTH, 1);
        sourceTime.set(Calendar.HOUR_OF_DAY, 0);
        sourceTime.set(Calendar.MINUTE, 0);
        sourceTime.set(Calendar.SECOND, 0);

        Date sourceDate = sourceTime.getTime();

        long millis = ((cal.getTime() - sourceDate.getTime())/1000)+1;
        Log.i("LOG", "Long milisecond: "+ millis);
        List<Byte> list =  new ArrayList<>();
        list.add((byte) (millis));
        list.add((byte) (millis >> 8));
        list.add((byte) (millis >> 16));
        list.add((byte) (millis >> 24));
        return list;
    }

    public byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public static List<Byte> longToByteArray(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        byte[] array = buffer.array();
        List<Byte> data = new ArrayList<>();
        for(int i = 1; i <= (array.length - 4); i++){
            data.add(array[array.length - i]);
        }
        return data;
    }

    public static long bytesToLong(List<Byte> bytes) {
        byte[] b = new byte[bytes.size()];
        for(int i = 0; i < b.length; i++){
            b[i] = bytes.get(i);
        }
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(b);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public static List<Byte> intToByteArray (int value) {
        List<Byte> list = new ArrayList<>();
        byte [] b = ByteBuffer.allocate(4).putInt(value).array();
        list.add(b[3]);
        list.add(b[2]);
        list.add(b[1]);
        list.add(b[0]);
        return list;
    }

    public static float byteArrayToFloat(byte[] byteArray){
        return ByteBuffer.wrap(byteArray).getFloat();
    }
}

